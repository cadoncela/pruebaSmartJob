package com.doncel.pruebaUsuarios.application.service;

import com.doncel.pruebaUsuarios.application.ports.input.UsuarioServicePort;
import com.doncel.pruebaUsuarios.application.ports.output.TokenGeneratorPort;
import com.doncel.pruebaUsuarios.application.ports.output.UsuarioPersistencePort;
import com.doncel.pruebaUsuarios.common.UseCase;
import com.doncel.pruebaUsuarios.domain.exception.EmailException;
import com.doncel.pruebaUsuarios.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
@RequiredArgsConstructor
@UseCase
public class UsuarioService implements UsuarioServicePort {

    private final UsuarioPersistencePort persistencePort;
    private final TokenGeneratorPort tokenGeneratorPort;

    @Override
    public Usuario findById(UUID id) {
        return persistencePort.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Usuario save(Usuario usuario) {
        int countEmail=(int)persistencePort.countByEmail(usuario.getEmail());
        if (countEmail > 0) {
            throw new EmailException();
        }
        String passEncode = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usuario.setModified(LocalDateTime.now());
        usuario.setCreated(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        usuario.setIsActive(Boolean.TRUE);
        usuario.setPassword(passEncode);
        usuario.setToken(generateToken(usuario.getEmail()));
        return persistencePort.save(usuario);
    }

    @Override
    public Usuario update(UUID id, Usuario usuario) {
        return persistencePort.findById(id)
                .map(usuarioSaved -> {
                    usuarioSaved.setName(usuario.getName());
                    usuarioSaved.setEmail(usuario.getEmail());
                    usuarioSaved.setPhones(usuario.getPhones());
                    usuarioSaved.setIsActive(usuario.getIsActive());
                    usuarioSaved.setModified(LocalDateTime.now());
                    usuarioSaved.setToken(generateToken(usuario.getEmail()));
                    return persistencePort.save(usuarioSaved);
                })
               .orElseThrow();
    }

    @Override
    public long countByEmail(String email) {
        return persistencePort.countByEmail(email);
    }

    @Override
    public Usuario findOneByEmail(String email) {
        return persistencePort.findOneByEmail(email).orElseThrow(RuntimeException::new);
    }

    @Override
    public Usuario updateToken(String email) {
        Usuario usuario = persistencePort.findOneByEmail(email).orElseThrow(RuntimeException::new);
        usuario.setToken(generateToken(email));
        return update(usuario.getId(), usuario);
    }

    private String generateToken(String email) {
        return tokenGeneratorPort.generateToken(email).substring(7);
    }
}
