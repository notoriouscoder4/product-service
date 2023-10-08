package dev.notoriouscoder4.productservice.thirdpartyclients.productservices.fakestore;

import dev.notoriouscoder4.productservice.exceptions.NotFoundException;
import dev.notoriouscoder4.productservice.thirdparty.GenericProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeShopClientsServices {
    @Value("${fakeStore.api.url}")
    private String fakeStoreApiURL;
    @Value("${fakeStore.api.paths.product}")
    private String fakeStoreProductPathApi;

    private String getProductRequestUrl;
    private String productRequestsBaseUrl;
    private String allCategoriesUrl;
    private String specificCategoryUrl;
    private RestTemplateBuilder restTemplateBuilder;

    public FakeShopClientsServices(RestTemplateBuilder restTemplateBuilder,
                                   @Value("${fakeStore.api.url}") String getProductRequestUrl,
                                   @Value("${fakeStore.api.paths.product}") String productRequestsBaseUrl,
                                   @Value("${fakeStore.api.paths.allCategories}") String allCategoriesUrl,
                                   @Value("${fakeStore.api.paths.specific.category}") String specificCategoryUrl ){
        this.restTemplateBuilder = restTemplateBuilder;
        this.getProductRequestUrl = getProductRequestUrl + productRequestsBaseUrl;
        this.productRequestsBaseUrl = getProductRequestUrl + productRequestsBaseUrl + "/{id}";
        this.allCategoriesUrl = getProductRequestUrl + allCategoriesUrl;
        this.specificCategoryUrl = getProductRequestUrl + specificCategoryUrl + "/{category}";
    }

    private GenericProductDTO convertFakeProductInGenericDtoProduct(FakeProductDTO fakeProductDto){

        GenericProductDTO product = new GenericProductDTO();
        product.setId(fakeProductDto.getId());
        product.setImage(fakeProductDto.getImage());
        product.setTitle(fakeProductDto.getTitle());
        product.setDescription(fakeProductDto.getDescription());
        product.setPrice(fakeProductDto.getPrice());
        product.setCategory(fakeProductDto.getCategory());

        return product;
    }

    public FakeProductDTO createProduct(GenericProductDTO product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDTO> response =
                restTemplate.postForEntity(
                        productRequestsBaseUrl,product,FakeProductDTO.class);

        return response.getBody();
    }

    public FakeProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //send a request and get a response from third party
        ResponseEntity<FakeProductDTO> response =
                restTemplate.getForEntity(getProductRequestUrl, FakeProductDTO.class, id);

        FakeProductDTO fakeProductDto = response.getBody();

        if(fakeProductDto == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist");
        }
        return fakeProductDto;
    }

    public List<FakeProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeProductDTO[]> response =
                restTemplate.getForEntity(productRequestsBaseUrl,FakeProductDTO[].class);

        List<GenericProductDTO> answer = new ArrayList<>();

        return Arrays.stream(response.getBody()).toList();
    }

    public FakeProductDTO deleteById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeProductDTO.class);
        ResponseEntity<FakeProductDTO> response = restTemplate.execute(
                getProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }

    public FakeProductDTO updateProductById(Long id, GenericProductDTO product) {
        RestTemplate restTemplate=restTemplateBuilder.build();


        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeProductDTO>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeProductDTO.class);

        ResponseEntity<FakeProductDTO> response= restTemplate.execute(
                getProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id,product
        );

//        FakeStoreProductDTO fakeStoreProductDTO=response.getBody();

        return response.getBody();
    }

    public List<String> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List> response= restTemplate.getForEntity(allCategoriesUrl, List.class);
        return response.getBody().stream().toList();
    }

    public List<FakeProductDTO> getByCategory(String category){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductDTO[]> response = restTemplate.getForEntity(getProductRequestUrl,FakeProductDTO[].class,category);

        return Arrays.stream(response.getBody()).toList();
    }
}
