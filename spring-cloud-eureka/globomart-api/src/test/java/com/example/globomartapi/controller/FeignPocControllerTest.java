package com.example.globomartapi.controller;

import com.example.globomartapi.GlobomartApiApplication;
import com.example.globomartapi.client.FeignPocApiClient;
import com.example.globomartapi.dto.DataTableRequestDTO;
import com.example.globomartapi.dto.ProductDTO;
import com.example.globomartapi.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FeignPocController.class)
@ContextConfiguration(classes = {GlobomartApiApplication.class, WebApplicationContext.class})
public class FeignPocControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private FeignPocController controller;

    @Mock
    private FeignPocApiClient service;

    private static String getSampleRequestJson() {
        return "{\n" +
                "  \"sortColumn\": \"createdDate\",\n" +
                "  \"sortOrder\": \"ASC\",\n" +
                "  \"pageSize\": 10,\n" +
                "  \"pageIndex\": 0,\n" +
                "  \"fetchAllRecords\": false,\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"type\": \"FORMAL_SHIRT\",\n" +
                "      \"catalogId\": 2,\n" +
                "      \"catalogName\": \"Sample catalog name for testing\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"type\": \"FORMAL_SHIRT\",\n" +
                "      \"catalogId\": 4,\n" +
                "      \"catalogName\": \"Sample catalog name for testing\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    private static DataTableRequestDTO<List<ProductDTO>> getSampleRequest() {
        DataTableRequestDTO<List<ProductDTO>> requestDTO = new DataTableRequestDTO<>();
        requestDTO.setSortColumn("catalogName");
        requestDTO.setSortOrder("ASC");
        requestDTO.setPageSize(25);
        requestDTO.setPageIndex(3);
        requestDTO.setFetchAllRecords(Boolean.TRUE);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCatalogId(45L);
        productDTO.setCatalogName("Test Category Name 45");
        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setCatalogId(25L);
        productDTO2.setCatalogName("Test Category Name 25");

        requestDTO.setQuery(Arrays.asList(productDTO, productDTO2));
        return requestDTO;
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testCatalogSearchView() throws Exception {
        ResponseDTO<List<ProductDTO>> responseDTO = new ResponseDTO<>();
        String message = "Test Message";
        responseDTO.setMessage(message);

        String expectedJson = "{\"status\":true,\"message\":\"Test Message\",\"data\":null,\"errors\":[]}";

        Mockito.doReturn(responseDTO).when(service).sendJsonAndReceiveJson(any(DataTableRequestDTO.class));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/feign/poc/sendJsonAndReceiveJson")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(getSampleRequestJson())
                .content(convertObjectToJsonBytes(getSampleRequest()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Boolean.TRUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        JSONAssert.assertEquals(expectedJson, response.getContentAsString(), false);

        verify(service, Mockito.times(1)).sendJsonAndReceiveJson(any(DataTableRequestDTO.class));
        verifyNoMoreInteractions(service);
    }
}