package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
/**
 * Teacher Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class TeacherTest {
    /**
     * Test learn
     */
    @Test
    public void whenTeacherLearnStudentThenReturnProfession() {
        Teacher teacherIvanov = new Teacher();
        Student studentPetrov = new Student();
        assertThat(teacherIvanov.learn(studentPetrov), instanceOf(Profession.class));
    }
}
