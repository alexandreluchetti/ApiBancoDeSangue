package br.com.alexandre.BancoDeSangue.service;

import br.com.alexandre.BancoDeSangue.entities.BloodTypeEnum;
import br.com.alexandre.BancoDeSangue.entities.Person;
import br.com.alexandre.BancoDeSangue.repositories.BancoDeSangueRepositoryImplement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class Service {

    private static List<Person> staticPeoplesList = null;

    private BancoDeSangueRepositoryImplement repository;

    @Autowired
    public Service(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    private List<Person> getPeoplesList() {
        if (staticPeoplesList == null) staticPeoplesList = repository.getPeople();
        return staticPeoplesList;
    }

    public Map<String, Integer> getCandidatesPerState() {
        List<Person> people = this.getPeoplesList();
        Map<String, Integer> peopleByState = new HashMap<>();

        for (Person person : people) {
            String state = person.getAddress().getState();
            if (peopleByState.containsKey(state)) {
                int quantidade = peopleByState.get(state) + 1;
                peopleByState.put(state, quantidade);
            } else {
                peopleByState.put(state, 1);
            }
        }

        return peopleByState;
    }

    public Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient() {
        List<Person> people = this.getPeoplesList();
        Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient = new HashMap<>();

        for (BloodTypeEnum bloodType : BloodTypeEnum.tipos) {
            amountOfDonorsForEachBloodTypeRecipient.put(
                    bloodType.getValue(),
                    getAmountOfBloodTypeDonors(people, BloodTypeEnum.recieveFrom(bloodType))
            );
        }

        return amountOfDonorsForEachBloodTypeRecipient;
    }

    public Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor() {
        List<Person> people = this.getPeoplesList();
        Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor = new HashMap<>();

        for (BloodTypeEnum bloodType : BloodTypeEnum.tipos) {
            amountOfRecipientsForEachBloodTypeDonor.put(
                    bloodType.getValue(),
                    getAmountOfBloodTypeDonors(people, BloodTypeEnum.donateTo(bloodType))
            );
        }

        return amountOfRecipientsForEachBloodTypeDonor;
    }

    private Integer getAmountOfBloodTypeDonors(List<Person> people, List<BloodTypeEnum> bloodTypes) {
        int amount = 0;
        for (BloodTypeEnum bloodType : bloodTypes) {
            for (Person person : people) {
                if (person.getBloodType() == bloodType) amount++;
            }
        }
        return amount;
    }

}