package br.com.alexandre.BancoDeSangue.exceptions;

public class FormatException extends RuntimeException{

    public FormatException(String message) {
        super(message);
    }

    public static FormatException stringDateFormat() {
        throw new FormatException("ERRO AO CONVERTER A DATA");
    }

}
