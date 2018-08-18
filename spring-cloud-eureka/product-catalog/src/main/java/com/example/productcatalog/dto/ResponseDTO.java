package com.example.productcatalog.dto;


import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

    public Collection getDataForXmlAsCollection() {
        System.out.println("?????????? Inside getDataForXmlAsCollection() method of " + this.getClass());
        if (data instanceof Collection) {
            return (Collection) data;
        }
        return null;
    }

    public T getDataForXmlAsSingleNode() {
        System.out.println("?????????? Inside getDataForXmlAsSingleNode() method of " + this.getClass());
        if (!(data instanceof Collection)) {
            return data;
        }
        return null;
    }


}
