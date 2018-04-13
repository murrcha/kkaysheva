package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
/**
 * Doctor Test
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @version $Id$
 * @since 0.1
 */
public class DoctorTest {
    /**
     * Test heal
     */
    @Test
    public void whenDoctorHealPatientThenReturnDiagnosis() {
        Doctor doctorPepper = new Doctor();
        Patient patientIvanov = new Patient();
        assertThat(doctorPepper.heal(patientIvanov), instanceOf(Diagnosis.class));
    }
}
