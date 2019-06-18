package controllers;

import entities.Comissao;
import entities.Pessoa;
import services.PessoaService;
import util.Validador;

import java.io.Serializable;
import java.util.*;

/**
 * Essa classe usa o padrão Controller contendo métodos que operam sobre a
 * classe Comissao.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class ComissaoController implements Serializable {

    /**
     * Armazena Id de serialização de ComissaoController
     */
    private static final long serialVersionUID = 5812622990246783000L;
    /**
     * Service responsável por fornecer informações sobre os objetos Pessoa
     * cadastrados no sistema.
     */
    private PessoaService pessoaService;

    /**
     * Armazena objetos Comissao assumindo como chave seu tema.
     */
    private Map<String, Comissao> comissoes;

    /**
     * Constroi um Controlador de Comissao a partir de um objeto de PessoaService.
     *
     * @param pessoaService servico responsavel por fornecer informacoes sobre
     *                      Pessoa
     */
    public ComissaoController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
        this.comissoes = new HashMap<>();
    }

    /**
     * Esse método valida dnis passados como uma String separadas por vírgula (CSV)
     * conferindo se cada dni é de uma pessoa cadastrada que é política.
     *
     * @param politicos a lista de dnis para validar.
     * @return a lista de dnis validadas.
     * @throws NullPointerException     se alguma dni não for cadastrada.
     * @throws IllegalArgumentException se alguma dni não for de um político.
     */
    private String[] validaDnis(String politicos) {
        String[] dnis = politicos.split(",");
        Validador v = new Validador();

        for (String dniPolitico : dnis) {
            v.validaDni(dniPolitico, "Erro ao cadastrar comissao: dni invalido");

            if (!(this.pessoaService.ehPessoaCadastrada(dniPolitico)))
                throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");

            if (!(this.pessoaService.ehDeputado(dniPolitico)))
                throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
        }

        return dnis;
    }

    /**
     * Cadastra uma comissão a partir de um tema e do conjunto de políticos que irão
     * fazer parte dela.
     *
     * @param tema      tema da comissao, será a chave de acesso a comissao
     * @param politicos String contendo os DNIs dos políticos separados por vírgula
     * @throws NullPointerException     caso algum parâmetro seja nulo
     * @throws IllegalArgumentException caso seja passado algum parâmetro seja vazio
     * @throws IllegalArgumentException caso algum DNI seja inválido
     * @throws IllegalArgumentException caso o tema já exista
     */
    public void cadastrarComissao(String tema, String politicos) {
        Validador v = new Validador();
        v.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
        v.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");

        if (this.comissoes.containsKey(tema)) {
            throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
        }

        String[] dnis = validaDnis(politicos);
        Set<String> integrantes = new HashSet<>(Arrays.asList(dnis));

        this.comissoes.put(tema, new Comissao(tema, integrantes));
    }

    /**
     * Retorna o conjunto de todas as Comissões Legislativas cadastradas no sistema.
     * @return Set de comissões legislativas.
     */
    public Set<Comissao> getComissoes() {
        return new HashSet<>(this.comissoes.values());
    }
}