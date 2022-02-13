package br.com.bonnafood.app.common.api.openapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkModelOpenApi {
    private LinkModel rel;

    @Getter
    @Setter
    private static class LinkModel {
        private String href;
        private boolean templated;
    }
}
