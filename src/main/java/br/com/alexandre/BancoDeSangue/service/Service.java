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

    private static List<Person> pessoasStatic = null;

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

    public List<PersonDto> getPeopleByCpf(String cpf) {
        return getPeople(cpf).stream().map(Person::toDto).toList();
    }

    public Map<String, Integer> getCandidatesPerState() {
        List<Person> people = getPeople("");
        Map<String, Integer> peopleByState = new HashMap<>();

        for (Person person : people) {
            String state = person.getAddress().getState();
            if (peopleByState.containsKey(state)) {
                int quantidade = peopleByState.get(state) + 1;
                peopleByState.put(state, quantidade);
            } else {
                peopleByState.put(state, 1);
            }
        }

        return peopleByState;
    }

    public Map<String, Double> getAvgAgeByBloodType() {
        List<Person> people = getPeople("");
        Map<String, Double> avgAgeByBloodType = new HashMap<>(Map.of());

        for (BloodTypeEnum bloodType : BloodTypeEnum.values()) {
            avgAgeByBloodType.put(bloodType.getValue(), getAvgAge(bloodType, people));
        }

        return avgAgeByBloodType;
    }

    public Map<String, Double> getPercentageOverWeightPeopleBySex() {
        List<Person> people = getPeople("");
        List<Person> men = new ArrayList<>();
        List<Person> women = new ArrayList<>();

        people.forEach(person -> {
            if (person.getSex().equals(MALE)) men.add(person);
            else women.add(person);
        });

        Map<String, Double> map = new HashMap<>(Map.of(
                MALE.getValue(), getAvgPercentage(men),
                FEMALE.getValue(), getAvgPercentage(women)
        ));

        return map;
    }

    public Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient() {
        List<Person> people = getPeople("");
        Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient = new HashMap<>();

        for (BloodTypeEnum bloodType : BloodTypeEnum.tipos) {
            amountOfDonorsForEachBloodTypeRecipient.put(
                    bloodType.getValue(),
                    getAmountOfBloodTypeDonors(people, BloodTypeEnum.recieveFrom(bloodType))
            );
        }

        return amountOfDonorsForEachBloodTypeRecipient;
    }

    public Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor() {
        List<Person> people = getPeople("");
        Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor = new HashMap<>();

        for (BloodTypeEnum bloodType : BloodTypeEnum.tipos) {
            amountOfRecipientsForEachBloodTypeDonor.put(
                    bloodType.getValue(),
                    getAmountOfBloodTypeDonors(people, BloodTypeEnum.donateTo(bloodType))
            );
        }

        return amountOfRecipientsForEachBloodTypeDonor;
    }

    public Map<String, Double> averageBmiPerDecade() {
        List<Person> people = getPeople("");
        Map<String, Double> averageBmiPerDecade = getAvgBmi(people);
        return averageBmiPerDecade;
    }

    private List<Person> getPeople(String cpf) {
        if (pessoasStatic == null) {
            pessoasStatic = personRepository.getPeopleByCpf(cpf);
        }
        return pessoasStatic;
    }

    private Map<String, Double> getAvgBmi(List<Person> people) {
        Map<String, Double> avgBmiPerDecade = getDecadesName(people);
        Map<String, Double> map = new HashMap<>();
        for (String decade : avgBmiPerDecade.keySet()) {
            double imc = 0;
            int amount = 0;
            String[] decadesNumber = decade.split("a");
            int startDecade = Integer.parseInt(decadesNumber[0].trim());
            int endDecade = Integer.parseInt(decadesNumber[1].trim());

            for (Person person : people) {
                int age = person.getAge();
                if (age >= startDecade && age <= endDecade) {
                    imc += person.getImc();
                    amount++;
                }
            }

            Double avgBmi = imc / amount;
            map.put(decade, formatDouble(avgBmi));
        }

        return map;
    }

    private Map<String, Double> getDecadesName(List<Person> people) {
        Map<String, Double> decadesNameMap = new HashMap<>();
        for (Person person : people) {
            char[] charArray = String.valueOf(person.getAge()).toCharArray();

            String decadeName = "";
            int firstNumber = Integer.parseInt(String.valueOf(charArray[0]));
            int secondNumber;
            if (String.valueOf(charArray[1]).equals("0")) {
                secondNumber = firstNumber * 10;
                firstNumber = secondNumber - 9;
            } else {
                firstNumber = (firstNumber * 10) + 1;
                secondNumber = firstNumber + 9;
            }

            decadeName = firstNumber + " a " + secondNumber;
            decadesNameMap.put(decadeName, 0.0);
        }
        return decadesNameMap;
    }

    private Integer getAmountOfBloodTypeDonors(List<Person> people, List<BloodTypeEnum> bloodTypes) {
        int amount = 0;
        for (BloodTypeEnum bloodType : bloodTypes) {
            for (Person person : people) {
                if (person.getBloodType() == bloodType) amount++;
            }
        }
        return amount;
    }

    private Double getAvgAge(BloodTypeEnum bloodType, List<Person> people) {
        int age = 0;
        int amountOfPeople = 0;
        for (Person person : people) {
            if (person.getBloodType().equals(bloodType)) {
                age += person.getAge();
                amountOfPeople++;
            }
        }

        if (age == 0 && amountOfPeople == 0) return 0.0;
        return (double) (age / amountOfPeople);
    }

    private Double getAvgPercentage(List<Person> people) {
        AtomicInteger overWeights = new AtomicInteger();
        people.forEach(person -> {
            if (person.getImc() > IMC_30) overWeights.getAndIncrement();
        });

        Double media = ((double) (overWeights.get() * 100) / people.size());
        return formatDouble(media);
    }

    private Double formatDouble(Double value) {
        String stringValue = DECIMAL_FORMAT.format(value).replace(",", ".");
        return Double.parseDouble(stringValue);
    }

}