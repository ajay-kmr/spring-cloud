package com.example.globomartapi.config.jackson;

import com.example.globomartapi.dto.DataTableResponseDTO;
import com.example.globomartapi.dto.ErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "response")
@XmlAccessorType(value = XmlAccessType.PROPERTY)
public class XmlDataTableResponseDtoMixin<T> extends DataTableResponseDTO<T> {
    @Override
    @XmlTransient
    public T getData() {
        return super.getData();
    }

    @Override
    @JsonSetter("data")
    @JacksonXmlProperty(localName = "data")
    public void setData(T data) {
        super.setData(data);
    }

    @Override
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @XmlElement(name = "data-item")
    @JacksonXmlProperty(localName = "data-item")
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
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @XmlElementWrapper(name = "data-items")
//    @XmlElement(name = "item")
    @JacksonXmlElementWrapper(localName = "data-items")
    @JacksonXmlProperty(localName = "item")
    public Collection getDataForXmlAsCollection() {
        //Do Nothing as the Mixin is used for separating annotation only..
        // It does not perform any business logic or code injection
        return null;
    }

    /**
     * If you want to include any field in both JSON and XML response,
     * create a getter with required name
     *
     * @return
     */
    @Override
    @JacksonXmlElementWrapper(localName = "errors")
    @JacksonXmlProperty(localName = "error")
    public List<ErrorDTO> getErrors() {
        return super.getErrors();
    }
}
