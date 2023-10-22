package br.com.alexandre.BancoDeSangue.service;

import br.com.alexandre.BancoDeSangue.controller.dto.PessoaDto;
import br.com.alexandre.BancoDeSangue.entities.Pessoa;
import br.com.alexandre.BancoDeSangue.entities.TipoSanguineoEnum;
import br.com.alexandre.BancoDeSangue.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
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
     private static final Integer IMC_30 = 30;
     private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

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
        pessoasStatic = (pessoasStatic.size() > 0)
                ? pessoasStatic
                : pessoaRepository.buscaPessoas(cpf);

        if (pessoasStatic == null) return new ArrayList<>();
        return pessoasStatic;
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
            mediaPorTIpoSanguineo.put(ts.getValue(), getIdadeMedia(ts, pessoas));
        }

        return mediaPorTIpoSanguineo;
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
                MASCULINO.getValue(), getPercentualMedio(homens),
                FEMININO.getValue(), getPercentualMedio(mulheres)
        ));

        return map;
    }

    public Map<String, Integer> quantidadeDoadoresParaCadaTipoSanguineoReceptor() {
        List<Pessoa> pessoas = buscaPessoas("");
        Map<String, Integer> quantidadeDeDoadoresPorTipoSanguineoReceptor = new HashMap<>();

        for (TipoSanguineoEnum tipoSanguineo : TipoSanguineoEnum.tipos) {
            quantidadeDeDoadoresPorTipoSanguineoReceptor.put(
                    tipoSanguineo.getValue(),
                    getQuantidadeDoadoresTipoSanguineo(pessoas, TipoSanguineoEnum.receberDe(tipoSanguineo))
            );
        }

        return quantidadeDeDoadoresPorTipoSanguineoReceptor;
    }

    public Map<String, Integer> quantidadeReceptoresParaCadaTipoSanguineoDoador() {
        List<Pessoa> pessoas = buscaPessoas("");
        Map<String, Integer> quantidadeDeReceptoresPorTipoSanguineoDoador = new HashMap<>();

        for (TipoSanguineoEnum tipoSanguineo : TipoSanguineoEnum.tipos) {
            quantidadeDeReceptoresPorTipoSanguineoDoador.put(
                    tipoSanguineo.getValue(),
                    getQuantidadeDoadoresTipoSanguineo(pessoas, TipoSanguineoEnum.doarPara(tipoSanguineo))
            );
        }

        return quantidadeDeReceptoresPorTipoSanguineoDoador;
    }

    public Map<String, Double> imcMedioPorDecada() {
        List<Pessoa> pessoas = buscaPessoas("");
        Map<String, Double> imcMedioPorDecada = getImcMedio(pessoas);
        return imcMedioPorDecada;
    }

    private Map<String, Double> getImcMedio(List<Pessoa> pessoas) {
        Map<String, Double> imcMedioPorDecada = getNomeDecadas(pessoas);
        Map<String, Double> map = new HashMap<>();
        for (String nomeDecada : imcMedioPorDecada.keySet()) {
            double imc = 0;
            int quantidade = 0;
            String[] numerosDecada = nomeDecada.split("a");
            Integer inicioDecada = Integer.parseInt(numerosDecada[0].trim());
            Integer fimDecada = Integer.parseInt(numerosDecada[1].trim());

            for (Pessoa pessoa : pessoas) {
                int idade = pessoa.getIdade();
                if (idade >= inicioDecada && idade <= fimDecada) {
                    imc += pessoa.getImc();
                    quantidade++;
                }
            }

            Double imcMedio = imc / quantidade;
            map.put(nomeDecada, formatDouble(imcMedio));
        }

        return map;
    }

    private Map<String, Double> getNomeDecadas(List<Pessoa> pessoas) {
        Map<String, Double> nomeDecadas = new HashMap<>();
        for (Pessoa pessoa : pessoas) {
            int idade = pessoa.getIdade();
            char[] charArray = String.valueOf(idade).toCharArray();

            String nomeDecada = "";
            int primeiroNumero = Integer.parseInt(String.valueOf(charArray[0]));
            int segundoNumero;
            if (String.valueOf(charArray[1]).equals("0")) {
                segundoNumero = primeiroNumero * 10;
                primeiroNumero = segundoNumero - 9;
            } else {
                primeiroNumero = (primeiroNumero * 10) + 1;
                segundoNumero = primeiroNumero + 9;
            }

            nomeDecada = primeiroNumero + " a " + segundoNumero;
            nomeDecadas.put(nomeDecada, 0.0);
        }
        return nomeDecadas;
    }

    private Integer getQuantidadeDoadoresTipoSanguineo(List<Pessoa> pessoas, List<TipoSanguineoEnum> tipos) {
        int quantidade = 0;
        for (TipoSanguineoEnum tipoSanguineo : tipos) {
            for (Pessoa pessoa : pessoas) {
                if (pessoa.getTipoSanguineo() == tipoSanguineo) quantidade++;
            }
        }
        return quantidade;
    }

    private Double getIdadeMedia(TipoSanguineoEnum tipoSanguineo, List<Pessoa> pessoas) {
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

    private Double getPercentualMedio(List<Pessoa> pessoas) {
        AtomicInteger obesos = new AtomicInteger();
        pessoas.forEach( pessoa -> {
            if (pessoa.getImc() > IMC_30) obesos.getAndIncrement();
        });

        Double media = ((double) (obesos.get() * 100) / pessoas.size());
        return formatDouble(media);
    }

    private Double formatDouble(Double value) {
        String stringValue = DECIMAL_FORMAT.format(value).replace(",", ".");
        return Double.parseDouble(stringValue);
    }

}