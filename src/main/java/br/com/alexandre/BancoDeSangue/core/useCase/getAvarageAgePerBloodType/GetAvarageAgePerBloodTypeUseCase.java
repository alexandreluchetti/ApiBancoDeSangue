package br.com.alexandre.BancoDeSangue.core.useCase.getAvarageAgePerBloodType;

import java.util.Map;

public interface GetAvarageAgePerBloodTypeUseCase {
    Map<String, Double> getAvgAgeByBloodType();
}
