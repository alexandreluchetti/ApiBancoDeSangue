package br.com.alexandre.BancoDeSangue.entrypoint;

import br.com.alexandre.BancoDeSangue.core.entities.Address;
import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.core.entities.enums.BloodTypeEnum;
import br.com.alexandre.BancoDeSangue.core.entities.enums.SexEnum;
import br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople.GetAllPeopleUseCase;
import br.com.alexandre.BancoDeSangue.entrypoint.registerPeople.dto.PersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BancoDeSangueRestControllerTest {


    @MockBean
    private GetAllPeopleUseCase useCase;
    private final MockMvc mockMvc;
    private List<PersonDto> personDtoList;

    @Autowired
    public BancoDeSangueRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeEach
    public void setUp() {
        PersonDto personDto1 = new PersonDto("John Doe", "12345678910", "", "", "", "", "", "", "", "", 123, "", "", "", "", "", 1.90, 92.0, "A+");
        PersonDto personDto2 = new PersonDto("Jane Smith", "12345678911", "", "", "", "", "", "", "", "", 123, "", "", "", "", "", 1.90, 92.0, "B-");
        personDtoList = Arrays.asList(personDto1, personDto2);
    }

    @Test
    public void whenGetPeople_thenReturnPersonDtoList() throws Exception {
        when(useCase.getPersonList()).thenReturn(
                personDtoList.stream().map(personDto -> new Person(
                        personDto.name(),
                        personDto.cpf(),
                        personDto.rg(),
                        personDto.birthdate(),
                        SexEnum.getEnum(personDto.sex()),
                        personDto.mother(),
                        personDto.father(),
                        personDto.email(),
                        new Address(
                                personDto.zipcode(),
                                personDto.address(),
                                personDto.number(),
                                personDto.neighborhood(),
                                personDto.city(),
                                personDto.state()
                        ),
                        personDto.homePhone(),
                        personDto.cellphone(),
                        personDto.height(),
                        personDto.weight(),
                        BloodTypeEnum.getEnum(personDto.bloodType())
                )).collect(Collectors.toList())
        );

        mockMvc.perform(get("/v1/bancodesangue/pessoas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(personDtoList)));
    }

    @Test
    public void whenGetPeople_thenHandleException() throws Exception {
        when(useCase.getPersonList()).thenThrow(new RuntimeException("Database Error"));

        mockMvc.perform(get("/v1/bancodesangue/pessoas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
