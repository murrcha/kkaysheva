package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * CyclingFinder Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class CyclingFinderTest {

    private Node first;
    private Node two;
    private Node third;
    private Node four;
    private CyclingFinder finder;

    @Before
    public void beforeTest() {
        first = new Node(1);
        two = new Node(2);
        third = new Node(3);
        four = new Node(4);
        finder = new CyclingFinder();
    }

    /**
     * Test hasCycle
     */
    @Test
    public void whenHasCyclingThenReturnTrue() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(finder.hasCycle(first), is(true));
    }

    /**
     * Test hasCycle
     */
    @Test
    public void whenHasNotCyclingThenReturnFalse() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(finder.hasCycle(first), is(false));
    }
}