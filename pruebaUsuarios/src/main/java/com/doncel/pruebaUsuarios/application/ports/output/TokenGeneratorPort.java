package com.doncel.pruebaUsuarios.application.ports.output;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Oliver & Ragnar
 */
public interface TokenGeneratorPort {
    String generateToken(String userName);
    String extractUsername(String token);
    Boolean validateToken(String token, UserDetails userDetails);

}
