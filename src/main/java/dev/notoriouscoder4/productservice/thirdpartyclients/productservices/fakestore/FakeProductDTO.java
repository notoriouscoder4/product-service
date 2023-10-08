package dev.notoriouscoder4.productservice.thirdpartyclients.productservices.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
