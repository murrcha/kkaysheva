package ru.job4j.parser.dao;

/**
 * VacancyDaoException
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class VacancyDaoException extends Exception {

    public VacancyDaoException() {
        super();
    }

    public VacancyDaoException(String message) {
        super(message);
    }

    public VacancyDaoException(Throwable cause) {
        super(cause);
    }

    public VacancyDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
