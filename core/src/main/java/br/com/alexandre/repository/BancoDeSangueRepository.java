package br.com.alexandre.repository;

import br.com.alexandre.entities.Person;

import java.util.List;

public interface BancoDeSangueRepository {

   List<Person> getPeople();

   void register(Person person);
}
