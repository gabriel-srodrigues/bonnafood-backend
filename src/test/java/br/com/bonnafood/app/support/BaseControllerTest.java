package br.com.bonnafood.app.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
public class BaseControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJsonString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
