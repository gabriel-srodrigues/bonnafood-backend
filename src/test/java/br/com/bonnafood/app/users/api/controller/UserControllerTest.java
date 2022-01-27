package br.com.bonnafood.app.users.api.controller;

import br.com.bonnafood.app.support.BaseControllerTest;
import br.com.bonnafood.app.template.user.UserTemplateLoader;
import br.com.bonnafood.app.template.user.builders.UserBuilder;
import br.com.bonnafood.app.users.domain.model.BonnaUser;
import br.com.bonnafood.app.users.domain.service.UserCrudService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCrudService userCrudService;

    private final UserTemplateLoader templateLoader = new UserTemplateLoader();

    @Test
    void given_invalidPhone_when_creatingUser_thenReturnsThrows() throws Exception {
        BonnaUser user = new UserBuilder().anyBonnaUser().withPassword("Test123@").withPhone("000000000").build();
        this.mockMvc
                .perform(post("/users")
                        .content(toJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void given_validUser_when_creatingUser_thenReturnsSuccess() throws Exception {
        BonnaUser user = new UserBuilder().anyBonnaUser().withPassword("Test123@21d").withPhone("(99) 99999-9999").build();
        Mockito.when(userCrudService.save(any())).thenReturn(user);
        this.mockMvc
                .perform(post("/users")
                        .content(toJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}