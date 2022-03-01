package br.com.bonnafood.app.recipes.api.controller;

import br.com.bonnafood.app.recipes.api.assembler.RecipeAssembler;
import br.com.bonnafood.app.recipes.api.assembler.RecipeDisassembler;
import br.com.bonnafood.app.recipes.api.assembler.RecipeSummaryAssembler;
import br.com.bonnafood.app.recipes.api.model.RecipeRequest;
import br.com.bonnafood.app.recipes.api.model.RecipeResponse;
import br.com.bonnafood.app.recipes.api.model.RecipeSummaryResponse;
import br.com.bonnafood.app.recipes.api.openapi.RecipeControllerOpenApi;
import br.com.bonnafood.app.recipes.domain.exception.RecipeNotFoundException;
import br.com.bonnafood.app.recipes.domain.filter.RecipeFilter;
import br.com.bonnafood.app.recipes.domain.model.Recipe;
import br.com.bonnafood.app.recipes.domain.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("recipes")
public class RecipeController implements RecipeControllerOpenApi {
    private final RecipeService service;
    private final RecipeSummaryAssembler summaryAssembler;
    private final RecipeAssembler assembler;
    private final RecipeDisassembler disassembler;
    private final PagedResourcesAssembler<Recipe> pagedResourcesAssembler;

    @Override
    @GetMapping
    public PagedModel<RecipeSummaryResponse> search(RecipeFilter recipeFilter, Pageable page) {
        Page<Recipe> pageRecipe = service.search(recipeFilter, page);
        return pagedResourcesAssembler.toModel(pageRecipe, summaryAssembler);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody RecipeRequest request) {
        Recipe recipe = disassembler.toDomainObject(request);
        recipe = service.save(recipe);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(recipe.getId()).toUri()).build();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable String id) {
        Recipe recipe = service.findById(id);
        RecipeResponse response = assembler.toModel(recipe);
        return ResponseEntity.ok(response);
    }

    @Override
    @PatchMapping("{recipeId}")
    public ResponseEntity<Void> update(@PathVariable String recipeId, @RequestBody Map<String, Object> fields) {
        Recipe currentRecipe = service.findById(recipeId);
        merge(fields, currentRecipe);
        service.save(currentRecipe);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteRecipe(id);
        return ResponseEntity.accepted().build();
    }


    private void merge(Map<String, Object> fields, Recipe currentRecipe) {
        ObjectMapper mapper = new ObjectMapper();
        Recipe recipeOrigin = mapper.convertValue(fields, Recipe.class);

        fields.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(Recipe.class, propertyName);
            assert field != null;
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, recipeOrigin);
            ReflectionUtils.setField(field, currentRecipe, newValue);
        });
    }

}
