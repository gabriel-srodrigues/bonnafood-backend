package br.com.bonnafood.app.files.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@NoArgsConstructor
public class NewPhoto {
    private String fileName;
    private String contentType;
    private InputStream inputStream;
}
