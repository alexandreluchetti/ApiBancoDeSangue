package br.com.alexandre.BancoDeSangue.core.useCase.registerPeople.impl;

import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.registerPeople.PeopleRegistrationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PeopleRegistrationUseCaseImpl implements PeopleRegistrationUseCase {

    private static final Logger logger = LoggerFactory.getLogger(PeopleRegistrationUseCaseImpl.class);

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
                logger.warn("IMPOSSIVEL REGISTRAR PESSOA: {}", exception.getMessage());
            }
        });
        return repository.getPeople();
    }
}
