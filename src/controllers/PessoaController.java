package controllers;

import entities.Pessoa;
import enums.CargosPoliticos;
import util.Validador;

import java.io.Serializable;
import java.util.*;

/**
 * Essa classe usa o padrão Controller contendo métodos que operam sobre a
 * classe pessoa.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PessoaController implements Serializable {
    /**
     * Armazena Id de serialização de PessoaController
     */
    private static final long serialVersionUID = 2198811903520135676L;
    /**
     * Armazena um mapa de pessoas em que a chave e o documento de identificação e
     * aponta para um objeto do tipo Pessoa.
     */
    private Map<String, Pessoa> pessoas;

    /**
     * Constrói uma classe controladora de pessoa e inicializa um mapa que armazena
     * as pessoas.
     */
    public PessoaController() {
        this.pessoas = new HashMap<>();
    }

    /**
     * Esse método cadastra uma pessoa que possui nome, documento de identificação,
     * estado, interesses e partido.
     *
     * @param nome       nome da pessoa.
     * @param dni        documento de identificação da pessoa.
     * @param estado     estado da pessoa.
     * @param interesses interesses da pessoa.
     * @param partido    partido da pessoa.
     * @throws IllegalArgumentException dni já existe.
     * @throws IllegalArgumentException caso algum parâmetro seja vazio.
     * @throws NullPointerException     caso algum parâmetro seja nulo.
     */
    public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        Validador v = new Validador();
        v.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
        v.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
        v.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
        v.validaNull(interesses, "Erro ao cadastrar pessoa: interesses nao pode ser nulo");
        v.validaNull(partido, "Erro ao cadastrar pessoa: interesses nao pode ser nulo");

        if (this.pessoas.containsKey(dni))
            throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");

        this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses, partido));
    }

    /**
     * Esse método cadastra uma pessoa que possui nome, documento de identificação,
     * estado e interesses.
     *
     * @param nome       nome da pessoa.
     * @param dni        documento de identificação da pessoa.
     * @param estado     estado da pessoa.
     * @param interesses interesses da pessoa.
     * @throws IllegalArgumentException dni já existe.
     * @throws IllegalArgumentException caso algum parâmetro seja vazio.
     * @throws NullPointerException     caso algum parâmetro seja nulo.
     */
    public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        this.cadastrarPessoa(nome, dni, estado, interesses, "");
    }

    /**
     * Esse método cadastra um deputado com sua data de início, alterando o cargo
     * político de uma pessoa para deputado.
     *
     * @param dni          o dni da pessoa que se deseja cadastrar como deputado.
     * @param dataDeInicio a data de início do cargo como deputado.
     * @throws NullPointerException     caso algum parâmetro seja nulo
     * @throws IllegalArgumentException caso algum parâmetro seja vazio ou de
     *                                  formato inválido.
     */
    public void cadastrarDeputado(String dni, String dataDeInicio) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar deputado: dni nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar deputado: dni invalido");

        if (!(this.pessoas.containsKey(dni)))
            throw new NullPointerException("Erro ao cadastrar deputado: pessoa nao encontrada");

        v.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");

        Date dataInicialValidada = v.validaData(dataDeInicio, "Erro ao cadastrar deputado: data invalida",
                "Erro ao cadastrar deputado: data futura");

        if (this.pessoas.get(dni).getPartido().equals(""))
            throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");

        if (this.pessoas.get(dni).getCargoPolitico().equals(CargosPoliticos.DEPUTADO))
            throw new IllegalArgumentException("Erro ao cadastrar deputado: deputado ja cadastrado");

        this.pessoas.get(dni).setCargoPolitico("Deputado", dataInicialValidada);
    }

    /**
     * Esse método exibe a descriçao de uma pessoa em forma de String.
     *
     * @param dni o dni da pessoa que se deseja cadastrar como deputado.
     * @return uma string contendo os atributos da pessoa.
     * @throws NullPointerException     caso algum parâmetro seja nulo
     * @throws IllegalArgumentException caso algum parâmetro seja vazio ou de
     *                                  formato inválido.
     * @throws IllegalArgumentException caso a pessoa nao esteja cadastrada.
     */
    public String exibirPessoa(String dni) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao exibir pessoa: dni invalido");

        if (!(this.pessoas.containsKey(dni)))
            throw new NullPointerException("Erro ao exibir pessoa: pessoa nao encontrada");

        return this.pessoas.get(dni).toString();
    }

    /**
     * Retorna o conjunto de pessoas cadastradas no sistema
     *
     * @return Set de pessoas
     */
    public Set<Pessoa> getPessoas() {
        return new HashSet<>(this.pessoas.values());
    }
}