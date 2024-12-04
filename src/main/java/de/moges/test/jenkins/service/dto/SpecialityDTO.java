package de.moges.test.jenkins.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link de.moges.test.jenkins.domain.Speciality} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SpecialityDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String label;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpecialityDTO)) {
            return false;
        }

        SpecialityDTO specialityDTO = (SpecialityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, specialityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SpecialityDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", label='" + getLabel() + "'" +
            "}";
    }
}
