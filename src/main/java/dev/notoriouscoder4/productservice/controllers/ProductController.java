package dev.notoriouscoder4.productservice.controllers;

import dev.notoriouscoder4.productservice.exceptions.NotFoundException;
import dev.notoriouscoder4.productservice.services.ProductService;
import dev.notoriouscoder4.productservice.thirdparty.GenericProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // get/products {}
    @GetMapping
    public List<GenericProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    //localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {

        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDTO> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                productService.deleteById(id),
                HttpStatus.OK
        );
    }


    @PutMapping("{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id,@RequestBody GenericProductDTO product){
        return productService.updateProductById(id,product);
    }
    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){
        return productService.createProduct(product);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/category/{category}")
    public List<GenericProductDTO> getByCategory(@PathVariable("category") String category){
        return (List<GenericProductDTO>) productService.getByCategory(category);
    }
}
