package com.example.productcatalog.service;

import com.example.productcatalog.dto.CatalogDTO;
import com.example.productcatalog.dto.DataTableRequestDTO;
import com.example.productcatalog.dto.DataTableResponseDTO;
import com.example.productcatalog.dto.ResponseDTO;

import java.util.List;

public interface CatalogService {

    ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO);

    DataTableResponseDTO<List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO);
}
