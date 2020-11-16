package zxh.demo.tw.assignment.conference.domain.factory;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.entity.Session;
import zxh.demo.tw.assignment.conference.domain.vo.SessionType;
import java.time.LocalTime;

class SessionFactoryTest {
    @Test
    public void should_create_morning_session() {
        Session morning = SessionFactory.create(SessionType.MORNING);

        assertThat(morning.getBegin(), is(LocalTime.of(9, 0)));
        assertThat(morning.getEnd(), is(LocalTime.of(12, 0)));
        assertThat(morning.getEndEvent().getName(), is("Lunch"));
    }

    @Test
    public void should_create_afternoon_session() {
        Session morning = SessionFactory.create(SessionType.AFTERNOON);

        assertThat(morning.getBegin(), is(LocalTime.of(13, 0)));
        assertThat(morning.getEnd(), is(LocalTime.of(17, 0)));
        assertThat(morning.getEndEvent().getName(), is("Networking Event"));
    }
}