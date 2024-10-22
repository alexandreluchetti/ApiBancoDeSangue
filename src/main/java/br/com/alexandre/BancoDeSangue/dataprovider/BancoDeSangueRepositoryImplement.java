package br.com.alexandre.BancoDeSangue.dataprovider;

import br.com.alexandre.BancoDeSangue.configuration.exceptions.EmptyListException;
import br.com.alexandre.BancoDeSangue.configuration.exceptions.PersonException;
import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.dataprovider.entity.PersonEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public class BancoDeSangueRepositoryImplement {

    private final BancoDeSangueRepository repository;

    public BancoDeSangueRepositoryImplement(BancoDeSangueRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Person> getPeople() {
        try {
            return this.repository.getPeople(null).stream().map(PersonEntity::toObject).toList();
        } catch (Exception exception) {
            throw EmptyListException.noPersonFound();
        }
    }

    public void register(Person person) {
        try {
            String cpf = person.getCleanCpf();
            if (isAlreadyRegistered(cpf)) {
                throw PersonException.cpfAlreadyRegistered(cpf);
            } else {
                registerPerson(person);
            }
        } catch (Exception exception) {
            throw PersonException.impossibleToRegister(person);
        }
    }

    private boolean isAlreadyRegistered(String cpf) {
        try {
            String cpfDB = this.repository.findPeopleByCpf(cpf).getCpf();
            return cpf.equals(cpfDB);
        } catch (Exception exception) {
            return false;
        }
    }

    private void registerPerson(Person person) {
        this.repository.register(
                person.getName(),
                person.getCleanCpf(),
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
