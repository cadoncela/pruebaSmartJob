package com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Oliver & Ragnar
 */
@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @JsonIgnoreProperties(value={"phones", "hibernateLazyInitializer", "handler"}, allowSetters =true )
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;
}
