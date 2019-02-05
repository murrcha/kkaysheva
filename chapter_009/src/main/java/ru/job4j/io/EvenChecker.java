package ru.job4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * EvenChecker
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 02.2019
 */
public class EvenChecker {

    public boolean isEvenNumber(InputStream in) {
        boolean result = false;
        try (Reader reader = new InputStreamReader(in)) {
            int number;
            while ((number = reader.read()) != -1) {
                if (isEven(number)) {
                    result = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean isEven(int number) {
        return (number % 2) == 0 && number != 0;
    }
}
