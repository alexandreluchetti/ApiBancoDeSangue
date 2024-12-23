package br.com.alexandre.useCase.getDonatorsByReceptors;

import java.util.Map;

public interface GetDonatorsByReceptorsUseCase {

    Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient();
}
