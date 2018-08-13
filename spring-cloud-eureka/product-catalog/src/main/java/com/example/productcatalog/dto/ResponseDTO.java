package com.example.productcatalog.dto;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
@JacksonXmlRootElement(localName = "response")
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
}
