package com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Oliver & Ragnar
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthCredentials {
    private String email;
    private String password;

}
