package com.doncel.pruebaUsuarios.infraestructure.security;

import com.doncel.pruebaUsuarios.application.ports.input.UsuarioServicePort;
import com.doncel.pruebaUsuarios.application.ports.output.TokenGeneratorPort;
import com.doncel.pruebaUsuarios.domain.model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Oliver & Ragnar
 */
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final TokenGeneratorPort tokenGenerator;
    private final UserDetailServiceImpl userDetailsService;
    private final UsuarioServicePort usuarioServicePort;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // Check if the header starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extract token
            username = tokenGenerator.extractUsername(token); // Extract username from token
        }

        // If the token is valid and no authentication is set in the context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validate token and set authentication
            if (tokenGenerator.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        actualizarUsuario(username);
        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private void actualizarUsuario(String email){
        Usuario usuarioSaved = usuarioServicePort.findOneByEmail(email);
        usuarioSaved.setLastLogin(LocalDateTime.now());
        usuarioServicePort.update(usuarioSaved.getId(), usuarioSaved);
    }
}
