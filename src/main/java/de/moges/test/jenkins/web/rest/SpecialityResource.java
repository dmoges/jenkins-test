package de.moges.test.jenkins.web.rest;

import de.moges.test.jenkins.repository.SpecialityRepository;
import de.moges.test.jenkins.service.SpecialityService;
import de.moges.test.jenkins.service.dto.SpecialityDTO;
import de.moges.test.jenkins.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link de.moges.test.jenkins.domain.Speciality}.
 */
@RestController
@RequestMapping("/api/specialities")
public class SpecialityResource {

    private static final Logger LOG = LoggerFactory.getLogger(SpecialityResource.class);

    private static final String ENTITY_NAME = "speciality";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SpecialityService specialityService;

    private final SpecialityRepository specialityRepository;

    public SpecialityResource(SpecialityService specialityService, SpecialityRepository specialityRepository) {
        this.specialityService = specialityService;
        this.specialityRepository = specialityRepository;
    }

    /**
     * {@code POST  /specialities} : Create a new speciality.
     *
     * @param specialityDTO the specialityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new specialityDTO, or with status {@code 400 (Bad Request)} if the speciality has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SpecialityDTO> createSpeciality(@Valid @RequestBody SpecialityDTO specialityDTO) throws URISyntaxException {
        LOG.debug("REST request to save Speciality : {}", specialityDTO);
        if (specialityDTO.getId() != null) {
            throw new BadRequestAlertException("A new speciality cannot already have an ID", ENTITY_NAME, "idexists");
        }
        specialityDTO = specialityService.save(specialityDTO);
        return ResponseEntity.created(new URI("/api/specialities/" + specialityDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, specialityDTO.getId().toString()))
            .body(specialityDTO);
    }

    /**
     * {@code PUT  /specialities/:id} : Updates an existing speciality.
     *
     * @param id the id of the specialityDTO to save.
     * @param specialityDTO the specialityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specialityDTO,
     * or with status {@code 400 (Bad Request)} if the specialityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the specialityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SpecialityDTO> updateSpeciality(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SpecialityDTO specialityDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Speciality : {}, {}", id, specialityDTO);
        if (specialityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, specialityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!specialityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        specialityDTO = specialityService.update(specialityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialityDTO.getId().toString()))
            .body(specialityDTO);
    }

    /**
     * {@code PATCH  /specialities/:id} : Partial updates given fields of an existing speciality, field will ignore if it is null
     *
     * @param id the id of the specialityDTO to save.
     * @param specialityDTO the specialityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated specialityDTO,
     * or with status {@code 400 (Bad Request)} if the specialityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the specialityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the specialityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SpecialityDTO> partialUpdateSpeciality(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SpecialityDTO specialityDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Speciality partially : {}, {}", id, specialityDTO);
        if (specialityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, specialityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!specialityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SpecialityDTO> result = specialityService.partialUpdate(specialityDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, specialityDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /specialities} : get all the specialities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of specialities in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SpecialityDTO>> getAllSpecialities(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        LOG.debug("REST request to get a page of Specialities");
        Page<SpecialityDTO> page = specialityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /specialities/:id} : get the "id" speciality.
     *
     * @param id the id of the specialityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the specialityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> getSpeciality(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Speciality : {}", id);
        Optional<SpecialityDTO> specialityDTO = specialityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(specialityDTO);
    }

    /**
     * {@code DELETE  /specialities/:id} : delete the "id" speciality.
     *
     * @param id the id of the specialityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Speciality : {}", id);
        specialityService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
