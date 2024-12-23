package br.com.alexandre.useCase.getAvarageAgePerBloodType.impl;

import br.com.alexandre.entities.Person;
import br.com.alexandre.entities.enums.BloodTypeEnum;
import br.com.alexandre.repository.BancoDeSangueRepository;
import br.com.alexandre.useCase.getAvarageAgePerBloodType.GetAvarageAgePerBloodTypeUseCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAvarageAgePerBloodTypeUseCaseImpl implements GetAvarageAgePerBloodTypeUseCase {

    private final BancoDeSangueRepository repository;

    public GetAvarageAgePerBloodTypeUseCaseImpl(BancoDeSangueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Double> getAvgAgeByBloodType() {
        List<Person> people = repository.getPeople();
        Map<String, Double> avgAgeByBloodType = new HashMap<>();

        for (BloodTypeEnum bloodType : BloodTypeEnum.values()) {
            avgAgeByBloodType.put(bloodType.getValue(), getAvgAge(bloodType, people));
        }

        return avgAgeByBloodType;
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
}
