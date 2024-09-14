package com.doncel.pruebaUsuarios.infraestructure.security;

import com.doncel.pruebaUsuarios.application.ports.input.UsuarioServicePort;
import com.doncel.pruebaUsuarios.domain.model.Usuario;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper.UsuarioRequestMapper;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper.UsuarioResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Oliver & Ragnar
 */
@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioServicePort servicePort;
    private final UsuarioRequestMapper requestMapper;
    private final UsuarioResponseMapper responseMapper;

    // Implementación del método que se encarga de devolver los detalles del usuario
    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = servicePort.findOneByEmail(email);
        return new UserDetailsImpl(usuario);
    }
}
