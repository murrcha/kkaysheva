package ru.job4j.professions;
/**
 * Engineer - профессия Инженер
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Engineer extends Profession {

    /**
     * Консруктор - инициализация поля name
     */
    public Engineer() {
        this.name = "Engineer";
    }
    /**
     * Method build - строить дом
     * @param design - дом
     * @return дом
     */
    public House build(Design design) {
        return new House();
    }
}
