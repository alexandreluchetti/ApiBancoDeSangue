package br.com.alexandre.useCase.getAllPeople.impl;

import br.com.alexandre.entities.Person;
import br.com.alexandre.repository.BancoDeSangueRepository;
import br.com.alexandre.useCase.getAllPeople.GetAllPeopleUseCase;

import java.util.List;

public class GetAllPeopleUseCaseImpl implements GetAllPeopleUseCase {

    private final BancoDeSangueRepository repository;

    public GetAllPeopleUseCaseImpl(BancoDeSangueRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getPersonList() {
        return repository.getPeople();
    }
}
