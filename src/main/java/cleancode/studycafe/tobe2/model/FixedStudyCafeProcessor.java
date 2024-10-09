package cleancode.studycafe.tobe2.model;

import cleancode.studycafe.tobe2.io.InputHandler;
import cleancode.studycafe.tobe2.io.OutputHandler;
import cleancode.studycafe.tobe2.io.StudyCafeLockerPassReader;
import cleancode.studycafe.tobe2.io.StudyCafePassReader;

import java.util.List;

public class FixedStudyCafeProcessor implements StudyCafeProcessor {

    private final StudyCafePassReader studyCafePassReader;
    private final StudyCafeLockerPassReader studyCafeLockerPassReader;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public FixedStudyCafeProcessor(StudyCafePassReader studyCafePassReader, StudyCafeLockerPassReader studyCafeLockerPassReader, InputHandler inputHandler, OutputHandler outputHandler) {
        this.studyCafePassReader = studyCafePassReader;
        this.studyCafeLockerPassReader = studyCafeLockerPassReader;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public boolean satisfiedBy(StudyCafePassType passType) {
        return passType == StudyCafePassType.FIXED;
    }

    @Override
    public void process() {
        List<StudyCafePass> studyCafePasses = studyCafePassReader.readStudyCafePasses();
        List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                .toList();
        outputHandler.showPassListForSelection(fixedPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

        List<StudyCafeLockerPass> lockerPasses = studyCafeLockerPassReader.readLockerPasses();
        StudyCafeLockerPass lockerPass = lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                                && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);

        boolean lockerSelection = false;
        if (lockerPass != null) {
            outputHandler.askLockerPass(lockerPass);
            lockerSelection = inputHandler.getLockerSelection();
        }

        if (lockerSelection) {
            outputHandler.showPassOrderSummary(selectedPass, lockerPass);
        } else {
            outputHandler.showPassOrderSummary(selectedPass, null);
        }
    }
}
