package de.moges.test.jenkins.service.mapper;

import de.moges.test.jenkins.domain.Speciality;
import de.moges.test.jenkins.service.dto.SpecialityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Speciality} and its DTO {@link SpecialityDTO}.
 */
@Mapper(componentModel = "spring")
public interface SpecialityMapper extends EntityMapper<SpecialityDTO, Speciality> {}
