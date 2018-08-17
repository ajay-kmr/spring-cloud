package com.example.globomartapi.controller.web;


import com.example.globomartapi.controller.BaseController;
import com.example.globomartapi.dto.CatalogDTO;
import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.DataTableResponseDTO;
import com.example.globomartapi.dto.ResponseDTO;
import com.example.globomartapi.service.CatalogService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin/v1/catalog")
@CommonsLog
public class CatalogAdminController extends BaseController {

    @Autowired
    CatalogService catalogService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createCatalog(@RequestBody CatalogDTO requestDTO) {
        ResponseDTO<CatalogDTO> responseDTO = catalogService.createCatalog(requestDTO);
        return getModelAndView("testView1", responseDTO);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchCatalog(@RequestBody DataTableRequestDTO<CatalogDTO> requestDTO) {
        DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> responseDTO = catalogService.searchCatalog(requestDTO);
        return getModelAndView("testView2", responseDTO);
    }

    @RequestMapping(value = "/search2", method = RequestMethod.GET)
    public ModelAndView searchCatalog(@RequestParam(name = "id") Long id) {
        DataTableRequestDTO<CatalogDTO> requestDTO = new DataTableRequestDTO<>();
        CatalogDTO catalogDTO = new CatalogDTO();
        catalogDTO.setId(id);
        requestDTO.setQuery(catalogDTO);
        DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> responseDTO = catalogService.searchCatalog(requestDTO);
        return getModelAndView("testView3", responseDTO);
    }


}
