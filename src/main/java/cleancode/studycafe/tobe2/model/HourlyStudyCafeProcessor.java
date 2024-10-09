package cleancode.studycafe.tobe2.model;

import cleancode.studycafe.tobe2.io.InputHandler;
import cleancode.studycafe.tobe2.io.OutputHandler;
import cleancode.studycafe.tobe2.io.StudyCafePassReader;

import java.util.List;

public class HourlyStudyCafeProcessor implements StudyCafeProcessor {

    private final StudyCafePassReader studyCafePassReader;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public HourlyStudyCafeProcessor(StudyCafePassReader studyCafePassReader, InputHandler inputHandler, OutputHandler outputHandler) {
        this.studyCafePassReader = studyCafePassReader;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    @Override
    public boolean satisfiedBy(StudyCafePassType passType) {
        return passType == StudyCafePassType.HOURLY;
    }

    @Override
    public void process() {
        List<StudyCafePass> studyCafePasses = studyCafePassReader.readStudyCafePasses();
        List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                .toList();
        outputHandler.showPassListForSelection(hourlyPasses);
        StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }
}
