package br.com.alexandre.BancoDeSangue.exceptions;

public class DtoException extends RuntimeException{

    public DtoException(String message) {
        super(message);
    }

    public static DtoException convert(Object object) {
        throw new DtoException("ERRO AO CONVERTER DTO: " + object.toString());
    }

}
