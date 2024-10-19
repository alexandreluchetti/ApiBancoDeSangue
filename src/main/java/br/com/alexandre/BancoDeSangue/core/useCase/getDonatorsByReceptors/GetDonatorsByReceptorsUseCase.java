package br.com.alexandre.BancoDeSangue.core.useCase.getDonatorsByReceptors;

import java.util.Map;

public interface GetDonatorsByReceptorsUseCase {

    Map<String, Integer> amountOfDonorsForEachBloodTypeRecipient();
}
