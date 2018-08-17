package com.example.globomartapi.controller;


import com.example.globomartapi.client.FeignPocApiClient;
import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.ProductDTO;
import com.example.globomartapi.dto.ResponseDTO;
import com.example.globomartapi.dto.UserDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("api/v1/feign/poc")
@CommonsLog
public class FeignPocController extends BaseController {
/*
    Content-Type indicates the content type of the body of the request.

    Accept indicates the expected content type of the response.
*/

    @Autowired
    FeignPocApiClient feignPocApiClient;

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "sendJsonAndReceiveJson")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveJson(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        return feignPocApiClient.sendJsonAndReceiveJson(dataTableRequestDTO);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_XML_VALUE,
            value = "sendJsonAndReceiveXml")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveXml(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        return feignPocApiClient.sendJsonAndReceiveXml(dataTableRequestDTO);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_XML_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "sendXmlAndReceiveJson")
    ResponseDTO<List<ProductDTO>> sendXmlAndReceiveJson(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        return feignPocApiClient.sendXmlAndReceiveJson(dataTableRequestDTO);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_XML_VALUE, produces = APPLICATION_XML_VALUE,
            value = "sendXmlAndReceiveXml")
    ResponseDTO<ProductDTO> sendXmlAndReceiveXml(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        return feignPocApiClient.sendXmlAndReceiveXml(dataTableRequestDTO);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "sendMultiPartAndReceiveJson")
    ResponseDTO<List<ProductDTO>> sendMultiPartAndReceiveJson(@RequestParam("file") MultipartFile multipartFile) {
        return feignPocApiClient.sendMultiPartAndReceiveJson(multipartFile);
    }

    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
    public ResponseDTO<List<ProductDTO>> uploadMultiFile(@RequestParam("files") MultipartFile[] files, ModelMap modelMap) {
        return feignPocApiClient.uploadMultiFile(files, modelMap);
    }

    /**
     * Try to consume files along with some additional data eg profilePic, firstName, lastName etc
     *
     * @param userDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_UTF8_VALUE,
            value = "sendMultiPartAlongWithOtherDataTooAndReceiveJson")
    ResponseDTO<List<ProductDTO>> sendMultiPartAlongWithOtherDataTooAndReceiveJson(@RequestBody UserDTO userDTO) {
        return feignPocApiClient.sendMultiPartAlongWithOtherDataTooAndReceiveJson(userDTO);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_UTF8_VALUE, produces = MULTIPART_FORM_DATA_VALUE,
            value = "sendJsonAndReceiveMultiPart")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveMultiPart(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO) {
        return feignPocApiClient.sendJsonAndReceiveMultiPart(dataTableRequestDTO);
    }

    @RequestMapping(method = RequestMethod.POST, value = "downloadFileAsAttachment")
    ResponseEntity downloadFileAsAttachment() {
        return feignPocApiClient.downloadFileAsAttachment();
    }
}
