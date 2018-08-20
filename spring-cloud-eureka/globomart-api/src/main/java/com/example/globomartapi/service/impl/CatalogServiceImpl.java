package com.example.globomartapi.service.impl;

import com.example.globomartapi.client.ProductCatalogClient;
import com.example.globomartapi.dto.CatalogDTO;
import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.DataTableResponseDTO;
import com.example.globomartapi.dto.ResponseDTO;
import com.example.globomartapi.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl extends BaseServiceImpl implements CatalogService {

    @Autowired
    ProductCatalogClient productCatalogClient;

    @Override
    public ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO) {
        return productCatalogClient.createCatalog(requestDTO);
    }

    @Override
    public DataTableResponseDTO<List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO) {
        return productCatalogClient.searchCatalog(requestDTO);
    }
}
