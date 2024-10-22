package br.com.alexandre.BancoDeSangue.entrypoint.getAvarageAgePerBloodType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetAvarageAgePerBloodTypeRestController.class)
public class GetAvarageAgePerBloodTypeRestControllerTest {


    @MockBean
    private GetAvarageAgePerBloodTypeRestController controller;

    private final MockMvc mockMvc;

    @Autowired
    public GetAvarageAgePerBloodTypeRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(
                get("/v1/bancodesangue/media/idade/tiposanguineo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
