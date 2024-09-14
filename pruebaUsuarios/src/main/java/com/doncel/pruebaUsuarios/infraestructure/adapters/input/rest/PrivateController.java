package com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest;

import com.doncel.pruebaUsuarios.application.ports.input.UsuarioServicePort;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper.UsuarioRequestMapper;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper.UsuarioResponseMapper;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Oliver & Ragnar
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class PrivateController {
    private final UsuarioServicePort servicePort;
    private final UsuarioRequestMapper requestMapper;
    private final UsuarioResponseMapper responseMapper;

    @GetMapping("/v1/api/{id}")
    public UsuarioResponse findById(@PathVariable UUID id){
        return responseMapper.toResponse(servicePort.findById(id));
    }

    @GetMapping("/v1/sumar/{param}/{param2}")
    public ResponseEntity<Integer> sumarNumerosGet(@PathVariable int param, @PathVariable int param2) {
        int sum = param+param2;
        System.out.println("Resultado suma : " + sum);
        return new ResponseEntity<Integer>(sum, HttpStatus.OK);
    }
}
