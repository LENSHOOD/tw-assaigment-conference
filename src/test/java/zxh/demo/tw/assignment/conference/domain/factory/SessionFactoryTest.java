package zxh.demo.tw.assignment.conference.domain.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.entity.Session;
import zxh.demo.tw.assignment.conference.domain.vo.SessionType;
import java.time.LocalTime;

class SessionFactoryTest {
    @Test
    public void should_create_morning_session() {
        Session morning = SessionFactory.create(SessionType.MORNING);

        assertEquals(LocalTime.of(9, 0), morning.getBegin());
        assertEquals(LocalTime.of(12, 0), morning.getEnd());
        assertEquals("Lunch", morning.getTalks().get(0).getName());
        assertEquals(0, morning.getTalks().get(0).getLength().getDurationNum());
    }

    @Test
    public void should_create_afternoon_session() {
        Session morning = SessionFactory.create(SessionType.AFTERNOON);

        assertEquals(LocalTime.of(13, 0), morning.getBegin());
        assertEquals(LocalTime.of(17, 0), morning.getEnd());
        assertEquals("Networking Event", morning.getTalks().get(0).getName());
        assertEquals(0, morning.getTalks().get(0).getLength().getDurationNum());
    }
}