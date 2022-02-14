package br.com.bonnafood.app.users.api.controller;

import br.com.bonnafood.app.support.BaseControllerTest;
import br.com.bonnafood.app.template.user.UserTemplateLoader;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnafood.app.users.domain.service.UserCrudService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.bonnafood.app.template.user.UserTemplateLoader.UserTemplate.USER_WITH_INVALID_PHONE;
import static br.com.bonnafood.app.template.user.UserTemplateLoader.UserTemplate.USER_WITH_VALID_PHONE;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCrudService userCrudService;

    private final UserTemplateLoader templateLoader = new UserTemplateLoader();

    @Test
    @DisplayName("Deve falhar: phone - inválido")
    void given_invalidPhone_when_creatingUser_thenReturnsThrows() throws Exception {
        User user = templateLoader.get(USER_WITH_INVALID_PHONE);
        this.mockMvc
                .perform(post("/users")
                        .content(toJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve passar: todos os atributos são válidos")
    void given_validUser_when_creatingUser_thenReturnsSuccess() throws Exception {
        User user = templateLoader.get(USER_WITH_VALID_PHONE);
        Mockito.when(userCrudService.save(any())).thenReturn(user);
        this.mockMvc
                .perform(post("/users")
                        .content(toJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}