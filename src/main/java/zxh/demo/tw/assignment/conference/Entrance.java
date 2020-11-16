package zxh.demo.tw.assignment.conference;

import static java.util.Objects.*;

import zxh.demo.tw.assignment.conference.app.ArrangeConferenceUseCase;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public class Entrance {
    private static final String TALK_DESCRIPTION_RESOURCE_NAME = "TalkDescriptions";

    public static void main(String[] args) {
        URL resource = Entrance.class.getClassLoader().getResource(TALK_DESCRIPTION_RESOURCE_NAME);
        if (isNull(resource)) {
            System.out.println("Read talk description resource failed, please check the resource name.");
            System.exit(1);
        }

        try {
            new ArrangeConferenceUseCase().arrangeConferenceFrom(Path.of(resource.toURI()), System.out);
        } catch (Exception e) {
            System.out.printf("Failed to arrange conference. %s\n", e.getMessage());
            System.exit(1);
        }
    }
}
