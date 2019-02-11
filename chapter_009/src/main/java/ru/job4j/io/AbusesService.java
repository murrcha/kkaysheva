package ru.job4j.io;

import java.io.*;
import java.util.Arrays;


/**
 * AbusesService
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class AbusesService {

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             PrintStream writer = new PrintStream(out)
        ) {
            reader.lines()
                    .map(s -> Arrays.stream(abuse)
                            .reduce(s, (s1, s2) -> s1.replaceAll(s2, ""))
                    ).forEach(writer::print);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
