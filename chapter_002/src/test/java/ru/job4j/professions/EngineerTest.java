package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
/**
 * Engineer Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class EngineerTest {
    /**
     * Test build
     */
    @Test
    public void whenEngineerBuildDesignThenReturnHouse() {
        Engineer engineerBuilder = new Engineer();
        Design designHouse = new Design();
        assertThat(engineerBuilder.build(designHouse), instanceOf(House.class));
    }
}
