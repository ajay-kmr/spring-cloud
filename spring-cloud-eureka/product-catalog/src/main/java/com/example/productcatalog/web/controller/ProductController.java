package com.example.productcatalog.web.controller;


import com.example.productcatalog.dto.DataTableRequestDTO;
import com.example.productcatalog.dto.DataTableResponseDTO;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.dto.ResponseDTO;
import com.example.productcatalog.service.ProductService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/product")
@CommonsLog
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseDTO<ProductDTO> createProduct(@RequestBody ProductDTO requestDTO) {
        return productService.createProduct(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseDTO<ProductDTO> removeProduct(@RequestBody ProductDTO requestDTO) {
        return productService.removeProduct(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public DataTableResponseDTO<List<ProductDTO>> searchProduct(@RequestBody DataTableRequestDTO<ProductDTO> requestDTO) {
        return productService.searchProduct(requestDTO);
    }
}
