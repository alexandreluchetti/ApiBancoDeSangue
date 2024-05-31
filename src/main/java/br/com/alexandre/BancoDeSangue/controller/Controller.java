package br.com.alexandre.BancoDeSangue.controller;

import br.com.alexandre.BancoDeSangue.controller.dto.PersonDto;
import br.com.alexandre.BancoDeSangue.exceptions.EmptyListException;
import br.com.alexandre.BancoDeSangue.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/bancodesangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping(path = "/envia/pessoas")
    @Operation(summary = "Operacao para enviar uma lista de pesoas para o banco de sangue")
    public void savePeople(@RequestBody List<PersonDto> personDtoList) {
        if (personDtoList == null || personDtoList.isEmpty()) {
            throw EmptyListException.noPersonAdded();
        }

        service.peopleRegistration(personDtoList.stream().map(PersonDto::toPerson).toList());
    }

    @GetMapping(path = "/pessoas")
    @Operation(summary = "Operacao para buscar uma lista de pessoas no banco de dados")
    public List<PersonDto> getPeople() {
        try {
            return service.getPersonList();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @GetMapping(path = "/pessoas/estados")
    @Operation(summary = "Operacao para buscar a quantidade de candidatos em cada state do Brasil")
    public Map<String, Integer> candidatesPerState() {
        try {
            return service.getCandidatesPerState();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @GetMapping(path = "/percentual/obesos/sexo")
    @Operation(summary = "Operacao para buscar o percentual de obesos por sex")
    public Map<String, Double> percentageOverWeightPeopleBySex() {
        try {
            return service.getPercentageOverWeightPeopleBySex();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @GetMapping(path = "/media/idade/tiposanguineo")
    @Operation(summary = "Operacao para buscar idade media por cada tipo sanguineo")
    public Map<String, Double> avgAgeByBloodType() {
        try {
            return service.getAvgAgeByBloodType();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @GetMapping(path = "/quantidade/doadores/tiposanguineo/receptor")
    @Operation(summary = "Operacao para buscar a quantidade de doadores para cada tipo sanguineo receptor")
    public Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient() {
        try {
            return service.amountOfDonorsForEachBloodTypeRecipient();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @GetMapping(path = "/quantidade/receptores/tiposanguineo/doador")
    @Operation(summary = "Operacao para buscar a quantidade de receptores para cada tipo sanguineo doador")
    public Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor() {
        try {
            return service.amountOfRecipientsForEachBloodTypeDonor();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @GetMapping(path = "/media/imc/decada")
    @Operation(summary = "Operacao para buscar a media de IMC por cada decada")
    public Map<String, Double> averageBmiPerDecade() {
        try {
            return service.averageBmiPerDecade();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

}
