package com.example.productcatalog.service.impl;

import com.example.productcatalog.dao.entity.Catalog;
import com.example.productcatalog.dao.entity.Product;
import com.example.productcatalog.dao.repoService.CatalogRepoService;
import com.example.productcatalog.dao.repoService.ProductRepoService;
import com.example.productcatalog.databinder.ProductDataBinder;
import com.example.productcatalog.dto.DataTableRequestDTO;
import com.example.productcatalog.dto.DataTableResponseDTO;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.dto.ResponseDTO;
import com.example.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

    @Autowired
    ProductRepoService productRepoService;

    @Autowired
    CatalogRepoService catalogRepoService;

    @Override
    @Transactional
    public ResponseDTO<ProductDTO> createProduct(ProductDTO requestDTO) {
        ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(Boolean.FALSE, getMessage("unable.to.save.record"), requestDTO);
        //TODO:- Validate Product eg check for duplicate product etc
        Catalog catalog = catalogRepoService.findOne(requestDTO.getCatalogId());
        if (catalog == null) {
            responseDTO.setMessage(getMessage("invalid.catalog.id"));
        } else {
            Product product = ProductDataBinder.bind(requestDTO, catalog);
            try {
                product = productRepoService.save(product);
                requestDTO.setId(product.getId());
                responseDTO.setMessage(getMessage("record.successfully.saved"));
                responseDTO.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseDTO;
    }

    @Override
    @Transactional
    public ResponseDTO<ProductDTO> removeProduct(ProductDTO requestDTO) {
        ResponseDTO<ProductDTO> responseDTO = new ResponseDTO<ProductDTO>(Boolean.FALSE, getMessage("unable.to.remove.catalog.product"), requestDTO);
        //TODO:- Validate Product eg check for duplicate product etc
        Product product = productRepoService.findOne(requestDTO.getId());
        if (product == null) {
            responseDTO.setMessage(getMessage("invalid.product.id"));
        } else {
            try {
                product.setDeleted(Boolean.TRUE);
                product = productRepoService.save(product);
                responseDTO.setMessage(getMessage("record.deleted.successfully"));
                responseDTO.setStatus(Boolean.TRUE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseDTO;
    }

    public DataTableResponseDTO<List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO) {
        return productRepoService.searchProduct(requestDTO);
    }
}
