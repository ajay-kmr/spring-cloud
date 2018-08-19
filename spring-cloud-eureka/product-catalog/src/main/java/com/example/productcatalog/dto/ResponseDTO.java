package com.example.productcatalog.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    public ResponseDTO(Boolean status, String message, T data, List<ErrorDTO> errors) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    /*getter/setter used for decorating XML request/response --STARTS--*/
    public Collection getDataForXmlAsCollection() {
        System.out.println("?????????? Inside getDataForXmlAsCollection() method of " + this.getClass());
        if (data instanceof Collection) {
            return (Collection) data;
        }
        return null;
    }

    public void setDataForXmlAsCollection(T dataForXmlAsCollection) {
        this.data = dataForXmlAsCollection;
    }

    public T getDataForXmlAsSingleNode() {
        System.out.println("?????????? Inside getDataForXmlAsSingleNode() method of " + this.getClass());
        if (!(data instanceof Collection)) {
            return data;
        }
        return null;
    }

    public void setDataForXmlAsSingleNode(T dataForXmlAsSingleNode) {
        this.data = dataForXmlAsSingleNode;
    }
    /*getter/setter used for decorating XML request/response --ENDS--*/
}
