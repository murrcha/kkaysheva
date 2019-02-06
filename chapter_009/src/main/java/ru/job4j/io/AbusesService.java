package ru.job4j.io;

import java.io.*;


/**
 * AbusesService
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class AbusesService {

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))
        ) {
            reader.lines()
                    .map(s -> clearAbuses(s, abuse))
                    .forEach(s -> {
                        try {
                            writer.write(s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private String clearAbuses(String line, String[] abuse) {
        String result = line;
        StringBuilder builder = new StringBuilder(result);
        for (String string : abuse) {
            while (result.contains(string)) {
                int start = result.indexOf(string);
                int end = start + string.length();
                builder.delete(start, end);
                result = builder.toString();
            }
        }
        return result;
    }
}
