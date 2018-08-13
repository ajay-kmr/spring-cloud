package com.example.globomartapi.client;


import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.ProductDTO;
import com.example.globomartapi.dto.ResponseDTO;
import com.example.globomartapi.dto.UserDTO;
import feign.Headers;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RequestMapping("v1/feign/poc")
public interface FeignPocApiClient {
/*
    Content-Type indicates the content type of the body of the request.

    Accept indicates the expected content type of the response.
*/

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "consumeJsonAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveJson(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "consumeJsonAndProduceXml")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveXml(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @Headers("Content-Type: application/xml")
    @RequestMapping(method = RequestMethod.POST, value = "consumeXmlAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendXmlAndReceiveJson(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @ResponseBody
    @Headers("Content-Type: application/xml")
    @RequestMapping(method = RequestMethod.POST, value = "consumeXmlAndProduceXml",
            produces = APPLICATION_XML_VALUE, consumes = APPLICATION_XML_VALUE)
    ResponseDTO<ProductDTO> sendXmlAndReceiveXml(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @Headers("Content-Type: multipart/form-data")
    @RequestMapping(method = RequestMethod.POST, value = "consumeMultiPartAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendMultiPartAndReceiveJson(@RequestParam("file") MultipartFile multipartFile);

    @Headers("Content-Type: multipart/form-data")
    @RequestMapping(value = "/uploadMultipleMultPartFile", method = RequestMethod.POST)
    public ResponseDTO<List<ProductDTO>> uploadMultiFile(@RequestParam("files") MultipartFile[] files, ModelMap modelMap);

    /**
     * Try to consume files along with some additional data eg profilePic, firstName, lastName etc
     *
     * @param userDTO
     * @return
     */

    @Headers("Content-Type: multipart/form-data")
    @RequestMapping(method = RequestMethod.POST, value = "consumeMultiPartAlongWithOtherDataTooAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendMultiPartAlongWithOtherDataTooAndReceiveJson(UserDTO userDTO);

    @Headers("Content-Type: application/json")
    @RequestMapping(method = RequestMethod.POST, value = "consumeJsonAndProduceMultiPart")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveMultiPart(DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @RequestMapping(method = RequestMethod.POST, value = "downloadFileAsAttachment")
    ResponseEntity downloadFileAsAttachment();
}
