package com.food.marcosfood.api.model.input.assembler;

import com.food.marcosfood.domain.model.Usuario;
import com.food.marcosfood.api.model.input.UsuarioAltInput;
import com.food.marcosfood.api.model.input.UsuarioInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioAltInput usuarioAltInput, Usuario usuario) {
        modelMapper.map(usuarioAltInput, usuario);
    }


}
