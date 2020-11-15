package zxh.demo.tw.assignment.conference.domain.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final LocalTime begin;
    private final LocalTime end;
    private List<Talk> talks = new ArrayList<>();

    public Session(LocalTime begin, LocalTime end) {
        this.begin = begin;
        this.end = end;
    }
}
