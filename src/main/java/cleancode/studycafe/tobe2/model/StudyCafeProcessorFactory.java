package cleancode.studycafe.tobe2.model;

public interface StudyCafeProcessorFactory {
    StudyCafeProcessor createProcessor(StudyCafePassType studyCafePassType);
}
