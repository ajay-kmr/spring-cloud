package com.example.globomartapi.service.impl;

import com.example.globomartapi.client.ProductCatalogClient;
import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.DataTableResponseDTO;
import com.example.globomartapi.dto.ProductDTO;
import com.example.globomartapi.dto.ResponseDTO;
import com.example.globomartapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

    @Autowired
    ProductCatalogClient productCatalogClient;

    @Override
    public ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO) {
        return productCatalogClient.createProduct(requestDTO);
    }

    @Override
    public ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO) {
        return productCatalogClient.removeProduct(requestDTO);
    }

    public DataTableResponseDTO<List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO) {
        return productCatalogClient.searchProduct(requestDTO);
    }
}
