package br.com.bonnafood.app.products.api.controller;

import br.com.bonnafood.app.products.api.model.CategoryRequest;
import br.com.bonnafood.app.products.api.model.CategoryResponse;
import br.com.bonnafood.app.products.api.openapi.CategoryControllerOpenApi;
import br.com.bonnafood.app.products.domain.filter.ProductFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("categories")
public class CategoryController implements CategoryControllerOpenApi {

    @Override
    @GetMapping
    public PagedModel<CategoryResponse> findAll(ProductFilter filter, Pageable pageable) {
        return null;
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable String id) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CategoryRequest productRequest) {
        return null;
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> delete(String id) {
        return null;
    }

    @Override
    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Map<String, Object> fields) {
        return null;
    }
}
