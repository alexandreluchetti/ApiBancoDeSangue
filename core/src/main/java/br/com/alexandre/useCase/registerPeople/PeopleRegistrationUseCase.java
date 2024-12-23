package br.com.alexandre.useCase.registerPeople;


import br.com.alexandre.entities.Person;

import java.util.List;

public interface PeopleRegistrationUseCase {

    List<Person> peopleRegistration(List<Person> people);
}
