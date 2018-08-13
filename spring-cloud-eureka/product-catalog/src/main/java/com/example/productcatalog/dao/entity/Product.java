package com.example.productcatalog.dao.entity;

import com.example.productcatalog.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Product extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    private String name;

    private ProductType type;

    @ManyToOne
    private Catalog catalog;


}
