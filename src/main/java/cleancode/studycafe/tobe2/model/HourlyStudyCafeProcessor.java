package cleancode.studycafe.tobe2.model;

public class HourlyStudyCafeProcessor implements StudyCafeProcessor {
    @Override
    public boolean satisfiedBy(StudyCafePassType passType) {
        return passType == StudyCafePassType.HOURLY;
    }

    @Override
    public void process() {
        System.out.println("시간제 이용권을 처리합니다.");
    }
}
