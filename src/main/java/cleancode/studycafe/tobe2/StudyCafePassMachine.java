package cleancode.studycafe.tobe2;

import cleancode.studycafe.tobe2.exception.AppException;
import cleancode.studycafe.tobe2.io.InputHandler;
import cleancode.studycafe.tobe2.io.OutputHandler;
import cleancode.studycafe.tobe2.io.StudyCafeLockerPassReader;
import cleancode.studycafe.tobe2.io.StudyCafePassReader;
import cleancode.studycafe.tobe2.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe2.model.StudyCafePass;
import cleancode.studycafe.tobe2.model.StudyCafePassType;
import cleancode.studycafe.tobe2.model.StudyCafeProcessor;
import cleancode.studycafe.tobe2.model.StudyCafeProcessorFactory;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final StudyCafePassReader studyCafePassReader;
    private final StudyCafeLockerPassReader studyCafeLockerPassReader;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, StudyCafePassReader studyCafePassReader, StudyCafeLockerPassReader studyCafeLockerPassReader) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.studyCafePassReader = studyCafePassReader;
        this.studyCafeLockerPassReader = studyCafeLockerPassReader;
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
            StudyCafeProcessor studyCafeProcessor = StudyCafeProcessorFactory.createProcessor(studyCafePassType);
            studyCafeProcessor.process();
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
