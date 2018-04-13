package ru.job4j.professions;

import org.junit.Test;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.is;
/**
 * Profession Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class ProfessionTest {
    /**
     * Test getName
     */
    @Test
    public void whenProfessionIsTeacherThenNameProfessionIsTeacher() {
        Profession teacher = new Teacher();
        String result = teacher.getName();
        String expected = "Teacher";
        assertThat(result, is(expected));
    }
    /**
     * Test getName
     */
    @Test
    public void whenProfessionIsEngineerThenNameProfessionIsEngineer() {
        Profession engineer = new Engineer();
        String result = engineer.getName();
        String expected = "Engineer";
        assertThat(result, is(expected));
    }
    /**
     * Test getName
     */
    @Test
    public void whenProfessionIsDoctorThenNameProfessionIsDoctor() {
        Profession doctor = new Doctor();
        String result = doctor.getName();
        String expected = "Doctor";
        assertThat(result, is(expected));
    }
}
