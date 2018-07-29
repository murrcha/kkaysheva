package ru.job4j.jmm;

/**
 * Counter - demo class for jmm task
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Counter {

    private Integer count;

    public Counter() {
        count = 0;
    }

    /**
     * Method getCount
     * @return
     */
    public Integer getCount() {
        return this.count;
    }

    /**
     * Method setCount
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}
