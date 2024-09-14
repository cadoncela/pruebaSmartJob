package com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest;

import com.doncel.pruebaUsuarios.application.ports.input.UsuarioServicePort;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper.UsuarioRequestMapper;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper.UsuarioResponseMapper;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.request.UsuarioCreateRequest;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.response.UsuarioResponse;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.request.AuthCredentials;
import com.doncel.pruebaUsuarios.application.ports.output.TokenGeneratorPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Oliver & Ragnar
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class PublicController {
    private final UsuarioServicePort servicePort;
    private final UsuarioRequestMapper requestMapper;
    private final UsuarioResponseMapper responseMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenGeneratorPort jwtService;


    @PostMapping("/v1/api/createUser")
    public ResponseEntity<UsuarioResponse> save(@Valid @RequestBody UsuarioCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseMapper.toResponse(
                        servicePort.save(requestMapper.toDomain(request))
                ));
    }

    @GetMapping("/v1/sumar/{param}/{param2}")
    public ResponseEntity<Integer> sumarNumerosGet(@PathVariable int param, @PathVariable int param2) {
        int sum = param+param2;
        System.out.println("Resultado suma : " + sum);
        return new ResponseEntity<Integer>(sum, HttpStatus.OK);
    }

    @PostMapping("/v1/api/generateToken")
    public ResponseEntity<UsuarioResponse> generateToken(@RequestBody AuthCredentials authCredentials){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentials.getEmail(), authCredentials.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMapper.toResponse(
                            servicePort.updateToken(authCredentials.getEmail())));
            //return jwtService.generateToken(authCredentials.getEmail());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
