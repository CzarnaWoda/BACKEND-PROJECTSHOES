package pl.projectshoes.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.dto.ProductDTOMapper;
import pl.projectshoes.product.requests.ProductCreateRequest;
import pl.projectshoes.product.service.ProductService;
import pl.projectshoes.utils.HttpResponse;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ProductDTOMapper productDTOMapper;

    @GetMapping("/all")
    public ResponseEntity<HttpResponse> getAllProducts(){
        return ResponseEntity.status(OK).body(HttpResponse.builder()
                .status(OK)
                .statusCode(OK.value())
                .data(Map.of("products", productService.getAllProducts()))
                .build());
    }

    @GetMapping("/{productCode}")
    public ResponseEntity<HttpResponse> getProductById(@PathVariable String productCode) {
        final Optional<ProductDTO> productDTO = productService.getProductByProductCode(productCode).map(productDTOMapper::fromProduct);
                if(productDTO.isPresent()){
                    return ResponseEntity.status(OK).body(HttpResponse.builder()
                            .status(OK)
                            .statusCode(OK.value())
                            .data(Map.of("product", productDTO))
                            .build());
                }else{
                    return ResponseEntity.status(NOT_FOUND).body(HttpResponse.builder()
                            .status(NOT_FOUND)
                            .statusCode(NOT_FOUND.value())
                            .message("Product was not found in repository !")
                            .data(Map.of("productCode",productCode))
                            .build());
                }
    }
    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest, BindingResult bindingResult){
        final String productCode = productCreateRequest.productCode();
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .message(bindingResult.getAllErrors().get(0).getDefaultMessage())
                    .build());
        } else if (productService.isProductExist(productCode)){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .message("Product with this product code already exist in repository !")
                    .build());
        } else {
            productService.createProduct(productCreateRequest);
            return ResponseEntity.status(CREATED).body(HttpResponse.builder()
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .developerMessage("Product was added to repository !")
                    .build());
        }
    }

    @DeleteMapping("/delete/{productCode}")
    public ResponseEntity<HttpResponse> deleteProduct(@PathVariable String productCode){
        if (productService.getProductByProductCode(productCode).isPresent()){
            productService.deleteProduct(productCode);
            return ResponseEntity.status(OK).body(HttpResponse.builder()
                    .status(OK)
                    .statusCode(OK.value())
                    .developerMessage("Product was deleted from repository !")
                    .build());
        } else {
            return ResponseEntity.status(NOT_FOUND).body(HttpResponse.builder()
                    .status(NOT_FOUND)
                    .statusCode(NOT_FOUND.value())
                    .message("Product was not found in repository !")
                    .data(Map.of("productCode",productCode))
                    .build());
        }
    }

}
