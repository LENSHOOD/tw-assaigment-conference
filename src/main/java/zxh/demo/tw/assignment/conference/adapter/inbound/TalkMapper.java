package zxh.demo.tw.assignment.conference.adapter.inbound;

import static java.util.Objects.isNull;

import zxh.demo.tw.assignment.conference.adapter.exception.IllegalTalkDescriptionException;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TalkMapper {
    /**
     * 2 or 3 groups to decompose the talk description.
     * Group1: talk name
     * Group2: minutes or lightning
     * Group3: (if exists) minutes number
     */
    private static final Pattern TALK_DESCRIPTION = Pattern.compile("(.+) ((\\d+)min|lightning)");

    private TalkMapper() {}

    public static Talk toTalk(String talkDescription) {
        if (isNull(talkDescription) || talkDescription.isBlank()) {
            throw new IllegalTalkDescriptionException(talkDescription);
        }

        Matcher talkMatcher = TALK_DESCRIPTION.matcher(talkDescription);
        if (!talkMatcher.matches()) {
            throw new IllegalTalkDescriptionException(talkDescription);
        }

        String talkName = talkMatcher.group(1);
        if (talkName.isBlank()) {
            throw new IllegalTalkDescriptionException(talkDescription);
        }

        String minutesStr = talkMatcher.group(3);
        Length talkLength = isNull(minutesStr)
                ? Length.createLightning()
                : Length.createNormal(Integer.parseInt(minutesStr));

        return new Talk(talkName, talkLength);
    }
}
