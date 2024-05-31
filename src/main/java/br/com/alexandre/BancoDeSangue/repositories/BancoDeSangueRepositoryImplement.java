package br.com.alexandre.BancoDeSangue.repositories;

import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.entities.PersonDB;
import br.com.alexandre.BancoDeSangue.exceptions.EmptyListException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BancoDeSangueRepositoryImplement {

    private final BancoDeSangueRepository repository;

    @Autowired
    public BancoDeSangueRepositoryImplement(BancoDeSangueRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Person> getPeople() {
        try {
            return this.repository.getPeople(null).stream().map(PersonDB::toPerson).toList();
        } catch (Exception exception) {
            throw EmptyListException.noPersonFound();
        }
    }

    public void register(Person person) {
        this.repository.register(
                person.getName(),
                person.getCpf(),
                person.getRg(),
                person.getFormattedDate(),
                person.getSex().getValueChar(),
                person.getMother(),
                person.getFather(),
                person.getEmail(),
                person.getAddress().getCep(),
                person.getAddress().getAddress(),
                person.getAddress().getNumber(),
                person.getAddress().getNeighborhood(),
                person.getAddress().getCity(),
                person.getAddress().getState(),
                person.getHomePhone(),
                person.getCellphone(),
                person.getHeight(),
                person.getWeight(),
                person.getBloodType().getValue()
        );
    }
}
