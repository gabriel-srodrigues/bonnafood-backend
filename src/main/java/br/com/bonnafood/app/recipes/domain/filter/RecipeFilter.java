package br.com.bonnafood.app.recipes.domain.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeFilter {
    private String tag;
    private String author;
    private String title;
}
