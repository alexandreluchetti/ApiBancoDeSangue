package br.com.alexandre.BancoDeSangue.entities;

public class ImcPorDecada {

    private String decada;
    private Double imc;
    private Integer quantidade;

    public ImcPorDecada(String decada, Double imc, Integer quantidade) {
        this.decada = decada;
        this.imc = imc;
        this.quantidade = quantidade;
    }

    public String getDecada() {
        return decada;
    }

    public Double getImc() {
        return imc;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
