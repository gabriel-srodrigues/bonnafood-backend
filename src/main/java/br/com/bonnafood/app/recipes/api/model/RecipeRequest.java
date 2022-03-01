package br.com.bonnafood.app.recipes.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;

@Getter
@Setter
public class RecipeRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    @NotNull
    private Duration cookingTime;
    private List<String> tags;
}
