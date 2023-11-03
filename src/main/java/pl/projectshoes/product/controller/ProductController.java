package pl.projectshoes.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.projectshoes.product.dto.ProductDTO;
import pl.projectshoes.product.enums.ShoeColor;
import pl.projectshoes.product.requests.ProductAddRequest;
import pl.projectshoes.product.service.ProductService;
import pl.projectshoes.utils.HttpResponse;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    //get, post, put, delete
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<HttpResponse> getAllProducts(){
        return ResponseEntity.status(OK).body(HttpResponse.builder()
                .status(OK)
                .statusCode(OK.value())
                .data(Map.of("products", productService.getAllProducts()))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponse> getProductById(@PathVariable long id) {
            final Optional<ProductDTO> productDTO = productService.mapToProductDTO(id);
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
                            .data(Map.of("id",id))
                            .build());
                }
    }
    @PostMapping("/add")
    public ResponseEntity<HttpResponse> addProduct(@RequestBody ProductAddRequest productAddRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .message(bindingResult.getAllErrors().get(0).getDefaultMessage())
                    .build());
        } else if (productService.isProductExist(productAddRequest.productCode())){
            return ResponseEntity.status(BAD_REQUEST).body(HttpResponse.builder()
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .message("Product with this product code already exist in repository !")
                    .build());
        } else {
            productService.addProduct(productAddRequest);
            return ResponseEntity.status(CREATED).body(HttpResponse.builder()
                    .status(CREATED)
                    .statusCode(CREATED.value())
                    .developerMessage("Product was added to repository !")
                    .build());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteProduct(@PathVariable long id){
        if (productService.getProductById(id).isPresent()){
            productService.deleteProduct(id);
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
                    .data(Map.of("id",id))
                    .build());
        }
    }

}
