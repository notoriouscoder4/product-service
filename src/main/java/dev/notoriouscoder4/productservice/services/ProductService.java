package dev.notoriouscoder4.productservice.services;

import dev.notoriouscoder4.productservice.exceptions.NotFoundException;
import dev.notoriouscoder4.productservice.thirdparty.GenericProductDTO;

import java.util.List;

public interface ProductService {
    GenericProductDTO createProduct(GenericProductDTO product);

    GenericProductDTO getProductById(Long id) throws NotFoundException;
    List<GenericProductDTO> getAllProducts();

    GenericProductDTO updateProductById(Long id, GenericProductDTO product);

    GenericProductDTO deleteById(Long id);
    List<String> getAllCategories();

    GenericProductDTO getByCategory(String category);
}
