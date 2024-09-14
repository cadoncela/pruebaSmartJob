package com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.repository;

import com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    long countByEmail(String email);
    Optional<UsuarioEntity> findOneByEmail(String email);
}
