package zxh.demo.tw.assignment.conference.adapter.inbound;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import zxh.demo.tw.assignment.conference.adapter.exception.IllegalTalkDescriptionException;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;

class TalkMapperTest {
    @Test
    public void should_map_talk_description_to_talk() {
        // given
        String talk1Str = "Writing Fast Tests Against Enterprise Rails 60min";
        String talk2Str = "Ruby Errors from Mismatched Gem Versions 45min";
        String talk3Str = "Rails for Python Developers lightning";

        // when
        Talk talk1 = TalkMapper.toTalk(talk1Str);
        Talk talk2 = TalkMapper.toTalk(talk2Str);
        Talk talk3 = TalkMapper.toTalk(talk3Str);

        // then
        assertNotNull(talk1);
        assertThat(talk1.getName(), is("Writing Fast Tests Against Enterprise Rails"));
        assertThat(talk1.getLength().getDurationMinutes(), is(60));

        assertNotNull(talk2);
        assertThat(talk2.getName(), is("Ruby Errors from Mismatched Gem Versions"));
        assertThat(talk2.getLength().getDurationMinutes(), is(45));

        assertNotNull(talk3);
        assertThat(talk3.getName(), is("Rails for Python Developers"));
        assertThat(talk3.getLength().getDurationMinutes(), is(5));
    }

    @Test
    public void should_throw_IllegalTalkDescriptionException_when_map_wrong_talk_descriptions() {
        assertThrows(IllegalTalkDescriptionException.class, () -> TalkMapper.toTalk(""));
        assertThrows(IllegalTalkDescriptionException.class, () -> TalkMapper.toTalk(" 35min"));
        assertThrows(IllegalTalkDescriptionException.class, () -> TalkMapper.toTalk("ABC"));
    }
}