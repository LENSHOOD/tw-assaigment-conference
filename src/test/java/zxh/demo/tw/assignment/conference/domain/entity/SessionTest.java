package zxh.demo.tw.assignment.conference.domain.entity;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.exception.SessionFullException;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import java.time.LocalTime;

class SessionTest {
    @Test
    public void should_add_talk_to_session() {
        // given
        Session session = new Session(LocalTime.of(9, 0), LocalTime.of(9, 1));
        Talk testTalk = new Talk("test", Length.createNormal(1));

        // when
        session.addTalk(testTalk);

        // then
        assertThat(session.getTalks(), contains(testTalk));
    }

    @Test
    public void should_throw_SessionFullException_when_add_too_long_talk_to_session() {
        Session session = new Session(LocalTime.of(9, 0), LocalTime.of(9, 1));
        Talk testTalk = new Talk("test", Length.createNormal(2));

        assertThrows(SessionFullException.class, () -> session.addTalk(testTalk));
    }
}