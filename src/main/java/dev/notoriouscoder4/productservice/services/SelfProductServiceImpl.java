package dev.notoriouscoder4.productservice.services;

import dev.notoriouscoder4.productservice.thirdparty.GenericProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    @Override
    public GenericProductDTO getProductById(Long id) {
        return null;
    }
    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return null;
    }
    @Override
    public List<GenericProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO product) {
        return null;
    }

    @Override
    public GenericProductDTO deleteById(Long id) {
        return null;
    }

    @Override
    public GenericProductDTO getByCategory(String category) {
        return null;
    }

    @Override
    public List<String> getAllCategories() {
        return null;
    }
}
