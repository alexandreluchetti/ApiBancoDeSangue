package br.com.alexandre.BancoDeSangue.service;

import br.com.alexandre.BancoDeSangue.controller.dto.PersonDto;
import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.entities.BloodTypeEnum;
import br.com.alexandre.BancoDeSangue.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.alexandre.BancoDeSangue.entities.SexEnum.FEMALE;
import static br.com.alexandre.BancoDeSangue.entities.SexEnum.MALE;

@org.springframework.stereotype.Service
public class Service {
     private static final Integer IMC_30 = 30;
     private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private static List<Person> pessoasStatic = new ArrayList<>();

    private PersonRepository personRepository;

    @Autowired
    public Service(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void personRegistration(Person person) {
        personRepository.personRegistration(person);
    }

    public void peopleRegistration(List<Person> people) {
        people.forEach(person -> {
            try {
                personRepository.personRegistration(person);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                throw exception;
            }
        });
    }
    
    public List<Person> buscaPessoas(String cpf) {
        pessoasStatic = (pessoasStatic.size() > 0)
                ? pessoasStatic
                : personRepository.getPeopleByCpf(cpf);

        if (pessoasStatic == null) return new ArrayList<>();
        return pessoasStatic;
    }

    public List<PersonDto> buscaPessoaDto(String cpf) {
        List<Person> people = buscaPessoas(cpf);
        List<PersonDto> personDtos = new ArrayList<>();
        people.forEach(person -> {
            personDtos.add(person.toDto());
        });
        return personDtos;
    }

    public Map<String, Integer> candidatosPorEstado() {
        List<Person> people = buscaPessoas("");
        Map<String, Integer> pessoasPorEstado = new HashMap<>();

        for (Person person : people) {
            String estado = person.getAddress().getState();
            if (pessoasPorEstado.containsKey(estado)) {
                int quantidade = pessoasPorEstado.get(estado) + 1;
                pessoasPorEstado.put(estado, quantidade);
            } else {
                pessoasPorEstado.put(estado, 1);
            }
        }

        return pessoasPorEstado;
    }

    public Map<String, Double> mediaIdadePorTipoSanguineo() {
        List<Person> people = buscaPessoas("");
        Map<String, Double> mediaPorTIpoSanguineo = new HashMap<>(Map.of());

        for (BloodTypeEnum ts : BloodTypeEnum.values()) {
            mediaPorTIpoSanguineo.put(ts.getValue(), getIdadeMedia(ts, people));
        }

        return mediaPorTIpoSanguineo;
    }

    public Map<String, Double> percentualDeObesosPorSexo() {
        List<Person> people = buscaPessoas("");
        List<Person> homens = new ArrayList<>();
        List<Person> mulheres = new ArrayList<>();

        people.forEach(person -> {
            if (person.getSex().equals(MALE)) homens.add(person);
            else mulheres.add(person);
        });

        Map<String, Double> map = new HashMap<>(Map.of(
                MALE.getValue(), getPercentualMedio(homens),
                FEMALE.getValue(), getPercentualMedio(mulheres)
        ));

        return map;
    }

    public Map<String, Integer> quantidadeDoadoresParaCadaTipoSanguineoReceptor() {
        List<Person> people = buscaPessoas("");
        Map<String, Integer> quantidadeDeDoadoresPorTipoSanguineoReceptor = new HashMap<>();

        for (BloodTypeEnum tipoSanguineo : BloodTypeEnum.tipos) {
            quantidadeDeDoadoresPorTipoSanguineoReceptor.put(
                    tipoSanguineo.getValue(),
                    getQuantidadeDoadoresTipoSanguineo(people, BloodTypeEnum.receberDe(tipoSanguineo))
            );
        }

        return quantidadeDeDoadoresPorTipoSanguineoReceptor;
    }

    public Map<String, Integer> quantidadeReceptoresParaCadaTipoSanguineoDoador() {
        List<Person> people = buscaPessoas("");
        Map<String, Integer> quantidadeDeReceptoresPorTipoSanguineoDoador = new HashMap<>();

        for (BloodTypeEnum tipoSanguineo : BloodTypeEnum.tipos) {
            quantidadeDeReceptoresPorTipoSanguineoDoador.put(
                    tipoSanguineo.getValue(),
                    getQuantidadeDoadoresTipoSanguineo(people, BloodTypeEnum.doarPara(tipoSanguineo))
            );
        }

        return quantidadeDeReceptoresPorTipoSanguineoDoador;
    }

    public Map<String, Double> imcMedioPorDecada() {
        List<Person> people = buscaPessoas("");
        Map<String, Double> imcMedioPorDecada = getImcMedio(people);
        return imcMedioPorDecada;
    }

    private Map<String, Double> getImcMedio(List<Person> people) {
        Map<String, Double> imcMedioPorDecada = getNomeDecadas(people);
        Map<String, Double> map = new HashMap<>();
        for (String nomeDecada : imcMedioPorDecada.keySet()) {
            double imc = 0;
            int quantidade = 0;
            String[] numerosDecada = nomeDecada.split("a");
            Integer inicioDecada = Integer.parseInt(numerosDecada[0].trim());
            Integer fimDecada = Integer.parseInt(numerosDecada[1].trim());

            for (Person person : people) {
                int idade = person.getAge();
                if (idade >= inicioDecada && idade <= fimDecada) {
                    imc += person.getImc();
                    quantidade++;
                }
            }

            Double imcMedio = imc / quantidade;
            map.put(nomeDecada, formatDouble(imcMedio));
        }

        return map;
    }

    private Map<String, Double> getNomeDecadas(List<Person> people) {
        Map<String, Double> nomeDecadas = new HashMap<>();
        for (Person person : people) {
            int idade = person.getAge();
            char[] charArray = String.valueOf(idade).toCharArray();

            String nomeDecada = "";
            int primeiroNumero = Integer.parseInt(String.valueOf(charArray[0]));
            int segundoNumero;
            if (String.valueOf(charArray[1]).equals("0")) {
                segundoNumero = primeiroNumero * 10;
                primeiroNumero = segundoNumero - 9;
            } else {
                primeiroNumero = (primeiroNumero * 10) + 1;
                segundoNumero = primeiroNumero + 9;
            }

            nomeDecada = primeiroNumero + " a " + segundoNumero;
            nomeDecadas.put(nomeDecada, 0.0);
        }
        return nomeDecadas;
    }

    private Integer getQuantidadeDoadoresTipoSanguineo(List<Person> people, List<BloodTypeEnum> tipos) {
        int quantidade = 0;
        for (BloodTypeEnum tipoSanguineo : tipos) {
            for (Person person : people) {
                if (person.getBloodType() == tipoSanguineo) quantidade++;
            }
        }
        return quantidade;
    }

    private Double getIdadeMedia(BloodTypeEnum tipoSanguineo, List<Person> people) {
        int idade = 0;
        int quantidadePessoas = 0;
        for (Person person : people) {
            if (person.getBloodType().equals(tipoSanguineo)) {
                idade += person.getAge();
                quantidadePessoas++;
            }
        }

        if (idade == 0 && quantidadePessoas == 0) return 0.0;
        return (double) (idade / quantidadePessoas);
    }

    private Double getPercentualMedio(List<Person> people) {
        AtomicInteger obesos = new AtomicInteger();
        people.forEach(person -> {
            if (person.getImc() > IMC_30) obesos.getAndIncrement();
        });

        Double media = ((double) (obesos.get() * 100) / people.size());
        return formatDouble(media);
    }

    private Double formatDouble(Double value) {
        String stringValue = DECIMAL_FORMAT.format(value).replace(",", ".");
        return Double.parseDouble(stringValue);
    }

}