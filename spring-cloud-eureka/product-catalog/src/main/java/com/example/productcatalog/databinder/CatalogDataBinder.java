package com.example.productcatalog.databinder;

import com.example.productcatalog.dao.entity.Catalog;
import com.example.productcatalog.dto.CatalogDTO;

public class CatalogDataBinder {

    public static Catalog bind(CatalogDTO source) {
        if (source == null) {
            return null;
        }
        return bind(new Catalog(), source);
    }

    public static Catalog bind(Catalog destination, CatalogDTO source) {
        if (source == null) {
            return null;
        }
        destination.setId(source.getId());
        destination.setName(source.getName());
        return destination;
    }
}
