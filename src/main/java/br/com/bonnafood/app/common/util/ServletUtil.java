package br.com.bonnafood.app.common.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ServletUtil {

    private ServletUtil() {}

    public static URI getUriByRequest(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
