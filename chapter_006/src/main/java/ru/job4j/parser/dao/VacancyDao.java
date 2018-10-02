package ru.job4j.parser.dao;

import ru.job4j.parser.pojo.Vacancy;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * VacancyDao
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public interface VacancyDao {

    /**
     * Method insert - insert new vacancy
     * @param vacancy vacancy
     */
    void insert(Vacancy vacancy) throws VacancyDaoException;

    /**
     * Method insertSet - insert set vacancies
     * @param vacancySet set
     */
    void insertSet(Set<Vacancy> vacancySet) throws VacancyDaoException;

    /**
     * Method delete - delete vacancy
     * @param vacancy vacancy
     */
    void delete(Vacancy vacancy) throws VacancyDaoException;

    /**
     * Method deleteAll - deleteAll vacancies
     */
    void deleteAll() throws VacancyDaoException;

    /**
     * Method getById - get vacancy by id
     * @param id id
     * @return vacancy
     */
    Vacancy getById(int id) throws VacancyDaoException;

    /**
     * Method getAll - get all vacancies
     * @return vacancy list
     */
    List<Vacancy> getAll() throws VacancyDaoException;

    /**
     * Method insertLastUpdateDate
     * @param date date
     */
    void insertLastUpdateDate(long date) throws VacancyDaoException;

    /**
     * Method updateLastUpdateDate
     * @param date date
     */
    void updateLastUpdateDate(long date) throws VacancyDaoException;

    /**
     * Method getLastUpdateDate
     */
    Date getLastUpdateDate() throws VacancyDaoException;
}
