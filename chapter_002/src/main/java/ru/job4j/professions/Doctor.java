package ru.job4j.professions;
/**
 * Doctor - профессия Доктор
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class Doctor extends Profession {

    /**
     * Консруктор - инициализация поля name
     */
    public Doctor() {
        this.name = "Doctor";
    }
    /**
     * Method heal - лечить пациента
     * @param patient - пациент
     * @return диагноз
     */
    public Diagnosis heal(Patient patient) {
        return new Diagnosis();
    }
}
