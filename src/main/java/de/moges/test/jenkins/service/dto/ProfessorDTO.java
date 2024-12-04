package de.moges.test.jenkins.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link de.moges.test.jenkins.domain.Professor} entity.
 */
@Schema(description = "Professeur.")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProfessorDTO implements Serializable {

    private Long id;

    @NotNull
    @Schema(description = "The firstName attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String telephone;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+@\\.[^@\\s]+$")
    private String email;

    @NotNull
    private LocalDate hireDate;

    private SpecialityDTO speciality;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public SpecialityDTO getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityDTO speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfessorDTO)) {
            return false;
        }

        ProfessorDTO professorDTO = (ProfessorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, professorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfessorDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", speciality=" + getSpeciality() +
            "}";
    }
}
