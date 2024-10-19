package br.com.alexandre.BancoDeSangue.core.useCase.getPeoplePerState.impl;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getPeoplePerState.GetPeoplePerStateUseCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetPeoplePerStateUseCaseImpl implements GetPeoplePerStateUseCase {

    private final BancoDeSangueRepositoryImplement repository;

    public GetPeoplePerStateUseCaseImpl(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Integer> getPeoplePerState() {
        List<Person> people = repository.getPeople();
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
}
