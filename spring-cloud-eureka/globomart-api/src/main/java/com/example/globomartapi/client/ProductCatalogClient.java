package com.example.globomartapi.client;

import com.example.globomartapi.dto.*;
import feign.Headers;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Headers("Content-Type: application/json")
public interface ProductCatalogClient {

    @RequestMapping(method = RequestMethod.POST, value = "/v1/catalog/create", consumes = "application/json")
    ResponseDTO<CatalogDTO> createCatalog(@RequestBody CatalogDTO requestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/catalog/search", consumes = "application/json")
    DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> searchCatalog(@RequestBody DataTableRequestDTO<CatalogDTO> requestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/product/create", consumes = "application/json")
    ResponseDTO<ProductDTO> createProduct(@RequestBody ProductDTO requestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/product/remove", consumes = "application/json")
    ResponseDTO<ProductDTO> removeProduct(@RequestBody ProductDTO requestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "/v1/product/search", consumes = "application/json")
    DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(@RequestBody DataTableRequestDTO<ProductDTO> requestDTO);
}
