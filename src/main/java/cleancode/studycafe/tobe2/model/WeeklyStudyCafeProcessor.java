package cleancode.studycafe.tobe2.model;

public class WeeklyStudyCafeProcessor implements StudyCafeProcessor {
    @Override
    public boolean satisfiedBy(StudyCafePassType passType) {
        return passType == StudyCafePassType.WEEKLY;
    }

    @Override
    public void process() {
        System.out.println("주간 이용권을 처리합니다.");
    }
}
