package br.com.bonnafood.app.recipes.domain.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class RecipeFilter {
    private String name;
    private String author;
    private Duration cookingTimeLessThan;
    private String title;
}
