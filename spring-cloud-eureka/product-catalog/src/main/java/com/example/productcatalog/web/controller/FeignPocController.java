package com.example.productcatalog.web.controller;


import com.example.productcatalog.dto.*;
import com.example.productcatalog.enums.ProductType;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
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
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(2L);
        productDTO2.setCatalogId(4L);
        productDTO2.setCatalogName("Sample catalog Name 2");
        productDTO2.setType(ProductType.T_SHIRT);
        productDTOS.add(productDTO2);
        return productDTOS;
    }

    public List<ErrorDTO> createTestErrors() {
        return Arrays.asList(new ErrorDTO("Code 1", "Message 1"), new ErrorDTO("Code 1", "Message 1"));
    }

    private ResponseDTO<List<ProductDTO>> generateTestResponseDTO() {
        return new ResponseDTO<List<ProductDTO>>(Boolean.TRUE,
                getMessage("request.processed.successfully"),
                generateTestData(), createTestErrors());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "consumeJsonAndProduceJson")
    ResponseDTO<List<ProductDTO>> consumeJsonAndProduceJson(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_XML_VALUE,
            value = "consumeJsonAndProduceXml")
    ResponseDTO<List<ProductDTO>> consumeJsonAndProduceXml(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_XML_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "consumeXmlAndProduceJson")
    ResponseDTO<List<ProductDTO>> consumeXmlAndProduceJson(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_XML_VALUE, produces = APPLICATION_XML_VALUE,
            value = "consumeXmlAndProduceXml")
    ResponseDTO<List<ProductDTO>> consumeXmlAndProduceXml(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO,
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
    ResponseDTO<List<ProductDTO>> consumeMultiPartAlongWithOtherDataTooAndProduceJson(@RequestBody UserDTO userDTO) {
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = MULTIPART_FORM_DATA_VALUE,
            value = "consumeJsonAndProduceMultiPart")
    ResponseDTO<List<ProductDTO>> consumeJsonAndProduceMultiPart(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        log.info(String.format("Received request:- %s", dataTableRequestDTO));
        return generateTestResponseDTO();
    }

    @RequestMapping(method = RequestMethod.POST, value = "downloadFileAsAttachment")
    ResponseEntity downloadFileAsAttachment() {
        return null;
    }

    @RequestMapping(path = "sample-request")
    DataTableRequestDTO<List<ProductDTO>> getSampleDataTableRequestDTO() {
        DataTableRequestDTO<List<ProductDTO>> requestDTO = new DataTableRequestDTO<>();
        requestDTO.setPageIndex(13);
        requestDTO.setPageSize(27);
        requestDTO.setQuery(generateTestData());
        return requestDTO;
    }

    @RequestMapping(path = "sample-request-bind",
            consumes = {MediaType.APPLICATION_XML_VALUE, APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, APPLICATION_JSON_UTF8_VALUE})
    ResponseDTO<ProductDTO> tryToBindToResponseDTOWithSimpleData(@RequestBody ResponseDTO<ProductDTO> responseDTO) {
        return responseDTO;
    }

    @RequestMapping(path = "sample-request-bind-list", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    ResponseDTO<List<ProductDTO>> tryToBindToResponseDTOWithListData(@RequestBody ResponseDTO<List<ProductDTO>> responseDTO) {
        return responseDTO;
    }

}
