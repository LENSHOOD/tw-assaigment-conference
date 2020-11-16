package zxh.demo.tw.assignment.conference.app;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

class ArrangeConferenceUseCaseTest {
    @Test
    public void should_build_conference_then_output_to_stream() throws URISyntaxException {
        // given
        Path input = Paths.get(requireNonNull(getClass().getClassLoader().getResource("textInput")).toURI());
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // when
        new ArrangeConferenceUseCase().arrangeConferenceFrom(input, os);

        // then
        String result = os.toString();
        assertNotNull(result);
        assertThat(result.split("\n").length, is(26));
    }
}