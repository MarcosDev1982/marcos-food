package com.food.marcosfood.infrastructure.storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import com.food.marcosfood.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${marcosfood.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeAquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(),
                    Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e.getCause());
        }
    }

    @Override
    public void remover(String nomeArquivo) {
      try {
          Path arquivoPath = getArquivoPath(nomeArquivo);
          Files.deleteIfExists(arquivoPath);
      }catch (Exception e){
          throw new StorageException("Não foi possível excluir arquivo", e);
      }

    }

    private Path getArquivoPath(String nomeArquivo) {
        return diretorioFotos.resolve(Path.of(nomeArquivo));
    }

}
