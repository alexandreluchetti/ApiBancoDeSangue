package br.com.alexandre.useCase.getReceptorsByDonators;

import java.util.Map;

public interface GetReceptorsByDonatorsUseCase {
    Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor();
}
