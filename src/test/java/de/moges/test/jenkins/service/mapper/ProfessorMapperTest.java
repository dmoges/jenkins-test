package de.moges.test.jenkins.service.mapper;

import static de.moges.test.jenkins.domain.ProfessorAsserts.*;
import static de.moges.test.jenkins.domain.ProfessorTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfessorMapperTest {

    private ProfessorMapper professorMapper;

    @BeforeEach
    void setUp() {
        professorMapper = new ProfessorMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getProfessorSample1();
        var actual = professorMapper.toEntity(professorMapper.toDto(expected));
        assertProfessorAllPropertiesEquals(expected, actual);
    }
}
