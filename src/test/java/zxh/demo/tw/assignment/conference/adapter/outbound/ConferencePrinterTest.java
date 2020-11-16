package zxh.demo.tw.assignment.conference.adapter.outbound;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.entity.Session;
import zxh.demo.tw.assignment.conference.domain.factory.SessionFactory;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import zxh.demo.tw.assignment.conference.domain.vo.SessionType;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;

class ConferencePrinterTest {
    @Test
    public void should_convert_session_to_string() {
        // given
        Talk talk1 = new Talk("test talk 1", Length.createNormal(20));
        Talk talk2 = new Talk("test talk 2", Length.createLightning());
        Talk talk3 = new Talk("test talk 3", Length.createNormal(35));
        Session session = SessionFactory.create(SessionType.MORNING);
        session.addTalk(talk1);
        session.addTalk(talk2);
        session.addTalk(talk3);

        // when
        String sessionStr = ConferencePrinter.convertSession(session);

        // then
        assertNotNull(sessionStr);
        String[] talkStrArr = sessionStr.split("\n");
        assertThat(talkStrArr.length, is(4));
        assertThat(talkStrArr[0], is("09:00AM test talk 1 20min"));
        assertThat(talkStrArr[1], is("09:20AM test talk 2 lightning"));
        assertThat(talkStrArr[2], is("09:25AM test talk 3 35min"));
        assertThat(talkStrArr[3], is("12:00PM Lunch"));
    }
}