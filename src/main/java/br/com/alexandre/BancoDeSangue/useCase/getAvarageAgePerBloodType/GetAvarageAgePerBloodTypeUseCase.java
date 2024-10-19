package br.com.alexandre.BancoDeSangue.useCase.getAvarageAgePerBloodType;

import java.util.Map;

public interface GetAvarageAgePerBloodTypeUseCase {
    Map<String, Double> getAvgAgeByBloodType();
}
