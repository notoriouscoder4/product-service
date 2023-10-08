package dev.notoriouscoder4.productservice.services;

import dev.notoriouscoder4.productservice.exceptions.NotFoundException;
import dev.notoriouscoder4.productservice.thirdparty.GenericProductDTO;
import dev.notoriouscoder4.productservice.thirdpartyclients.productservices.fakestore.FakeProductDTO;
import dev.notoriouscoder4.productservice.thirdpartyclients.productservices.fakestore.FakeShopClientsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Primary
@Repository("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private FakeShopClientsServices fakeShopClientsServices;

    @Autowired
    public FakeStoreProductService(FakeShopClientsServices fakeShopClientsServices) {
        this.fakeShopClientsServices = fakeShopClientsServices;
    }

    private GenericProductDTO convertFakeProductInGenericDtoProduct(FakeProductDTO fakeProductDto) {

        GenericProductDTO product = new GenericProductDTO();
        product.setId(fakeProductDto.getId());
        product.setImage(fakeProductDto.getImage());
        product.setTitle(fakeProductDto.getTitle());
        product.setDescription(fakeProductDto.getDescription());
        product.setPrice(fakeProductDto.getPrice());
        product.setCategory(fakeProductDto.getCategory());

        return product;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO product) {
        return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.createProduct(product));
    }

    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {
        return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.getProductById(id));
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<GenericProductDTO> genericDtoProducts = new ArrayList<>();

        for (FakeProductDTO fakeProductDto : fakeShopClientsServices.getAllProducts()) {

            genericDtoProducts.add(convertFakeProductInGenericDtoProduct(fakeProductDto));
        }
        return genericDtoProducts;
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO product) {

        return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.updateProductById(id, product));
    }

    @Override
    public GenericProductDTO deleteById(Long id) {
        return convertFakeProductInGenericDtoProduct(fakeShopClientsServices.deleteById(id));
    }

    @Override
    public List<String> getAllCategories() {
        return fakeShopClientsServices.getAllCategories();
    }

    @Override
    public GenericProductDTO getByCategory(String category) {
        return null;
    }
}
