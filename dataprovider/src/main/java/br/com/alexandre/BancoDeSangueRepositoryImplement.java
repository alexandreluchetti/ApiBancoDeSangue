package br.com.alexandre;

import br.com.alexandre.entities.Person;
import br.com.alexandre.entity.PersonEntity;
import br.com.alexandre.exceptions.EmptyListException;
import br.com.alexandre.exceptions.PersonException;
import br.com.alexandre.repository.BancoDeSangueRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BancoDeSangueRepositoryImplement implements BancoDeSangueRepository {

    private static final Logger logger = LoggerFactory.getLogger(BancoDeSangueRepositoryImplement.class);

    private final BancoDeSangueJpaRepository repository;

    public BancoDeSangueRepositoryImplement(BancoDeSangueJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Person> getPeople() {
        try {
            return this.repository.getPeople(null).stream().map(PersonEntity::toObject).toList();
        } catch (Exception exception) {
            logger.warn("Nenhuma pessoa encontrada");
            throw EmptyListException.noPersonFound();
        }
    }

    @Override
    public void register(Person person) {
        try {
            String cpf = person.getCleanCpf();
            if (isAlreadyRegistered(cpf)) {
                logger.info("CPF ja registrado: {}", person.getCleanCpf());
                throw PersonException.cpfAlreadyRegistered(cpf);
            } else {
                registerPerson(person);
            }
        } catch (Exception exception) {
            logger.warn("Impossivel registrar pessoa");
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
