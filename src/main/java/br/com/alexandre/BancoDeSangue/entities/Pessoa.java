package br.com.alexandre.BancoDeSangue.entities;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

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

    public Pessoa(String nome, String cpf, String rg, String dataNascimento, SexoEnum sexo, String mae, String pai, String email, Endereco endereco, String telefoneFixo, String celular, double altura, double peso, TipoSanguineoEnum tipoSanguineo) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.mae = mae;
        this.pai = pai;
        this.email = email;
        this.endereco = endereco;
        this.telefoneFixo = telefoneFixo;
        this.celular = celular;
        this.altura = altura;
        this.peso = peso;
        this.tipoSanguineo = tipoSanguineo;
    }

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

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public Date getFormatedDate() {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date utilDate = outputFormat.parse(dataNascimento); // Converte a string para java.util.Date
            String formattedDate = outputFormat.format(utilDate); // Formata a data no formato "yyyy-MM-dd"
            return Date.valueOf(formattedDate);
        } catch (ParseException exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public String getMae() {
        return mae;
    }

    public String getPai() {
        return pai;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public String getCelular() {
        return celular;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public TipoSanguineoEnum getTipoSanguineo() {
        return tipoSanguineo;
    }

    public Integer getIdade() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataDeNascimento = getFormatedDate().toLocalDate();
        Period periodo = Period.between(dataDeNascimento, dataAtual);
        return periodo.getYears();
    }

    public Double getImc() {
        return (this.peso / (this.altura * this.altura));
    }

    public PessoaDto toDto() {
        return new PessoaDto(
                nome,
                cpf,
                rg,
                dataNascimento,
                sexo.getValue(),
                mae,
                pai,
                email,
                endereco.getCep(),
                endereco.getEndereco(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                telefoneFixo,
                celular,
                altura,
                peso,
                tipoSanguineo.getValue()
        );
    }

    @Override
    public String toString() {
        return "Pessoa {nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", mae=" + mae + ", pai=" + pai + ", email=" + email + ", endereco=" + endereco.toString() + ", telefoneFixo=" + telefoneFixo + ", celular=" + celular + ", altura=" + altura + ", peso=" + peso + ", tipoSanguineo=" + tipoSanguineo + "}";
    }
}
