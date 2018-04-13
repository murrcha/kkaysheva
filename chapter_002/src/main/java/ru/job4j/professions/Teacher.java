package ru.job4j.professions;
/**
 * Teacher - профессия Учитель
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Teacher extends Profession {
    /**
     * Консруктор - инициализация поля name
     */
    public Teacher() {
        this.name = "Teacher";
    }
    /**
     * Method lean - учить
     * @param student - студент
     * @return специалист (профессия)
     */
    public Profession learn(Student student) {
        return new Profession();
    }
}
