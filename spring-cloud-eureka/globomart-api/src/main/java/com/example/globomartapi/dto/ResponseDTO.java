package com.example.globomartapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(value = XmlAccessType.PROPERTY)
public class ResponseDTO<T> implements Serializable {

    private Boolean status = true;
    private String message;

    private T data;

    private List<ErrorDTO> errors = new ArrayList<>();

    public ResponseDTO(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(Boolean status, String message, List<ErrorDTO> errorDTOS) {
        this.status = status;
        this.message = message;
        this.errors = errorDTOS;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @XmlElement(name = "data")
    public T createDataAsSingleNodeWhichNeedsToBeOnlyIncludedInXmlAndNotInJson() {
        if (!(data instanceof Collection)) {
            return data;
        }
        return null;
    }

    /**
     * If u want to create any field that needs to be included in XML and not in JSON,
     * then simply create a method which should not start with get and apply appropriate annotations of JaxB
     *
     * @return
     */
    @XmlElementWrapper(name = "data")
    @XmlElement(name = "item")
    public Collection createDataAsSingleNodeWhichNeedsToBeOnlyIncludedInXmlAndNotInJson2() {
        if (data instanceof Collection) {
            return (Collection) data;
        }
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
        return errors;
    }

    @XmlTransient
    @JsonProperty(value = "fieldToBeOnlyIncludedInJsonAndNotInXml")
    public String fieldToBeOnlyIncludedInJsonAndNotInXml() {
        //TODO:- Add if any
        return "Only present in Json and not in XML";
    }
}
