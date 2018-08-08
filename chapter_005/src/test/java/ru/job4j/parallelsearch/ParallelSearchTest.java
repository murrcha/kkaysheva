package ru.job4j.parallelsearch;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ParallelSearch Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ParallelSearchTest {

    private static final String ROOT = "./";
    private static final String TEXT = "ParallelSearch";
    private final List<String> extensions = new ArrayList<>();
    private ParallelSearch parallelSearch;

    @Before
    public void beforeTest() {
        extensions.add("java");
        parallelSearch = new ParallelSearch(ROOT, TEXT, extensions);
    }

    /**
     * Test parallel search
     */
    @Test
    public void whenRunParallelSearchThenReturnResult() {
        parallelSearch.init();
        List<String> result = parallelSearch.result();
        assertThat(result.size(), is(2));
        System.out.println(result.get(0));
        System.out.println(result.get(1));
    }
}