package zxh.demo.tw.assignment.conference.domain.entity;

import zxh.demo.tw.assignment.conference.domain.exception.SessionFullException;
import zxh.demo.tw.assignment.conference.domain.vo.EndEvent;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;
import zxh.demo.tw.assignment.conference.domain.vo.TalkSchedule;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final LocalTime begin;
    private final LocalTime end;
    private final List<Talk> talks = new ArrayList<>();
    private final EndEvent endEvent;

    public Session(LocalTime begin, LocalTime end, EndEvent endEvent) {
        this.begin = begin;
        this.end = end;
        this.endEvent = endEvent;
    }

    public LocalTime getBegin() {
        return begin;
    }

    public LocalTime getEnd() {
        return end;
    }

    public List<Talk> getTalks() {
        return List.copyOf(talks);
    }

    public void addTalk(Talk talk) {
        long wholeDuration = talks.stream().mapToLong(t -> t.getLength().getDurationMinutes()).sum();
        boolean isFull = begin.plusMinutes(wholeDuration).plusMinutes(talk.getLength().getDurationMinutes()).isAfter(end);

        if (isFull) {
            throw new SessionFullException();
        }

        talks.add(talk);
    }

    public List<TalkSchedule> getSessionSchedule() {
        LocalTime currentTime = begin;
        List<TalkSchedule> schedules = new ArrayList<>();
        for (Talk talk : talks) {
            schedules.add(new TalkSchedule(currentTime, talk));
            currentTime = currentTime.plusMinutes(talk.getLength().getDurationMinutes());
        }

        return List.copyOf(schedules);
    }

    public EndEvent getEndEvent() {
        return endEvent;
    }
}
