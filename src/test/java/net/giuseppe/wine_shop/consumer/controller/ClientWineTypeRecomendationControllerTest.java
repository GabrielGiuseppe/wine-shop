package net.giuseppe.wine_shop.consumer.controller;

import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.service.ClientWineTypeRecomendationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientWineTypeRecomendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientWineTypeRecomendationService service;

    @Test
    public void testGetGeneralWineTypeRecomendation() throws Exception {
        BaseResponse mockResponse = new BaseResponse<>("Espumante");
        when(service.getGeneralWineTypeRecomendation()).thenReturn(mockResponse);

        mockMvc.perform(get("/recomendacao/cliente/tipo")
                        .contentType(MediaType.ALL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWineTypeRecomendation() throws Exception {
        String consumerName = "Andreia_Emanuelly_da_Mata";
        BaseResponse mockResponse = new BaseResponse<>("Rosé");
        when(service.getWineTypeRecomendation(consumerName)).thenReturn(mockResponse);

        mockMvc.perform(get("/recomendacao/{cliente}/tipo", consumerName)
                        .contentType(MediaType.ALL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}