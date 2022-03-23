package br.com.bonnafood.app.products.api.openapi;

import br.com.bonnafood.app.products.api.model.CategoryRequest;
import br.com.bonnafood.app.products.api.model.CategoryResponse;
import br.com.bonnafood.app.products.api.model.ProductRequest;
import br.com.bonnafood.app.products.domain.filter.ProductFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Tag(name = "Categorias")
public interface CategoryControllerOpenApi {
    PagedModel<CategoryResponse> findAll(ProductFilter filter, Pageable pageable);

    ResponseEntity<CategoryResponse> findById(String id);

    ResponseEntity<Void> create(CategoryRequest productRequest);

    ResponseEntity<Void> delete(String id);

    ResponseEntity<Void> update(String id, Map<String, Object> fields);
}
