package br.com.alexandre.BancoDeSangue.entrypoint.getPeoplePerState;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GetPeoplePerStateRestController.class)
public class GetPeoplePerStateRestControllerTest {

    @MockBean
    private GetPeoplePerStateRestController controller;

    private final MockMvc mockMvc;

    @Autowired
    public GetPeoplePerStateRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void getPeoplePerStateRestControllerTest() throws Exception {
        mockMvc.perform(
                        get("/v1/bancodesangue/pessoas/estados").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
