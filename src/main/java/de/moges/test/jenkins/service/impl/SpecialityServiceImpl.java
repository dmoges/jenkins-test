package de.moges.test.jenkins.service.impl;

import de.moges.test.jenkins.domain.Speciality;
import de.moges.test.jenkins.repository.SpecialityRepository;
import de.moges.test.jenkins.service.SpecialityService;
import de.moges.test.jenkins.service.dto.SpecialityDTO;
import de.moges.test.jenkins.service.mapper.SpecialityMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link de.moges.test.jenkins.domain.Speciality}.
 */
@Service
@Transactional
public class SpecialityServiceImpl implements SpecialityService {

    private static final Logger LOG = LoggerFactory.getLogger(SpecialityServiceImpl.class);

    private final SpecialityRepository specialityRepository;

    private final SpecialityMapper specialityMapper;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository, SpecialityMapper specialityMapper) {
        this.specialityRepository = specialityRepository;
        this.specialityMapper = specialityMapper;
    }

    @Override
    public SpecialityDTO save(SpecialityDTO specialityDTO) {
        LOG.debug("Request to save Speciality : {}", specialityDTO);
        Speciality speciality = specialityMapper.toEntity(specialityDTO);
        speciality = specialityRepository.save(speciality);
        return specialityMapper.toDto(speciality);
    }

    @Override
    public SpecialityDTO update(SpecialityDTO specialityDTO) {
        LOG.debug("Request to update Speciality : {}", specialityDTO);
        Speciality speciality = specialityMapper.toEntity(specialityDTO);
        speciality = specialityRepository.save(speciality);
        return specialityMapper.toDto(speciality);
    }

    @Override
    public Optional<SpecialityDTO> partialUpdate(SpecialityDTO specialityDTO) {
        LOG.debug("Request to partially update Speciality : {}", specialityDTO);

        return specialityRepository
            .findById(specialityDTO.getId())
            .map(existingSpeciality -> {
                specialityMapper.partialUpdate(existingSpeciality, specialityDTO);

                return existingSpeciality;
            })
            .map(specialityRepository::save)
            .map(specialityMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SpecialityDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Specialities");
        return specialityRepository.findAll(pageable).map(specialityMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SpecialityDTO> findOne(Long id) {
        LOG.debug("Request to get Speciality : {}", id);
        return specialityRepository.findById(id).map(specialityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Speciality : {}", id);
        specialityRepository.deleteById(id);
    }
}
