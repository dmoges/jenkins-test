package de.moges.test.jenkins.service;

import de.moges.test.jenkins.service.dto.ProfessorDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link de.moges.test.jenkins.domain.Professor}.
 */
public interface ProfessorService {
    /**
     * Save a professor.
     *
     * @param professorDTO the entity to save.
     * @return the persisted entity.
     */
    ProfessorDTO save(ProfessorDTO professorDTO);

    /**
     * Updates a professor.
     *
     * @param professorDTO the entity to update.
     * @return the persisted entity.
     */
    ProfessorDTO update(ProfessorDTO professorDTO);

    /**
     * Partially updates a professor.
     *
     * @param professorDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProfessorDTO> partialUpdate(ProfessorDTO professorDTO);

    /**
     * Get all the professors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfessorDTO> findAll(Pageable pageable);

    /**
     * Get all the professors with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfessorDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" professor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfessorDTO> findOne(Long id);

    /**
     * Delete the "id" professor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
