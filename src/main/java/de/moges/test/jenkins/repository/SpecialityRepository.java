package de.moges.test.jenkins.repository;

import de.moges.test.jenkins.domain.Speciality;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Speciality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {}
