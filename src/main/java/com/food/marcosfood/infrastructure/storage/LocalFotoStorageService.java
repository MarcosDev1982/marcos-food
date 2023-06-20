package com.food.marcosfood.infrastructure.storage;

import com.food.marcosfood.domain.service.FotoStorageService;
import org.springframework.stereotype.Service;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Override
    public void armazenar(NovaFoto novaFoto) {
     novaFoto.getInputStream();

    }
}
