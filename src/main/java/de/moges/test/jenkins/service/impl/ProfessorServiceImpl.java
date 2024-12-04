package de.moges.test.jenkins.service.impl;

import de.moges.test.jenkins.domain.Professor;
import de.moges.test.jenkins.repository.ProfessorRepository;
import de.moges.test.jenkins.service.ProfessorService;
import de.moges.test.jenkins.service.dto.ProfessorDTO;
import de.moges.test.jenkins.service.mapper.ProfessorMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link de.moges.test.jenkins.domain.Professor}.
 */
@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    private final ProfessorRepository professorRepository;

    private final ProfessorMapper professorMapper;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Override
    public ProfessorDTO save(ProfessorDTO professorDTO) {
        LOG.debug("Request to save Professor : {}", professorDTO);
        Professor professor = professorMapper.toEntity(professorDTO);
        professor = professorRepository.save(professor);
        return professorMapper.toDto(professor);
    }

    @Override
    public ProfessorDTO update(ProfessorDTO professorDTO) {
        LOG.debug("Request to update Professor : {}", professorDTO);
        Professor professor = professorMapper.toEntity(professorDTO);
        professor = professorRepository.save(professor);
        return professorMapper.toDto(professor);
    }

    @Override
    public Optional<ProfessorDTO> partialUpdate(ProfessorDTO professorDTO) {
        LOG.debug("Request to partially update Professor : {}", professorDTO);

        return professorRepository
            .findById(professorDTO.getId())
            .map(existingProfessor -> {
                professorMapper.partialUpdate(existingProfessor, professorDTO);

                return existingProfessor;
            })
            .map(professorRepository::save)
            .map(professorMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProfessorDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Professors");
        return professorRepository.findAll(pageable).map(professorMapper::toDto);
    }

    public Page<ProfessorDTO> findAllWithEagerRelationships(Pageable pageable) {
        return professorRepository.findAllWithEagerRelationships(pageable).map(professorMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfessorDTO> findOne(Long id) {
        LOG.debug("Request to get Professor : {}", id);
        return professorRepository.findOneWithEagerRelationships(id).map(professorMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Professor : {}", id);
        professorRepository.deleteById(id);
    }
}
