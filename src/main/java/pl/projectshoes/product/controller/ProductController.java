package pl.projectshoes.product.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.repository.ProductRepository;
import pl.projectshoes.product.service.ProductService;
import pl.projectshoes.utils.HttpResponse;

import javax.security.auth.callback.ConfirmationCallback;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    //get, post, put, delete
    private final ProductService productService;

    @GetMapping("/id")
    public ResponseEntity<HttpResponse> getProductById(@PathVariable long id) {
        if (productService.isProductExist(id)) {
            final ProductDTO productDTO = productService.mapToProductDTO(id);

            return ResponseEntity.status(ConfirmationCallback.OK).body(HttpResponse.builder()
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .data(Map.of("product", productDTO))
                    .build());
        } else {
            return ResponseEntity.ok(HttpResponse.builder()
                    .status(NOT_FOUND)
                    .statusCode(NOT_FOUND.value())
                    .message("Product was not found in repository !")
                    .data(Map.of("id",id))
                    .build());
        }
    }

}
