package zxh.demo.tw.assignment.conference.adapter.outbound;

import zxh.demo.tw.assignment.conference.domain.entity.Conference;
import zxh.demo.tw.assignment.conference.domain.entity.Session;
import zxh.demo.tw.assignment.conference.domain.entity.Track;
import zxh.demo.tw.assignment.conference.domain.vo.Length;
import zxh.demo.tw.assignment.conference.domain.vo.TalkSchedule;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConferencePrinter {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("hh:mma");

    private ConferencePrinter() {}

    static String convertConference(Conference conference) {
        StringBuilder conferenceStrBuilder = new StringBuilder();
        for (int i = 0; i < conference.getTracks().size(); i++) {
            conferenceStrBuilder.append("Track ")
                    .append(i + 1)
                    .append(":")
                    .append("\n")
                    .append(convertTrack(conference.getTracks().get(i)))
                    .append("\n");
        }
        return conferenceStrBuilder.toString();
    }

    static String convertTrack(Track track) {
        StringBuilder trackStrBuilder = new StringBuilder();
        for (Session session : track.getSessions()) {
            trackStrBuilder.append(convertSession(session)).append("\n");
        }
        return trackStrBuilder.toString();
    }

    static String convertSession(Session session) {
        StringBuilder sessionStrBuilder = new StringBuilder();
        List<TalkSchedule> schedules = session.getSessionSchedule();
        for (TalkSchedule schedule : schedules) {
            sessionStrBuilder.append(schedule.getStartTime().format(TIME_FORMAT))
                    .append(" ")
                    .append(schedule.getTalk().getName())
                    .append(" ")
                    .append(schedule.getTalk().getLength().getType().equals(Length.LengthType.LIGHTNING)
                            ? "lightning"
                            : schedule.getTalk().getLength().getDurationMinutes() + "min")
                    .append("\n");
        }

        sessionStrBuilder.append(session.getEnd().format(TIME_FORMAT))
                .append(" ")
                .append(session.getEndEvent().getName());

        return sessionStrBuilder.toString();
    }
}
