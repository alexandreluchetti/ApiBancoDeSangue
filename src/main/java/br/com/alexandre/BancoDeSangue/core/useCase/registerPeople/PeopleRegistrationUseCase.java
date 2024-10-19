package br.com.alexandre.BancoDeSangue.core.useCase.registerPeople;

import br.com.alexandre.BancoDeSangue.core.entities.Person;

import java.util.List;

public interface PeopleRegistrationUseCase {

    List<Person> peopleRegistration(List<Person> people);
}
