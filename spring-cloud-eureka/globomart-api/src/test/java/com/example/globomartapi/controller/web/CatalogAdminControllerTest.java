package com.example.globomartapi.controller.web;

import com.example.globomartapi.GlobomartApiApplication;
import com.example.globomartapi.dto.CatalogDTO;
import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.DataTableResponseDTO;
import com.example.globomartapi.service.CatalogService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {GlobomartApiApplication.class})
@SpringBootTest(classes = {CatalogAdminController.class})
public class CatalogAdminControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CatalogAdminController controller;

    @Mock
    private CatalogService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCatalogSearchView() throws Exception {
        DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> responseDTO = new DataTableResponseDTO<>();
        Mockito.doReturn(responseDTO).when(service).searchCatalog(any(DataTableRequestDTO.class));

        String requestUrl = String.format("/admin/v1/catalog/search2.html?id=%s", 1);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(requestUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("testView3"))
                .andExpect(MockMvcResultMatchers.model().attribute("response", responseDTO))
                .andReturn();
        Assert.assertNotNull(result.getModelAndView());
    }

    @Test
    public void testCatalogSearchView2() throws Exception {
        DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> responseDTO = new DataTableResponseDTO<>();
        Mockito.doReturn(responseDTO).when(service).searchCatalog(any(DataTableRequestDTO.class));

//        ArgumentMatchers.anyInt();

        //Can use: /admin/v1/catalog/search2 or /admin/v1/catalog/search2.html
        String requestUrl = String.format("/admin/v1/catalog/search2?id=%s", 1);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(requestUrl))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("testView3"))
                .andExpect(MockMvcResultMatchers.model().attribute("response", responseDTO))
                .andReturn();
        Assert.assertNotNull(result.getModelAndView());
    }

    @Test
    public void testCatalogSearchView3() throws Exception {
        DataTableResponseDTO<CatalogDTO, List<CatalogDTO>> responseDTO = new DataTableResponseDTO<>();
        Mockito.doReturn(responseDTO).when(service).searchCatalog(any(DataTableRequestDTO.class));

        //Can use: /admin/v1/catalog/search2 or /admin/v1/catalog/search2.html
        String requestUrl = "/admin/v1/catalog/search2";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(requestUrl)
                        .param("id", "1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("testView3"))
                .andExpect(MockMvcResultMatchers.model().attribute("response", responseDTO))
                .andReturn();
        Assert.assertNotNull(result.getModelAndView());

        verify(service, Mockito.times(1)).searchCatalog(any(DataTableRequestDTO.class));
        verifyNoMoreInteractions(service);
    }

}