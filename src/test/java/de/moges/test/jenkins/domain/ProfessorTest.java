package de.moges.test.jenkins.domain;

import static de.moges.test.jenkins.domain.ProfessorTestSamples.*;
import static de.moges.test.jenkins.domain.SpecialityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import de.moges.test.jenkins.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProfessorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Professor.class);
        Professor professor1 = getProfessorSample1();
        Professor professor2 = new Professor();
        assertThat(professor1).isNotEqualTo(professor2);

        professor2.setId(professor1.getId());
        assertThat(professor1).isEqualTo(professor2);

        professor2 = getProfessorSample2();
        assertThat(professor1).isNotEqualTo(professor2);
    }

    @Test
    void specialityTest() {
        Professor professor = getProfessorRandomSampleGenerator();
        Speciality specialityBack = getSpecialityRandomSampleGenerator();

        professor.setSpeciality(specialityBack);
        assertThat(professor.getSpeciality()).isEqualTo(specialityBack);

        professor.speciality(null);
        assertThat(professor.getSpeciality()).isNull();
    }
}
