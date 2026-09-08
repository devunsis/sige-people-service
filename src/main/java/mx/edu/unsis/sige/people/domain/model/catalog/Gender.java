package mx.edu.unsis.sige.people.domain.model.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Modelo de dominio puro del catálogo de géneros
 * (MALE, FEMALE, NON_BINARY, UNDISCLOSED, ...). Sin anotaciones de framework.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gender {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private boolean active;
}
