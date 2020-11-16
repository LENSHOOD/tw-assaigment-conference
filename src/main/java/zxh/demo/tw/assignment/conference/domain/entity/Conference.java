package zxh.demo.tw.assignment.conference.domain.entity;

import zxh.demo.tw.assignment.conference.domain.exception.TooLongTalkException;
import zxh.demo.tw.assignment.conference.domain.exception.TrackFullException;
import zxh.demo.tw.assignment.conference.domain.vo.Talk;
import java.util.ArrayList;
import java.util.List;

public class Conference {
    private List<Track> tracks = new ArrayList<>();

    public void arrangeTalk(Talk talk) {
        boolean isAllTrackFull = true;
        for (Track track : tracks) {
            try {
                track.arrangeTalk(talk);
                isAllTrackFull = false;
                break;
            } catch (TrackFullException e) {
                // continue to next loop
            }
        }

        if (isAllTrackFull) {
            Track newTrack = new Track();
            try {
                newTrack.arrangeTalk(talk);
            } catch (TrackFullException e) {
                // if new track cannot take the talk, the talk length is too long
                throw new TooLongTalkException();
            }

            tracks.add(newTrack);
        }
    }

    public List<Track> getTracks() {
        return List.copyOf(tracks);
    }
}
