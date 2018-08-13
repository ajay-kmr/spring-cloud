package com.example.globomartapi.client;

import com.example.globomartapi.dto.*;
import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Headers("Content-Type: application/json")
public interface ProductCatalogClient2 {

    @RequestLine("POST /v1/catalog/create")
    ResponseDTO<CatalogDTO> createCatalog(@RequestBody CatalogDTO requestDTO);

    @RequestLine("POST /v1/catalog/search")
    DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(@RequestBody DataTableRequestDTO<CatalogDTO> requestDTO);

    @RequestLine("POST /v1/product/create")
    ResponseDTO<ProductDTO> createProduct(@RequestBody ProductDTO requestDTO);

    @RequestLine("POST /v1/product/remove")
    ResponseDTO<ProductDTO> removeProduct(@RequestBody ProductDTO requestDTO);

    @RequestLine("POST /v1/product/search")
    DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(@RequestBody DataTableRequestDTO<ProductDTO> requestDTO);
}
