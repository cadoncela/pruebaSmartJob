package com.doncel.pruebaUsuarios.application.ports.input;

import com.doncel.pruebaUsuarios.domain.model.Usuario;

import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
public interface UsuarioServicePort {
    Usuario findById(UUID id);
    Usuario save(Usuario usuario);
    Usuario update(UUID id, Usuario usuario);
    long countByEmail(String email);
    Usuario findOneByEmail(String email);
    Usuario updateToken(String email);
}
