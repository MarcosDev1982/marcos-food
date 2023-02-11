package com.food.marcosfood.ipi.assembler;

import com.food.marcosfood.domain.model.Usuario;
import com.food.marcosfood.ipi.model.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioModelAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toModell(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> collectionModell(List<Usuario> usuarios){
       return usuarios.stream().map(usuario -> toModell(usuario)).collect(Collectors.toList());
    }


}
