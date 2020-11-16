package zxh.demo.tw.assignment.conference.domain.vo;

public class Length {
    private final int durationMinutes;

    private Length(int lengthNum) {
        this.durationMinutes = lengthNum;
    }

    public static Length createNormal(int minutes) {
        return new Length(minutes);
    }

    public static Length createLightning() {
        return new Length(5);
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
