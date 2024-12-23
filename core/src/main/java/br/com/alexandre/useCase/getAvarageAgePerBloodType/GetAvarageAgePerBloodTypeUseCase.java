package br.com.alexandre.useCase.getAvarageAgePerBloodType;

import java.util.Map;

public interface GetAvarageAgePerBloodTypeUseCase {
    Map<String, Double> getAvgAgeByBloodType();
}
