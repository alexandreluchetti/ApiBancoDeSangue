package br.com.alexandre.configuration.exceptions;

public record ExceptionEntity(

        int code,
        String message
) {

    @Override
    public String toString() {
        return "ExceptionEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
