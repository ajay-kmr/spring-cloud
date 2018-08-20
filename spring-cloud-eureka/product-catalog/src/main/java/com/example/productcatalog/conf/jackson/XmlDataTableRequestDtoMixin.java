package com.example.productcatalog.conf.jackson;

import com.example.productcatalog.dto.DataTableRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

@XmlRootElement(name = "response")
@XmlAccessorType(value = XmlAccessType.PROPERTY)
public class XmlDataTableRequestDtoMixin<T> extends DataTableRequestDTO<T> {

    @Override
    @XmlTransient
    public T getQuery() {
        return super.getQuery();
    }

    @Override
    @JsonSetter("query")
    @JacksonXmlProperty(localName = "query")
    public void setQuery(T query) {
        //Do Nothing
    }

    @Override
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @XmlElement(name = "data-item")
    @JacksonXmlProperty(localName = "query-item")
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
    @JacksonXmlElementWrapper(localName = "query-items")
    @JacksonXmlProperty(localName = "item")
    public Collection getDataForXmlAsCollection() {
        //Do Nothing as the Mixin is used for separating annotation only..
        // It does not perform any business logic or code injection
        return null;
    }
}
