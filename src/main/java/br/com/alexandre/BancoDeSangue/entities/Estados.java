package br.com.alexandre.BancoDeSangue.entities;

public enum Estados {

    ACRE(1, "AC"),
    ALAGOAS(2, "AL"),
    AMAPA(3, "AP"),
    AMAZONAS(4, "AM"),
    BAHIA(5, "BA"),
    CEARA(6, "CE"),
    DISTRITO_FEDERAL(7, "DF"),
    ESPIRITO_SANTO(8, "ES");

    private int index;
    private String sigla;

    Estados(int index, String sigla) {
        this.index = index;
        this.sigla = sigla;
    }

    public int getIndex() {
        return index;
    }

    public String getSigla() {
        return sigla;
    }
}
