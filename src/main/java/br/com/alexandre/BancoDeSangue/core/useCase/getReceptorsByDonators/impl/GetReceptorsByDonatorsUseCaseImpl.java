package br.com.alexandre.BancoDeSangue.core.useCase.getReceptorsByDonators.impl;

import br.com.alexandre.BancoDeSangue.core.entities.enums.BloodTypeEnum;
import br.com.alexandre.BancoDeSangue.core.entities.Person;
import br.com.alexandre.BancoDeSangue.dataprovider.BancoDeSangueRepositoryImplement;
import br.com.alexandre.BancoDeSangue.core.useCase.getReceptorsByDonators.GetReceptorsByDonatorsUseCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetReceptorsByDonatorsUseCaseImpl implements GetReceptorsByDonatorsUseCase {

    private final BancoDeSangueRepositoryImplement repository;

    public GetReceptorsByDonatorsUseCaseImpl(BancoDeSangueRepositoryImplement repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor() {
        List<Person> people = repository.getPeople();
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
