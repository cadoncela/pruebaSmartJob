package com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.response;

import com.doncel.pruebaUsuarios.domain.model.Phone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;

    /*
    Le agregué el campo 'phones' para hacer pruebas.
    Si deseas que aparezca el dato de los teléfonos en el response por favor eliminar la anotación @JsonIgnore del campo.
     */
    @JsonIgnore
    private List<Phone> phones;
}
