package com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper;

import com.doncel.pruebaUsuarios.domain.model.Usuario;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.request.UsuarioCreateRequest;
import org.mapstruct.Mapper;

/**
 * @author Oliver & Ragnar
 */
@Mapper(componentModel = "spring")
public interface UsuarioRequestMapper {

    Usuario toDomain(UsuarioCreateRequest request);
    UsuarioCreateRequest toRequest(Usuario usuario);
}
