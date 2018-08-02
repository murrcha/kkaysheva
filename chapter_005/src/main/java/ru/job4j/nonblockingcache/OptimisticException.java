package ru.job4j.nonblockingcache;

/**
 * OptimisticException
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}
