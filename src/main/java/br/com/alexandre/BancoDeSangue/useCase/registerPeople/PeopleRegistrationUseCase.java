package br.com.alexandre.BancoDeSangue.useCase.registerPeople;

import br.com.alexandre.BancoDeSangue.entities.Person;

import java.util.List;

public interface PeopleRegistrationUseCase {

    List<Person> peopleRegistration(List<Person> people);
}
