package br.com.alexandre.useCase.getAllPeople;


import br.com.alexandre.entities.Person;

import java.util.List;

public interface GetAllPeopleUseCase {

    List<Person> getPersonList();
}
