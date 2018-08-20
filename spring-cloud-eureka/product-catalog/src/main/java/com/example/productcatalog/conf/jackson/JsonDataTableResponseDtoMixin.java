package com.example.productcatalog.conf.jackson;

import com.example.productcatalog.dto.DataTableResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "response")
@XmlAccessorType(value = XmlAccessType.PROPERTY)
public class JsonDataTableResponseDtoMixin<T> extends DataTableResponseDTO<T> {

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
