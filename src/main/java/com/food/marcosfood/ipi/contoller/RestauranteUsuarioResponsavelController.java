package com.food.marcosfood.ipi.contoller;


import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.service.RestauranteService;
import com.food.marcosfood.ipi.assembler.UsuarioModelAssembler;
import com.food.marcosfood.ipi.model.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante/{restuarnteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public List<UsuarioDTO> listar(@PathVariable Long restuarnteId){
        Restaurante restaurante = restauranteService.buscarPorId(restuarnteId);

        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());

    }


    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associaUsuarioAuRestuarante(@PathVariable Long restuarnteId, @PathVariable Long usuarioId ) {
        restauranteService.asscociarUsuario(restuarnteId, usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociaUsuarioAuRestuarante(@PathVariable Long restuarnteId, @PathVariable Long usuarioId ) {
        restauranteService.desasscociarUsuario(restuarnteId, usuarioId);
    }
}
