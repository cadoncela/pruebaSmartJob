package com.doncel.pruebaUsuarios.domain.model;

import lombok.*;

/**
 * @author Oliver & Ragnar
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    private String number;
    private String cityCode;
    private String countryCode;
}
