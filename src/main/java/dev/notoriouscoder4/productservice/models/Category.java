package dev.notoriouscoder4.productservice.models;

import jakarta.persistence.Entity;

@Entity(name = "category")
public class Category extends BaseModel{
    private String name;
}
