package zxh.demo.tw.assignment.conference.domain.entity;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.exception.TooLongTalkException;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;

class ConferenceTest {
    @Test
    public void should_add_talk_to_conference() {
        // given
        Talk talk = new Talk("test1", Length.createNormal(100));
        Conference conference = new Conference();

        // when
        conference.arrangeTalk(talk);

        // then
        assertThat(conference.getTracks().size(), is(1));
        assertThat(conference.getTracks().get(0).getSessions().get(0).getTalks().get(0).getName(), is("test1"));
    }

    @Test
    public void should_add_talk_to_conference_with_more_than_one_track() {
        // given
        Talk talk1 = new Talk("test1", Length.createNormal(100));
        Talk talk2 = new Talk("test2", Length.createNormal(150));
        Talk talk3 = new Talk("test3", Length.createNormal(200));
        Conference conference = new Conference();

        // when
        conference.arrangeTalk(talk1);
        conference.arrangeTalk(talk2);
        conference.arrangeTalk(talk3);

        // then
        assertThat(conference.getTracks().size(), is(2));
        assertThat(conference.getTracks().get(0).getSessions().get(0).getTalks().get(0).getName(), is("test1"));
        assertThat(conference.getTracks().get(1).getSessions().get(1).getTalks().get(0).getName(), is("test3"));
    }

    @Test
    public void should_throw_TooLongTalkException_when_arrange_wrong_talk_that_too_long() {
        Talk talk = new Talk("test1", Length.createNormal(255));
        Conference conference = new Conference();

        assertThrows(TooLongTalkException.class, () -> conference.arrangeTalk(talk));
    }

}