package com.example.globomartapi.service;

import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.DataTableResponseDTO;
import com.example.globomartapi.dto.ProductDTO;
import com.example.globomartapi.dto.ResponseDTO;

import java.util.List;

public interface ProductService {

    ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO);

    ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO);

    DataTableResponseDTO<List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO);
}
