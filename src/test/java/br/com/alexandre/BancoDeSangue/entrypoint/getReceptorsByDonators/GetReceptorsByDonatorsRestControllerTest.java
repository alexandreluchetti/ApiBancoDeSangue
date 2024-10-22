package br.com.alexandre.BancoDeSangue.entrypoint.getReceptorsByDonators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetReceptorsByDonatorsRestController.class)
public class GetReceptorsByDonatorsRestControllerTest {

    @MockBean
    private GetReceptorsByDonatorsRestController controller;

    private final MockMvc mockMvc;

    @Autowired
    public GetReceptorsByDonatorsRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getReceptorsByDonatorsRestControllerTest() throws Exception {
        mockMvc.perform(
                        get("/v1/bancodesangue/quantidade/receptores/tiposanguineo/doador").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
