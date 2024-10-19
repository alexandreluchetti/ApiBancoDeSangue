package br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople.impl;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople.GetAllPeopleUseCase;

import java.util.List;

public class GetAllPeopleUseCaseImpl implements GetAllPeopleUseCase {

    private final BancoDeSangueRepositoryImplement repository;

    public GetAllPeopleUseCaseImpl(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getPersonList() {
        return repository.getPeople();
    }
}
