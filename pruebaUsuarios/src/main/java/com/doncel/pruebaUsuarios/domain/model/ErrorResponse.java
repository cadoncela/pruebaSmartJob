package com.doncel.pruebaUsuarios.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Oliver & Ragnar
 */
@Builder
@Getter
@Setter
public class ErrorResponse {
    private String mensaje;
}
