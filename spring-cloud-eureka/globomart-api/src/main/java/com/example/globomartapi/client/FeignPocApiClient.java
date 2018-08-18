package com.example.globomartapi.client;


import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.ProductDTO;
import com.example.globomartapi.dto.ResponseDTO;
import com.example.globomartapi.dto.UserDTO;
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

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "consumeJsonAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveJson(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "consumeJsonAndProduceXml")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveXml(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "consumeXmlAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendXmlAndReceiveJson(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "consumeXmlAndProduceXml",
            produces = APPLICATION_XML_VALUE, consumes = APPLICATION_XML_VALUE)
    ResponseDTO<ProductDTO> sendXmlAndReceiveXml(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "consumeMultiPartAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendMultiPartAndReceiveJson(@RequestParam("file") MultipartFile multipartFile);

    @ResponseBody
    @RequestMapping(value = "/uploadMultipleMultPartFile", method = RequestMethod.POST)
    public ResponseDTO<List<ProductDTO>> uploadMultiFile(@RequestParam("files") MultipartFile[] files, ModelMap modelMap);

    /**
     * Try to consume files along with some additional data eg profilePic, firstName, lastName etc
     *
     * @param userDTO
     * @return
     */

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "consumeMultiPartAlongWithOtherDataTooAndProduceJson")
    ResponseDTO<List<ProductDTO>> sendMultiPartAlongWithOtherDataTooAndReceiveJson(@RequestBody UserDTO userDTO);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "consumeJsonAndProduceMultiPart")
    ResponseDTO<List<ProductDTO>> sendJsonAndReceiveMultiPart(@RequestBody DataTableRequestDTO<List<ProductDTO>> dataTableRequestDTO);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "downloadFileAsAttachment")
    ResponseEntity downloadFileAsAttachment();
}
