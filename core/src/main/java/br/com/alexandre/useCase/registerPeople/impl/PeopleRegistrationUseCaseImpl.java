package br.com.alexandre.useCase.registerPeople.impl;

import br.com.alexandre.entities.Person;
import br.com.alexandre.repository.BancoDeSangueRepository;
import br.com.alexandre.useCase.registerPeople.PeopleRegistrationUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PeopleRegistrationUseCaseImpl implements PeopleRegistrationUseCase {

    private static final Logger logger = LoggerFactory.getLogger(PeopleRegistrationUseCaseImpl.class);

    private final BancoDeSangueRepository repository;

    public PeopleRegistrationUseCaseImpl(BancoDeSangueRepository repository) {
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
