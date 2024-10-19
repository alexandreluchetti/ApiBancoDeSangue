package br.com.alexandre.BancoDeSangue.useCase.getReceptorsByDonators;

import java.util.Map;

public interface GetReceptorsByDonatorsUseCase {
    Map<String, Integer> amountOfRecipientsForEachBloodTypeDonor();
}
