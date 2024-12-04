package de.moges.test.jenkins.domain;

import static de.moges.test.jenkins.domain.SpecialityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import de.moges.test.jenkins.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SpecialityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Speciality.class);
        Speciality speciality1 = getSpecialitySample1();
        Speciality speciality2 = new Speciality();
        assertThat(speciality1).isNotEqualTo(speciality2);

        speciality2.setId(speciality1.getId());
        assertThat(speciality1).isEqualTo(speciality2);

        speciality2 = getSpecialitySample2();
        assertThat(speciality1).isNotEqualTo(speciality2);
    }
}
