package br.com.alexandre.BancoDeSangue.useCase.getAllPeople;

import br.com.alexandre.BancoDeSangue.entities.Person;

import java.util.List;

public interface GetAllPeopleUseCase {

    List<Person> getPersonList();
}
