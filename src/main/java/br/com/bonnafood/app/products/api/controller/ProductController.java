package br.com.bonnafood.app.products.api.controller;

import br.com.bonnafood.app.products.api.model.ProductDetailedResponse;
import br.com.bonnafood.app.products.api.model.ProductRequest;
import br.com.bonnafood.app.products.api.model.ProductSummaryResponse;
import br.com.bonnafood.app.products.api.openapi.ProductControllerOpenApi;
import br.com.bonnafood.app.products.domain.filter.ProductFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductController implements ProductControllerOpenApi {

    @Override
    @GetMapping
    public PagedModel<ProductSummaryResponse> findAll(ProductFilter filter, Pageable pageable) {
        return null;
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ProductDetailedResponse> findById(@PathVariable String id) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductRequest productRequest) {
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
