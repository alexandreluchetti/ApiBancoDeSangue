package br.com.alexandre.BancoDeSangue.exceptions;

public class PersonException extends RuntimeException{

    public PersonException(String message) {
        super(message);
    }

    public static PersonException convert(Object object) {
        throw new PersonException("ERRO AO CONVERTER DTO: " + object.toString());
    }

    public static PersonException impossibleToRegister(Object object) {
        throw new PersonException("NAO FOI POSSIVEL REGISTRAR: " + object.toString());
    }

    public static PersonException cpfAlreadyRegistered(String cpf) {
        throw new PersonException("CPF JA REGISTRADO: " + cpf);
    }

}
