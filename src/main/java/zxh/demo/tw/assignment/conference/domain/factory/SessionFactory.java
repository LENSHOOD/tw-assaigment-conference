package zxh.demo.tw.assignment.conference.domain.factory;

import zxh.demo.tw.assignment.conference.domain.entity.Session;
import zxh.demo.tw.assignment.conference.domain.entity.Talk;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import zxh.demo.tw.assignment.conference.domain.vo.SessionType;
import java.time.LocalTime;

public class SessionFactory {
    private SessionFactory() {
    }

    public static Session create(SessionType type) {
        return switch (type) {
            case MORNING -> createMorning();
            case AFTERNOON -> createAfternoon();
        };
    }

    private static Session createMorning() {
        Session morningSession = new Session(LocalTime.of(9, 0), LocalTime.of(12, 0));
        morningSession.addTalk(new Talk("Lunch", Length.createNormal(0)));
        return morningSession;
    }

    private static Session createAfternoon() {
        Session afternoonSession = new Session(LocalTime.of(13, 0), LocalTime.of(17, 0));
        afternoonSession.addTalk(new Talk("Networking Event", Length.createNormal(0)));
        return afternoonSession;
    }
}