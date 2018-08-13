package com.example.productcatalog.databinder;

import com.example.productcatalog.dao.entity.Catalog;
import com.example.productcatalog.dao.entity.Product;
import com.example.productcatalog.dto.ProductDTO;

public class ProductDataBinder {

    public static Product bind(ProductDTO source) {
        if (source == null) {
            return null;
        }
        return bind(new Product(), source);
    }

    public static Product bind(Product destination, ProductDTO source) {
        if (source == null) {
            return null;
        }
        destination.setId(source.getId());
        destination.setName(source.getName());
        return destination;
    }

    public static Product bind(ProductDTO source, Catalog catalog) {
        Product product = bind(source);
        product.setCatalog(catalog);
        return product;
    }
}
