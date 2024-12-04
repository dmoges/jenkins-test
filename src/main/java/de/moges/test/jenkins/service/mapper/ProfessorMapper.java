package de.moges.test.jenkins.service.mapper;

import de.moges.test.jenkins.domain.Professor;
import de.moges.test.jenkins.domain.Speciality;
import de.moges.test.jenkins.service.dto.ProfessorDTO;
import de.moges.test.jenkins.service.dto.SpecialityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Professor} and its DTO {@link ProfessorDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfessorMapper extends EntityMapper<ProfessorDTO, Professor> {
    @Mapping(target = "speciality", source = "speciality", qualifiedByName = "specialityLabel")
    ProfessorDTO toDto(Professor s);

    @Named("specialityLabel")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "label", source = "label")
    SpecialityDTO toDtoSpecialityLabel(Speciality speciality);
}
