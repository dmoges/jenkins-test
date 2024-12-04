package de.moges.test.jenkins.service.mapper;

import static de.moges.test.jenkins.domain.SpecialityAsserts.*;
import static de.moges.test.jenkins.domain.SpecialityTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialityMapperTest {

    private SpecialityMapper specialityMapper;

    @BeforeEach
    void setUp() {
        specialityMapper = new SpecialityMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getSpecialitySample1();
        var actual = specialityMapper.toEntity(specialityMapper.toDto(expected));
        assertSpecialityAllPropertiesEquals(expected, actual);
    }
}
