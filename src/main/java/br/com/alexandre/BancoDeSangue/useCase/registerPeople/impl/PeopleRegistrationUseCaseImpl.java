package br.com.alexandre.BancoDeSangue.useCase.registerPeople.impl;

import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.useCase.registerPeople.PeopleRegistrationUseCase;

import java.util.List;

public class PeopleRegistrationUseCaseImpl implements PeopleRegistrationUseCase {

    private final BancoDeSangueRepositoryImplement repository;

    public PeopleRegistrationUseCaseImpl(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> peopleRegistration(List<Person> people) {
        people.forEach(person -> {
            try {
                this.repository.register(person);
            } catch (Exception exception) {
                System.out.println("IMPOSSIVEL REGISTRAR PESSOA: " + exception.getMessage());
            }
        });
        return repository.getPeople();
    }
}
