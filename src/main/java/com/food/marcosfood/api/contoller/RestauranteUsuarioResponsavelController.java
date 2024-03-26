package com.food.marcosfood.api.contoller;


import com.food.marcosfood.api.model.UsuarioDTO;
import com.food.marcosfood.api.model.input.assembler.UsuarioModelAssembler;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurante/{restuarnteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public CollectionModel<UsuarioDTO> listar(@PathVariable Long restuarnteId) {
        Restaurante restaurante = restauranteService.buscarPorId(restuarnteId);

        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());

    }


    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associaUsuarioAuRestuarante(@PathVariable Long restuarnteId, @PathVariable Long usuarioId) {
        restauranteService.asscociarUsuario(restuarnteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociaUsuarioAuRestuarante(@PathVariable Long restuarnteId, @PathVariable Long usuarioId) {
        restauranteService.desasscociarUsuario(restuarnteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
