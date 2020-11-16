package zxh.demo.tw.assignment.conference.app;

import zxh.demo.tw.assignment.conference.adapter.inbound.TalkMapper;
import zxh.demo.tw.assignment.conference.adapter.inbound.TxtReader;
import zxh.demo.tw.assignment.conference.adapter.outbound.ConferencePrinter;
import zxh.demo.tw.assignment.conference.domain.entity.Conference;
import java.io.OutputStream;
import java.nio.file.Path;

public class ArrangeConferenceUseCase {
    public void arrangeConferenceFrom(Path inputFilePath, OutputStream outputStream) {
        Conference conference = new Conference();
        TxtReader.readLinesFrom(inputFilePath).stream().map(TalkMapper::toTalk).forEach(conference::arrangeTalk);
        ConferencePrinter.print(conference, outputStream);
    }
}
