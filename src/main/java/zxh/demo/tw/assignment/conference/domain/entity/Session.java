package zxh.demo.tw.assignment.conference.domain.entity;

import zxh.demo.tw.assignment.conference.domain.exception.SessionFullException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final LocalTime begin;
    private final LocalTime end;
    private final List<Talk> talks = new ArrayList<>();

    public Session(LocalTime begin, LocalTime end) {
        this.begin = begin;
        this.end = end;
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
        long wholeDuration = talks.stream().mapToLong(t -> t.getLength().getDurationNum()).sum();
        boolean isFull = begin.plusMinutes(wholeDuration).plusMinutes(talk.getLength().getDurationNum()).isAfter(end);

        if (isFull) {
            throw new SessionFullException();
        }

        talks.add(talk);
    }
}
