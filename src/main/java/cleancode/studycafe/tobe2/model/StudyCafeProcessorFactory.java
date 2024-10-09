package cleancode.studycafe.tobe2.model;

import cleancode.studycafe.asis.exception.AppException;

import java.util.List;

public class StudyCafeProcessorFactory {

    private static final List<StudyCafeProcessor> processors = List.of(
            new HourlyStudyCafeProcessor(),
            new WeeklyStudyCafeProcessor(),
            new FixedStudyCafeProcessor()
    );

    private StudyCafeProcessorFactory() {

    }

    public static StudyCafeProcessor createProcessor(StudyCafePassType passType) {
        return processors.stream()
                .filter(processor -> processor.satisfiedBy(passType))
                .findFirst()
                .orElseThrow(() -> new AppException("해당 이용권을 처리할 수 있는 프로세서가 없습니다."));
    }
}
