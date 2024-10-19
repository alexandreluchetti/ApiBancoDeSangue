package br.com.alexandre.BancoDeSangue.configuration.exceptions;

public class EmptyListException extends RuntimeException{

    public EmptyListException(String message) {
        super(message);
    }

    public static EmptyListException noPersonAdded() {
        throw new EmptyListException("NENHUMA PESSOA FOI ADICIONADA");
    }

    public static EmptyListException noPersonFound() {
        throw new EmptyListException("NENHUMA PESSOA RETORNADA DO BANCO DE DADOS");
    }

}
