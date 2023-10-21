package br.com.alexandre.BancoDeSangue.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pessoa {
    
    private String nome;
    private String cpf;
    private String rg;
    @JsonAlias({"data_nasc"}) private String dataNascimento;
    private SexoEnum sexo;
    private String mae;
    private String pai;
    private String email;
    private Endereco endereco;
    @JsonAlias({"telefone_fixo"}) private String telefoneFixo;
    private String celular;
    private double altura;
    private double peso;
    @JsonAlias({"tipo_sanguineo"}) private TipoSanguineoEnum tipoSanguineo;

    public Pessoa() {
        this.nome = "";
        this.cpf = "";
        this.rg = "";
        this.dataNascimento = "";
        this.sexo = SexoEnum.VAZIO;
        this.mae = "";
        this.pai = "";
        this.email = "";
        this.endereco = new Endereco();
        this.telefoneFixo = "";
        this.celular = "";
        this.altura = 0.0;
        this.peso = 0.0;
        this.tipoSanguineo = TipoSanguineoEnum.VAZIO;
    }
}
