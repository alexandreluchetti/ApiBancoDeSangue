package br.com.alexandre.BancoDeSangue.entrypoint.getAvarageBMIPerDecade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetAvarageBMIPerDecadeRestController.class)
public class GetAvarageBMIPerDecadeRestControllerTest {

    @MockBean
    private GetAvarageBMIPerDecadeRestController controller;

    private final MockMvc mockMvc;

    @Autowired
    public GetAvarageBMIPerDecadeRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getAvarageBMIPerDecadeRestControllerTest() throws Exception {
        mockMvc.perform(
                get("/v1/bancodesangue/media/imc/decada").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
