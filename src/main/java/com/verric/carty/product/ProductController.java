package com.verric.carty.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts(Principal principal) {
        if(principal != null) {
            System.out.println(principal.getName());
        }else{
            System.out.println("principal is null");
        }
        List<ProductEntity> products = this.productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable("id") final Long id) {
        ProductEntity product = this.productRepository.findById(id).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductParams productParams) {
        ProductEntity product = new ProductEntity();
        product.setName(productParams.getName());
        product.setPrice(productParams.getPrice());
        product.setDescription(productParams.getDescription());

        ProductEntity newProduct = this.productRepository.save(product);
        return new ResponseEntity<>(newProduct , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable("id") final Long id, @RequestBody ProductParams productParams) {
        ProductEntity product = this.productRepository.findById(id).orElseThrow(RuntimeException::new);
        product.setName(productParams.getName());
        product.setPrice(productParams.getPrice());
        product.setDescription(productParams.getDescription());

        ProductEntity updatedProduct = this.productRepository.save(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") final Long id) {
        this.productRepository.deleteById(id);
        return  new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

@Data
class ProductParams {
    @JsonProperty
    private String name;
    @JsonProperty
    private long price;
    @JsonProperty
    private String description;
}