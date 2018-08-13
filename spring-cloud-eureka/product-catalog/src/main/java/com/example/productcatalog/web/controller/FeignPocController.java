package com.example.productcatalog.web.controller;


import com.example.productcatalog.dto.DataTableRequestDTO;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.dto.ResponseDTO;
import com.example.productcatalog.dto.UserDTO;
import com.example.productcatalog.enums.ProductType;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("v1/feign/poc")
@CommonsLog
public class FeignPocController extends BaseController {
/*
    Content-Type indicates the content type of the body of the request.

    Accept indicates the expected content type of the response.
*/

    private static List<ProductDTO> generateTestData() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setCatalogId(2L);
        productDTO.setCatalogName("Sample catalog Name 1");
        productDTO.setType(ProductType.FORMAL_SHIRT);
        productDTOS.add(productDTO);
        return productDTOS;
    }

    private ResponseDTO<List<ProductDTO>> generateTestResponseDTO() {
        return new ResponseDTO<List<ProductDTO>>(Boolean.TRUE,
                getMessage("request.processed.successfully"),
                generateTestData());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "consumeJsonAndProduceJson")
    ResponseDTO<List<ProductDTO>> consumeJsonAndProduceJson(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_XML_VALUE,
            value = "consumeJsonAndProduceXml")
    ResponseDTO<List<ProductDTO>> consumeJsonAndProduceXml(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_XML_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "consumeXmlAndProduceJson")
    ResponseDTO<List<ProductDTO>> consumeXmlAndProduceJson(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_XML_VALUE, produces = APPLICATION_XML_VALUE,
            value = "consumeXmlAndProduceXml")
    ResponseDTO<List<ProductDTO>> consumeXmlAndProduceXml(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO,
                                                          @RequestHeader MultiValueMap headers) {

        log.info(String.format("Received request header:- %s", headers));
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "consumeMultiPartAndProduceJson")
    ResponseDTO<List<ProductDTO>> consumeMultiPartAndProduceJson(@RequestParam("file") MultipartFile multipartFile) {
        return generateTestResponseDTO();
    }

    @RequestMapping(value = "/uploadMultipleMultPartFile", method = RequestMethod.POST)
    public ResponseDTO<List<ProductDTO>> uploadMultiFile(@RequestParam("files") MultipartFile[] files, ModelMap modelMap) {
        modelMap.addAttribute("files", files);
        return generateTestResponseDTO();
    }

    /**
     * Try to consume files along with some additional data eg profilePic, firstName, lastName etc
     *
     * @param userDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "consumeMultiPartAlongWithOtherDataTooAndProduceJson")
    ResponseDTO<List<ProductDTO>> consumeMultiPartAlongWithOtherDataTooAndProduceJson(UserDTO userDTO) {
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = MULTIPART_FORM_DATA_VALUE,
            value = "consumeJsonAndProduceMultiPart")
    ResponseDTO<List<ProductDTO>> consumeJsonAndProduceMultiPart(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, value = "downloadFileAsAttachment")
    ResponseEntity downloadFileAsAttachment() {
        return null;
    }
}
