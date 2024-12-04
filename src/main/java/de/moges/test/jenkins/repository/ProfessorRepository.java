package de.moges.test.jenkins.repository;

import de.moges.test.jenkins.domain.Professor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Professor entity.
 */
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    default Optional<Professor> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Professor> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Professor> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select professor from Professor professor left join fetch professor.speciality",
        countQuery = "select count(professor) from Professor professor"
    )
    Page<Professor> findAllWithToOneRelationships(Pageable pageable);

    @Query("select professor from Professor professor left join fetch professor.speciality")
    List<Professor> findAllWithToOneRelationships();

    @Query("select professor from Professor professor left join fetch professor.speciality where professor.id =:id")
    Optional<Professor> findOneWithToOneRelationships(@Param("id") Long id);
}
