package br.com.bonnafood.app.common.api.controller;

import br.com.bonnafood.app.common.domain.i18n.ReflectionsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping
public class CommonController {

    //Implementação para entender o funcionamento pleno do ListAllEnumsByLocate ~
    @GetMapping("admin/configuration/list-all-enums")
    public ResponseEntity<Map<String, Map<String, String>>> getAllEnums(HttpServletRequest request) {
        Map<String, Map<String, String>> allEnums = ReflectionsUtils.listAllEnumsByLocale(request.getLocale());
        return ResponseEntity.ok(allEnums);
    }

}
