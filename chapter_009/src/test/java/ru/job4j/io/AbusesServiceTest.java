package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * AbusesServiceTest
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class AbusesServiceTest {

    @Test
    public void dropAbusesDirtyInput() {
        String input = "Ну заяц, что скажешь, косой";
        String[] abuse = {"заяц", "косой"};
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        AbusesService service = new AbusesService();
        service.dropAbuses(in, out, abuse);
        assertThat(out.toString(), is("Ну , что скажешь, "));
    }

    @Test
    public void dropAbusesCleanInput() {
        String input = "Ну голубчик, что скажешь, догорой";
        String[] abuse = {"заяц", "косой"};
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        AbusesService service = new AbusesService();
        service.dropAbuses(in, out, abuse);
        assertThat(out.toString(), is("Ну голубчик, что скажешь, догорой"));
    }
}