package zxh.demo.tw.assignment.conference.domain.entity;

import zxh.demo.tw.assignment.conference.domain.exception.SessionFullException;
import zxh.demo.tw.assignment.conference.domain.exception.TrackFullException;
import zxh.demo.tw.assignment.conference.domain.factory.SessionFactory;
import zxh.demo.tw.assignment.conference.domain.vo.SessionType;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;
import java.util.ArrayList;
import java.util.List;

public class Track {
    private final List<Session> sessions = new ArrayList<>();

    public Track() {
        Session morningSession = SessionFactory.create(SessionType.MORNING);
        Session afternoonSession = SessionFactory.create(SessionType.AFTERNOON);
        sessions.add(morningSession);
        sessions.add(afternoonSession);
    }

    public void arrangeTalk(Talk talk) {
        boolean isFull = true;
        for (Session session : sessions) {
            try {
                session.addTalk(talk);
                isFull = false;
                break;
            } catch (SessionFullException e) {
                // continue to next loop
            }
        }

        if (isFull) {
            throw new TrackFullException();
        }
    }

    public List<Session> getSessions() {
        return List.copyOf(sessions);
    }
}
