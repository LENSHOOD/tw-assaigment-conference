package zxh.demo.tw.assignment.conference.adapter.inbound;

import zxh.demo.tw.assignment.conference.adapter.exception.IllegalTxtFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TxtReader {
    private TxtReader() {}

    public static List<String> readLinesFrom(Path txtFilePath) {
        try {
            return Files.readAllLines(txtFilePath);
        } catch (IOException e) {
            throw new IllegalTxtFileException();
        }
    }
}
