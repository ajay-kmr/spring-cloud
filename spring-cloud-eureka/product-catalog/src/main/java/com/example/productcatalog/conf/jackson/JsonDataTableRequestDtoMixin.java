package com.example.productcatalog.conf.jackson;

import com.example.productcatalog.dto.DataTableRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

public class JsonDataTableRequestDtoMixin<T> extends DataTableRequestDTO<T> {

    @Override
    @JsonIgnore
    public T getDataForXmlAsSingleNode() {
        //Do Nothing as the Mixin is used for separating annotation only..
        // It does not perform any business logic or code injection
        return null;
    }

    /**
     * If u want to create any field that needs to be included in XML and not in JSON,
     * then simply create a method which should not start with get and apply appropriate annotations of JaxB
     *
     * @return
     */
    @Override
    @JsonIgnore
    public Collection getDataForXmlAsCollection() {
        //Do Nothing as the Mixin is used for separating annotation only..
        // It does not perform any business logic or code injection
        return null;
    }
}
