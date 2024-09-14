package com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.mapper;

import com.doncel.pruebaUsuarios.domain.model.Usuario;
import com.doncel.pruebaUsuarios.infraestructure.adapters.input.rest.model.response.UsuarioResponse;
import com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Oliver & Ragnar
 */
@Mapper(componentModel = "spring")
public interface UsuarioResponseMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "created", target = "created")
    @Mapping(source = "modified", target = "modified")
    @Mapping(source = "lastLogin", target = "lastLogin")
    @Mapping(source = "token", target = "token")
    @Mapping(source = "isActive", target = "isActive")
    @Mapping(source = "phones", target = "phones")
    UsuarioResponse toResponse(Usuario usuario);


}
