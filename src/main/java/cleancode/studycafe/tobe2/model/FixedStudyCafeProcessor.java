package cleancode.studycafe.tobe2.model;

public class FixedStudyCafeProcessor implements StudyCafeProcessor {
    @Override
    public boolean satisfiedBy(StudyCafePassType passType) {
        return passType == StudyCafePassType.FIXED;
    }

    @Override
    public void process() {
        System.out.println("고정 이용권을 처리합니다.");
    }
}
