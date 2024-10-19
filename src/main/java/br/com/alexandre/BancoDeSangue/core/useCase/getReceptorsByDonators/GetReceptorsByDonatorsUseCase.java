package br.com.alexandre.BancoDeSangue.core.useCase.getReceptorsByDonators;

import java.util.Map;

public interface GetReceptorsByDonatorsUseCase {
    Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor();
}
