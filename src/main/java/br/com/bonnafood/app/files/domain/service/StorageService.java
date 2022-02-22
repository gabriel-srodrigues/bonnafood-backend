package br.com.bonnafood.app.files.domain.service;

import br.com.bonnafood.app.files.domain.model.NewPhoto;

import java.util.UUID;

public interface StorageService {
    void storage(NewPhoto novaFoto);

    void delete(String nomeArquivo);

    default void update(String oldFileName, NewPhoto newPhoto) {
        this.storage(newPhoto);

        if (oldFileName != null) {
            this.delete(oldFileName);
        }
    }

    default String makeFileName(String name) {
        return UUID.randomUUID() + "_" + name;
    }

}