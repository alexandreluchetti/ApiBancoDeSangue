package br.com.alexandre.BancoDeSangue.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Endereco {

    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco() {
        this.cep = "";
        this.endereco = "";
        this.numero = -1;
        this.bairro = "";
        this.cidade = "";
        this.estado = "";
    }
}
