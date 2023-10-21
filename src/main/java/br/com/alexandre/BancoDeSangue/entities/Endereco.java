package br.com.alexandre.BancoDeSangue.entities;


public class Endereco {

    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;

    public Endereco(String cep, String endereco, Integer numero, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco() {
        this.cep = "";
        this.endereco = "";
        this.numero = -1;
        this.bairro = "";
        this.cidade = "";
        this.estado = "";
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Endereco {cep=" + cep + ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + "}";
    }
}
