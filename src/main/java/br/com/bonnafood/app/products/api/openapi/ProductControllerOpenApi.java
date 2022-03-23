package br.com.bonnafood.app.products.api.openapi;

import br.com.bonnafood.app.products.api.controller.ProductController;
import br.com.bonnafood.app.products.api.model.ProductDetailedResponse;
import br.com.bonnafood.app.products.api.model.ProductRequest;
import br.com.bonnafood.app.products.api.model.ProductSummaryResponse;
import br.com.bonnafood.app.products.domain.filter.ProductFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Tag(name = "Produtos")
public interface ProductControllerOpenApi {
    PagedModel<ProductSummaryResponse> findAll(ProductFilter filter, Pageable pageable);

    ResponseEntity<ProductDetailedResponse> findById(String id);

    ResponseEntity<Void> create(ProductRequest productRequest);

    ResponseEntity<Void> delete(String id);

    ResponseEntity<Void> update(String id, Map<String, Object> fields);

}
