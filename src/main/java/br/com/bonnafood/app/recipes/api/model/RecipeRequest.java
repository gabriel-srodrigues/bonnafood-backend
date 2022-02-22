package br.com.bonnafood.app.recipes.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class RecipeRequest {
    private String title;
    private String body;
    private Duration cookingTime;
}
