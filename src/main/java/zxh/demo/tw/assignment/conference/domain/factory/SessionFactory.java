package zxh.demo.tw.assignment.conference.domain.factory;

import zxh.demo.tw.assignment.conference.domain.entity.Session;
import zxh.demo.tw.assignment.conference.domain.vo.SessionType;

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
        return null;
    }

    private static Session createAfternoon() {
        return null;
    }
}