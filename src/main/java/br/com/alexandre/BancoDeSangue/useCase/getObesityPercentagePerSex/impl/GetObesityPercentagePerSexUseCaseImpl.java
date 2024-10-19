package br.com.alexandre.BancoDeSangue.useCase.getObesityPercentagePerSex.impl;

import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.getObesityPercentagePerSex.GetObesityPercentagePerSexUseCase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.alexandre.BancoDeSangue.entities.SexEnum.FEMALE;
import static br.com.alexandre.BancoDeSangue.entities.SexEnum.MALE;

public class GetObesityPercentagePerSexUseCaseImpl implements GetObesityPercentagePerSexUseCase {

    private static final Integer IMC_30 = 30;
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private final BancoDeSangueRepositoryImplement repository;

    public GetObesityPercentagePerSexUseCaseImpl(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Double> getObesityPercentagePerSex() {
        List<Person> people = repository.getPeople();
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
