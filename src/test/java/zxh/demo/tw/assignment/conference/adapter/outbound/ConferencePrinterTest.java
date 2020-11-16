package zxh.demo.tw.assignment.conference.adapter.outbound;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.entity.Conference;
import zxh.demo.tw.assignment.conference.domain.entity.Session;
import zxh.demo.tw.assignment.conference.domain.entity.Track;
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

    @Test
    public void should_convert_track_to_string() {
        // given
        Talk talk1 = new Talk("test talk 1", Length.createNormal(100));
        Talk talk2 = new Talk("test talk 2", Length.createLightning());
        Talk talk3 = new Talk("test talk 3", Length.createNormal(100));
        Track track = new Track();
        track.arrangeTalk(talk1);
        track.arrangeTalk(talk2);
        track.arrangeTalk(talk3);

        // when
        String sessionStr = ConferencePrinter.convertTrack(track);

        // then
        assertNotNull(sessionStr);
        String[] talkStrArr = sessionStr.split("\n");
        assertThat(talkStrArr.length, is(5));
        assertThat(talkStrArr[0], is("09:00AM test talk 1 100min"));
        assertThat(talkStrArr[1], is("10:40AM test talk 2 lightning"));
        assertThat(talkStrArr[2], is("12:00PM Lunch"));
        assertThat(talkStrArr[3], is("01:00PM test talk 3 100min"));
        assertThat(talkStrArr[4], is("05:00PM Networking Event"));
    }

    @Test
    public void should_convert_conference_to_string() {
        // given
        Talk talk1 = new Talk("test talk 1", Length.createNormal(100));
        Talk talk2 = new Talk("test talk 2", Length.createLightning());
        Talk talk3 = new Talk("test talk 3", Length.createNormal(100));
        Talk talk4 = new Talk("test talk 4", Length.createNormal(160));
        Talk talk5 = new Talk("test talk 5", Length.createNormal(160));
        Conference conference = new Conference();
        conference.arrangeTalk(talk1);
        conference.arrangeTalk(talk2);
        conference.arrangeTalk(talk3);
        conference.arrangeTalk(talk4);
        conference.arrangeTalk(talk5);

        // when
        String sessionStr = ConferencePrinter.convertConference(conference);

        // then
        assertNotNull(sessionStr);
        String[] talkStrArr = sessionStr.split("\n");
        assertThat(talkStrArr.length, is(12));
        assertThat(talkStrArr[0], is("Track 1:"));
        assertThat(talkStrArr[1], is("09:00AM test talk 1 100min"));
        assertThat(talkStrArr[2], is("10:40AM test talk 2 lightning"));
        assertThat(talkStrArr[3], is("12:00PM Lunch"));
        assertThat(talkStrArr[4], is("01:00PM test talk 3 100min"));
        assertThat(talkStrArr[5], is("05:00PM Networking Event"));
        assertThat(talkStrArr[6], is(""));
        assertThat(talkStrArr[7], is("Track 2:"));
        assertThat(talkStrArr[8], is("09:00AM test talk 4 160min"));
        assertThat(talkStrArr[9], is("12:00PM Lunch"));
        assertThat(talkStrArr[10], is("01:00PM test talk 5 160min"));
        assertThat(talkStrArr[11], is("05:00PM Networking Event"));
    }
}