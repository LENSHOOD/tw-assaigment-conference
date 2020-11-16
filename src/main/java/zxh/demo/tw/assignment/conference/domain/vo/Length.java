package zxh.demo.tw.assignment.conference.domain.vo;

public class Length {
    public enum LengthType {
        NORMAL, LIGHTNING
    }

    private final int durationMinutes;
    private final LengthType type;

    private Length(int lengthNum, LengthType type) {
        this.type = type;
        this.durationMinutes = lengthNum;
    }

    public static Length createNormal(int minutes) {
        return new Length(minutes, LengthType.NORMAL);
    }

    public static Length createLightning() {
        return new Length(5, LengthType.LIGHTNING);
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public LengthType getType() {
        return type;
    }
}
