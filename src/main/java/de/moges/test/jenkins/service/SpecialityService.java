package de.moges.test.jenkins.service;

import de.moges.test.jenkins.service.dto.SpecialityDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link de.moges.test.jenkins.domain.Speciality}.
 */
public interface SpecialityService {
    /**
     * Save a speciality.
     *
     * @param specialityDTO the entity to save.
     * @return the persisted entity.
     */
    SpecialityDTO save(SpecialityDTO specialityDTO);

    /**
     * Updates a speciality.
     *
     * @param specialityDTO the entity to update.
     * @return the persisted entity.
     */
    SpecialityDTO update(SpecialityDTO specialityDTO);

    /**
     * Partially updates a speciality.
     *
     * @param specialityDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SpecialityDTO> partialUpdate(SpecialityDTO specialityDTO);

    /**
     * Get all the specialities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SpecialityDTO> findAll(Pageable pageable);

    /**
     * Get the "id" speciality.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SpecialityDTO> findOne(Long id);

    /**
     * Delete the "id" speciality.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
