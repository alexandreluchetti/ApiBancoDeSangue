package br.com.alexandre.BancoDeSangue.core.useCase.getAllPeople;

import br.com.alexandre.BancoDeSangue.core.entities.Person;

import java.util.List;

public interface GetAllPeopleUseCase {

    List<Person> getPersonList();
}
