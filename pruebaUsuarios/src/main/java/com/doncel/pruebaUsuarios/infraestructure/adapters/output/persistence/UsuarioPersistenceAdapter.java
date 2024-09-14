package com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence;

import com.doncel.pruebaUsuarios.application.ports.output.UsuarioPersistencePort;
import com.doncel.pruebaUsuarios.common.UseCase;
import com.doncel.pruebaUsuarios.domain.model.Usuario;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper.UsuarioResponseMapper;
import com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.mapper.UsuarioPersistenceMapper;
import com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.repository.UsuarioRepository;
import com.doncel.pruebaUsuarios.infraestructure.security.TokenGeneratorImpl;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
@UseCase
@RequiredArgsConstructor
public class UsuarioPersistenceAdapter implements UsuarioPersistencePort {

    private final UsuarioRepository repository;
    private final UsuarioPersistenceMapper persistenceMapper;
    private final UsuarioResponseMapper responseMapper;
    private final TokenGeneratorImpl tokenGenerator;
    @Override
    public Optional<Usuario> findById(UUID id) {
        return repository.findById(id)
                .map(persistenceMapper::toDomain);
    }

    @Override
    public long countByEmail(String email) {
        return repository.countByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setToken(tokenGenerator.generateToken(usuario.getEmail()));
        return persistenceMapper.toDomain(repository.save(persistenceMapper.toEntity(usuario)));
    }

    @Override
    public Optional<Usuario> findOneByEmail(String email) {
        return repository.findOneByEmail(email)
                .map(persistenceMapper::toDomain);
    }
}
