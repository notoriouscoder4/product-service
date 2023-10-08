package dev.notoriouscoder4.productservice.thirdparty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public void setId(Long id) {
    }
}
