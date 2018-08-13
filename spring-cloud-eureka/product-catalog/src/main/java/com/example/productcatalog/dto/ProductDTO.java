package com.example.productcatalog.dto;

import com.example.productcatalog.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class ProductDTO extends BaseEntityDTO<Long> {
    protected Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    protected Long catalogId;

    private String catalogName;

}
