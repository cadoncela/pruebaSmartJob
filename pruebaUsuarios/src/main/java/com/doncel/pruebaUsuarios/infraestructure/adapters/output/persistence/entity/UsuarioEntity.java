package com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modified;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    private String token;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @JsonIgnoreProperties(value={"usuario", "hibernateLazyInitializer","handler"}, allowSetters =true)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

}
