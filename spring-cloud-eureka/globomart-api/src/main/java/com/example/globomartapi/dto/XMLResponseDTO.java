package com.example.globomartapi.dto;

import java.util.List;

public class XMLResponseDTO {
    private ResponseDTO<List<ProductDTO>> ResponseDTO;

    public ResponseDTO<List<ProductDTO>> getResponseDTO() {
        return ResponseDTO;
    }

    public void setResponseDTO(ResponseDTO<List<ProductDTO>> ResponseDTO) {
        this.ResponseDTO = ResponseDTO;
    }

    @Override
    public String toString() {
        return "ClassPojo [ResponseDTO = " + ResponseDTO + "]";
    }
}
