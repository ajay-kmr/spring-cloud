package com.example.productcatalog.conf.jackson;

import com.example.productcatalog.dto.ErrorDTO;
import com.example.productcatalog.dto.ResponseDTO;
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
public class XmlResponseDtoMixin<T> extends ResponseDTO<T> {

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
    @JacksonXmlProperty(localName = "data")
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
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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
    @JacksonXmlElementWrapper(localName = "errors")
    @JacksonXmlProperty(localName = "error")
    public List<ErrorDTO> getErrors() {
        return super.getErrors();
    }

}
