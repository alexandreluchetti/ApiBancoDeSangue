package br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors.impl;

import br.com.alexandre.BancoDeSangue.core.entities.enums.BloodTypeEnum;
import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors.GetDonatorsByReceptorsUseCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDonatorsByReceptorsUseCaseImpl implements GetDonatorsByReceptorsUseCase {

    private final BancoDeSangueRepositoryImplement repository;

    public GetDonatorsByReceptorsUseCaseImpl(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient() {
        List<Person> people = repository.getPeople();
        Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient = new HashMap<>();

        for (BloodTypeEnum bloodType : BloodTypeEnum.tipos) {
            amountOfDonorsForEachBloodTypeRecipient.put(
                    bloodType.getValue(),
                    getAmountOfBloodTypeDonors(people, BloodTypeEnum.recieveFrom(bloodType))
            );
        }

        return amountOfDonorsForEachBloodTypeRecipient;
    }

    private Integer getAmountOfBloodTypeDonors(List<Person> people, List<BloodTypeEnum> bloodTypes) {
        int amount = 0;
        for (BloodTypeEnum bloodType : bloodTypes) {
            for (Person person : people) {
                if (person.getBloodType() == bloodType && person.canDonateBlood()) amount++;
            }
        }
        return amount;
    }
}
