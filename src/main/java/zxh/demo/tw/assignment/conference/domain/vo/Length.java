package zxh.demo.tw.assignment.conference.domain.vo;

public class Length {
    private final int durationNum;

    private Length(int lengthNum) {
        this.durationNum = lengthNum;
    }

    public static Length createNormal(int minutes) {
        return new Length(minutes);
    }

    public static Length createLightning() {
        return new Length(5);
    }
}
