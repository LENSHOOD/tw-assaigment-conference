package zxh.demo.tw.assignment.conference.domain.vo;

import java.time.LocalTime;

public class TalkSchedule {
    private final LocalTime startTime;
    private final Talk talk;

    public TalkSchedule(LocalTime startTime, Talk talk) {
        this.startTime = startTime;
        this.talk = talk;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public Talk getTalk() {
        return talk;
    }
}
