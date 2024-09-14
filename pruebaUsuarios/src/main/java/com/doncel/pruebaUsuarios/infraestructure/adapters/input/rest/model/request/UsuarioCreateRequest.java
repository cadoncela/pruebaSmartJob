package com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.*;
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
public class UsuarioCreateRequest {

    @JsonIgnore
    private UUID id;

    @NotBlank(message = "El campo name no puede ser vacio o nulo.")
    private String name;

    @NotBlank(message = "El campo email no puede ser vacio o nulo.")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Formato de email incorrecto")
    private String email;

    @NotBlank(message = "El campo password no puede ser vacio o nulo.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Formato incorrecto. Incluir al menos una mayúscula, al menos una minúscula, al menos un caracter especial y al menos un número. Logitud minima de 8 carácteres")
    private String password;

    @NotNull(message = "El campo phone no puede ser vacio")
    private List<Phone> phones;

    @JsonIgnore
    private LocalDateTime created;
    @JsonIgnore
    private LocalDateTime modified;
    @JsonIgnore
    private LocalDateTime lastLogin;
    @JsonIgnore
    private String token;
    @JsonIgnore
    private Boolean isActive;
}
