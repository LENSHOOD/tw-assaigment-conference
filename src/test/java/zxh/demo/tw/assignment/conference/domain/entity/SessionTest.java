package zxh.demo.tw.assignment.conference.domain.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.exception.SessionFullException;
import zxh.demo.tw.assignment.conference.domain.factory.SessionFactory;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import zxh.demo.tw.assignment.conference.domain.vo.SessionType;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;
import zxh.demo.tw.assignment.conference.domain.vo.TalkSchedule;
import java.time.LocalTime;
import java.util.List;

class SessionTest {
    @Test
    public void should_add_talk_to_session() {
        // given
        Session session = new Session(LocalTime.of(9, 0), LocalTime.of(9, 1), null);
        Talk testTalk = new Talk("test", Length.createNormal(1));

        // when
        session.addTalk(testTalk);

        // then
        assertThat(session.getTalks(), contains(testTalk));
    }

    @Test
    public void should_throw_SessionFullException_when_add_too_long_talk_to_session() {
        Session session = new Session(LocalTime.of(9, 0), LocalTime.of(9, 1), null);
        Talk testTalk = new Talk("test", Length.createNormal(2));

        assertThrows(SessionFullException.class, () -> session.addTalk(testTalk));
    }

    @Test
    public void should_get_session_schedule_with_time_order() {
        // given
        Talk talk1 = new Talk("test talk 1", Length.createNormal(20));
        Talk talk2 = new Talk("test talk 2", Length.createLightning());
        Talk talk3 = new Talk("test talk 3", Length.createNormal(35));
        Session session = SessionFactory.create(SessionType.MORNING);
        session.addTalk(talk1);
        session.addTalk(talk2);
        session.addTalk(talk3);

        // when
        List<TalkSchedule> sessionSchedules = session.getSessionSchedule();

        // then
        assertThat(sessionSchedules.size(), is(3));
        assertThat(sessionSchedules.get(0).getStartTime(), is(LocalTime.of(9, 0)));
        assertThat(sessionSchedules.get(0).getTalk().getName(), is("test talk 1"));
        assertThat(sessionSchedules.get(1).getStartTime(), is(LocalTime.of(9, 20)));
        assertThat(sessionSchedules.get(1).getTalk().getName(), is("test talk 2"));
        assertThat(sessionSchedules.get(2).getStartTime(), is(LocalTime.of(9, 25)));
        assertThat(sessionSchedules.get(2).getTalk().getName(), is("test talk 3"));
    }
}