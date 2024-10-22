package br.com.alexandre.BancoDeSangue.core.useCase.getAvarageAgePerBloodType;

import br.com.alexandre.BancoDeSangue.entrypoint.getAvarageAgePerBloodType.dto.AvarageAgePerBloodTypeResponseDto;

public interface GetAvarageAgePerBloodTypeUseCase {
    AvarageAgePerBloodTypeResponseDto getAvgAgeByBloodType();
}
