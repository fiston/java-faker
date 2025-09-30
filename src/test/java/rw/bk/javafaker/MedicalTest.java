package rw.bk.javafaker;

import org.junit.jupiter.api.Test;

import static rw.bk.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static org.hamcrest.MatcherAssert.assertThat;

public class MedicalTest extends AbstractFakerTest {

    @Test
    public void testMedicineName() {
        assertThat(faker.medical().medicineName(), isStringWithContents());
    }

    @Test
    public void testDiseaseName() {
        assertThat(faker.medical().diseaseName(), isStringWithContents());
    }

    @Test
    public void testHospitalName() {
        assertThat(faker.medical().hospitalName(), isStringWithContents());
    }

    @Test
    public void testSymptom() {
        assertThat(faker.medical().symptoms(), isStringWithContents());
    }


}