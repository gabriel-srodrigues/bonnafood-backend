package br.com.bonnafood.app.recipes.api.model;

import br.com.bonnafood.app.users.domain.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class RecipeResponse extends RepresentationModel<RecipeResponse> {
    private String id;
    private String title;
    private String body;
    @JsonFormat(pattern = "MINUTES")
    private Duration cookingTime;
    private User createdBy;
    private OffsetDateTime createdAt;
    private User updatedBy;
    private OffsetDateTime updatedAt;
    private String image;
    private String video;
    private List<String> tags;
}
