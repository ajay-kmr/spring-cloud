package com.example.globomartapi.service;

import com.example.globomartapi.dto.CatalogDTO;
import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.DataTableResponseDTO;
import com.example.globomartapi.dto.ResponseDTO;

import java.util.List;

public interface CatalogService {

    ResponseDTO<CatalogDTO> createCatalog(CatalogDTO requestDTO);

    DataTableResponseDTO<List<CatalogDTO>> searchCatalog(DataTableRequestDTO<CatalogDTO> requestDTO);
}
