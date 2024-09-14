package com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.mapper;

import com.doncel.pruebaUsuarios.domain.model.Phone;
import com.doncel.pruebaUsuarios.domain.model.Usuario;
import com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.entity.PhoneEntity;
import com.doncel.pruebaUsuarios.infraestructure.adapters.output.persistence.entity.UsuarioEntity;
import org.mapstruct.Mapper;

/**
 * @author Oliver & Ragnar
 */
@Mapper(componentModel = "spring")
public interface UsuarioPersistenceMapper {
    UsuarioEntity toEntity(Usuario usuario);

    Usuario toDomain(UsuarioEntity entity);

    PhoneEntity toPhoneEntity(Phone phone);
}
