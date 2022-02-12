package br.com.bonnafood.app.files.domain.service;

import br.com.bonnafood.app.files.domain.model.NewPhoto;

import java.util.UUID;

public interface StorageService {
    void armazenar(NewPhoto novaFoto);

    void remover(String nomeArquivo);

    default void substituir(String nomeArquivoAntigo, NewPhoto novaFoto) {
        this.armazenar(novaFoto);

        if (nomeArquivoAntigo != null) {
            this.remover(nomeArquivoAntigo);
        }
    }

    default String gerarNomeArquivo(String nomeOriginal) {
        return UUID.randomUUID() + "_" + nomeOriginal;
    }

}