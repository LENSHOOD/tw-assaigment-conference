package zxh.demo.tw.assignment.conference.domain.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.domain.exception.TrackFullException;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;

class TrackTest {
    @Test
    public void should_create_track_with_morning_and_after_session() {
        Track track = new Track();
        assertThat(track.getSessions().size(), is(2));
        assertThat(track.getSessions().get(0).getBegin().getHour(), is(9));
        assertThat(track.getSessions().get(1).getBegin().getHour(), is(13));
    }

    @Test
    public void should_throw_TrackFullException_when_all_sessions_fulled() {
        // given
        Track track = new Track();
        Talk talk1 = new Talk("test1", Length.createNormal(180));
        Talk talk2 = new Talk("test1", Length.createNormal(240));

        // when
        track.arrangeTalk(talk1);
        track.arrangeTalk(talk2);

        // then
        assertThrows(TrackFullException.class, () -> track.arrangeTalk(talk1));
    }
}