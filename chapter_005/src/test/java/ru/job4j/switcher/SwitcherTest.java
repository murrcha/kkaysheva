package ru.job4j.switcher;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SwitcherTest {

    private static final String FIRST_DIGIT = "1111111111";
    private static final String SECOND_DIGIT = "2222222222";

    /**
     * Test switch threads
     */
    @Test
    public void whenSwitchDigitThenSwitchThread() {
        Switcher switcher = new Switcher();
        SwitcherThread threadOne = new SwitcherThread(switcher, 1);
        SwitcherThread threadTwo = new SwitcherThread(switcher, 2);
        threadOne.start();
        threadTwo.start();
        try {
            Thread.sleep(100);
            threadOne.interrupt();
            threadTwo.interrupt();
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        String string = switcher.getString();
        String partOne = string.substring(0, 10);
        String partTwo = string.substring(10, 20);
        String partThree = string.substring(20, 30);
        assertThat(partOne, is(FIRST_DIGIT));
        assertThat(partTwo, is(SECOND_DIGIT));
        assertThat(partThree, is(FIRST_DIGIT));
    }
}