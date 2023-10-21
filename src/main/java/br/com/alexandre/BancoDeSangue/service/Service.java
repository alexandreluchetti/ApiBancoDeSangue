package br.com.alexandre.BancoDeSangue.service;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import br.com.alexandre.BancoDeSangue.entities.ImcPorDecada;
import br.com.alexandre.BancoDeSangue.entities.Pessoa;
import br.com.alexandre.BancoDeSangue.entities.TipoSanguineoEnum;
import br.com.alexandre.BancoDeSangue.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.alexandre.BancoDeSangue.entities.SexoEnum.FEMININO;
import static br.com.alexandre.BancoDeSangue.entities.SexoEnum.MASCULINO;

@org.springframework.stereotype.Service
public class Service {

    private static final String ZERO_10 = "0 a 10";
     private static final String ONZE_20 = "11 a 20";
     private static final String VINTE1_30 = "21 a 30";
     private static final String TRINTA1_40 = "31 a 40";
     private static final String QUARENTA1_50 = "41 a 50";
     private static final String CINQUENTA1_60 = "51 a 60";
     private static final String SECENTA1_70 = "61 a 70";
     private static final String SETENTA1_80 = "71 a 80";
     private static final String OITENTA1_90 = "81 a 90";
     private static final String NOVENTA1_100 = "91 a 100";
     private static final Integer IMC_30 = 30;

    private static List<Pessoa> pessoasStatic = new ArrayList<>();

    private PessoaRepository pessoaRepository;

    @Autowired
    public Service(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void registraPessoa(Pessoa pessoa) {
        pessoaRepository.registrarPessoa(pessoa);
    }

    public List<Pessoa> buscaPessoas(String cpf) {
        List<Pessoa> pessoas = (pessoasStatic.isEmpty())
                ? pessoaRepository.buscaPessoas(cpf)
                : pessoasStatic;

        return pessoas;
    }

    public List<PessoaDto> buscaPessoaDto(String cpf) {
        List<Pessoa> pessoas = buscaPessoas(cpf);
        List<PessoaDto> pessoaDtos = new ArrayList<>();
        pessoas.forEach( pessoa -> {
            pessoaDtos.add(pessoa.toDto());
        });
        return pessoaDtos;
    }

    public Map<String, Integer> candidatosPorEstado() {
        List<Pessoa> pessoas = buscaPessoas("");
        Map<String, Integer> pessoasPorEstado = new HashMap<>();

        for (Pessoa pessoa : pessoas) {
            String estado = pessoa.getEndereco().getEstado();
            if (pessoasPorEstado.containsKey(estado)) {
                int quantidade = pessoasPorEstado.get(estado) + 1;
                pessoasPorEstado.put(estado, quantidade);
            } else {
                pessoasPorEstado.put(estado, 1);
            }
        }

        return pessoasPorEstado;
    }

    public Map<String, Double> mediaIdadePorTipoSanguineo() {
        List<Pessoa> pessoas = buscaPessoas("");
        Map<String, Double> mediaPorTIpoSanguineo = new HashMap<>(Map.of());

        for (TipoSanguineoEnum ts : TipoSanguineoEnum.values()) {
            mediaPorTIpoSanguineo.put(ts.getValue(), getMedia(ts, pessoas));
        }

        return mediaPorTIpoSanguineo;
    }

    private Double getMedia(TipoSanguineoEnum tipoSanguineo, List<Pessoa> pessoas) {
        int idade = 0;
        int quantidadePessoas = 0;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getTipoSanguineo().equals(tipoSanguineo)) {
                idade += pessoa.getIdade();
                quantidadePessoas++;
            }
        }

        if (idade == 0 && quantidadePessoas == 0) return 0.0;
        return (double) (idade / quantidadePessoas);
    }

    public Map<String, Double> percentualDeObesosPorSexo() {
        List<Pessoa> pessoas = buscaPessoas("");
        List<Pessoa> homens = new ArrayList<>();
        List<Pessoa> mulheres = new ArrayList<>();

        pessoas.forEach( pessoa -> {
            if (pessoa.getSexo().equals(MASCULINO)) homens.add(pessoa);
            else mulheres.add(pessoa);
        });

        Map<String, Double> map = new HashMap<>(Map.of(
                MASCULINO.getValue(), getMedia(homens),
                FEMININO.getValue(), getMedia(mulheres)
        ));

        return map;
    }

    private Double getMedia(List<Pessoa> pessoas) {
        AtomicInteger obesos = new AtomicInteger();
        pessoas.forEach( pessoa -> {
            if (pessoa.getImc() > IMC_30) obesos.getAndIncrement();
        });

        Double media = ((double) (obesos.get() * 100) / pessoas.size());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String doubleFormatado = decimalFormat.format(media).replace(",", ".");
        return Double.parseDouble(doubleFormatado);
    }

    public Map<String, Double> imcMedioPorDezenaDeIdade() {
        List<Pessoa> pessoas = buscaPessoas("");
        Map<String, Double> decadas = new HashMap<>();
        List<ImcPorDecada> imcPorDecadaList = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            int idade = pessoa.getIdade();
            double imc = (pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura()));
            String keyDezenas = getDezenas(idade);
            double imcJaRegistrado = decadas.get(keyDezenas);
            decadas.put(keyDezenas, imc + imcJaRegistrado);
        }

        for (String decada : decadas.keySet()) {
            double imcTotal = decadas.get(decada);
            double mediaImc = imcTotal;
        }

        return decadas;
    }

    private String getDezenas(int idade) {
        String key = "";
        if (idade < 10) key = ZERO_10;
        if (idade > 10 && idade < 20) key = ONZE_20;
        if (idade > 20 && idade < 30) key = VINTE1_30;
        if (idade > 30 && idade < 40) key = TRINTA1_40;
        if (idade > 40 && idade < 50) key = QUARENTA1_50;
        if (idade > 50 && idade < 60) key = CINQUENTA1_60;
        if (idade > 60 && idade < 70) key = SECENTA1_70;
        if (idade > 70 && idade < 80) key = SETENTA1_80;
        if (idade > 80 && idade < 90) key = OITENTA1_90;
        if (idade > 90) key = NOVENTA1_100;
        return key;
    }

}