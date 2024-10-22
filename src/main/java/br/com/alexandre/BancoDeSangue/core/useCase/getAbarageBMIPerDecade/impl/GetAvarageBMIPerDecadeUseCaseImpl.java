package br.com.alexandre.BancoDeSangue.core.useCase.getAbarageBMIPerDecade.impl;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getAbarageBMIPerDecade.GetAvarageBMIPerDecadeUseCase;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAvarageBMIPerDecadeUseCaseImpl implements GetAvarageBMIPerDecadeUseCase {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private final BancoDeSangueRepositoryImplement repository;

    public GetAvarageBMIPerDecadeUseCaseImpl(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Double> getAvarageBMIPerDacade() {
        List<Person> people = repository.getPeople();
        Map<String, Double> averageBmiPerDecade = getAvgBmi(people);
        return averageBmiPerDecade;
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

            int firstNumber = Integer.parseInt(String.valueOf(charArray[0]));
            int secondNumber;
            if (String.valueOf(charArray[1]).equals("0")) {
                secondNumber = firstNumber * 10;
                firstNumber = secondNumber - 9;
            } else {
                firstNumber = (firstNumber * 10) + 1;
                secondNumber = firstNumber + 9;
            }

            String decadeName = firstNumber + " a " + secondNumber;
            decadesNameMap.put(decadeName, 0.0);
        }
        return decadesNameMap;
    }

    private Double formatDouble(Double value) {
        String stringValue = DECIMAL_FORMAT.format(value).replace(",", ".");
        return Double.parseDouble(stringValue);
    }
}
