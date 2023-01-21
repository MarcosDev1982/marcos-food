package com.food.marcosfood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.food.marcosfood.domain.model.Cidade;
import com.food.marcosfood.domain.model.Cozinha;
import com.food.marcosfood.domain.model.Restaurante;
import com.food.marcosfood.ipi.model.mixin.CidadeMixin;
import com.food.marcosfood.ipi.model.mixin.CozinhaMixin;
import org.springframework.stereotype.Component;



@Component
public class JacksonMixinModule extends SimpleModule {


    private static final long serialVersionUID = -1392471235847178940L;

    public JacksonMixinModule(){
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
   }




}
