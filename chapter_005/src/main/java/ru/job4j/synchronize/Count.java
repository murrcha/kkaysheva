package ru.job4j.synchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Count
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class Count {

    @GuardedBy("this")
    private int value;

    /**
     * Method increment - add 1 to value
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Method get
     * @return value
     */
    public synchronized int get() {
        return this.value;
    }
}
