package com.food.marcosfood.api.v1.assembler;

import com.food.marcosfood.api.v1.contoller.CidadeController;
import com.food.marcosfood.api.v1.contoller.EstadoController;
import com.food.marcosfood.api.v1.model.CidadeDTO;
import com.food.marcosfood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public CidadeModelAssembler() {
        super(CidadeController.class, CidadeDTO.class);
    }

    @Override
    public CidadeDTO toModel(Cidade cidade) {

        CidadeDTO cidademodel = createModelWithId(cidade.getId(), cidade);
        modelMapper.map(cidade, cidademodel);
        CidadeDTO cidadeDTO = modelMapper.map(cidade, CidadeDTO.class);

      // outra forma de fazer
       /* cidadeDTO.add(linkTo(methodOn(CidadeController.class)
                .findAllById(cidadeDTO.getId()))
                .withSelfRel());*/

        cidadeDTO.add((linkTo(methodOn(CidadeController.class)
                .findAll()).withRel("cidades")));

        cidadeDTO.getEstado().add(linkTo(methodOn(EstadoController.class)
                .buscarPorId(cidadeDTO.getEstado().getId()))
                .withSelfRel());


        return cidadeDTO;

    }

    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(CidadeController.class).withSelfRel());
    }

    /*  public List<CidadeDTO> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
    }
*/


}
