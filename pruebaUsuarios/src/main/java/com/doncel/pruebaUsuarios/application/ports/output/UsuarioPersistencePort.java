package com.doncel.pruebaUsuarios.application.ports.output;

import com.doncel.pruebaUsuarios.domain.model.Usuario;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.response.UsuarioResponse;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
public interface UsuarioPersistencePort {
    Optional<Usuario> findById(UUID id);
    long countByEmail(String email);
    Usuario save(Usuario usuario);
    Optional<Usuario> findOneByEmail(String email);
}