package entities;

import enums.SituacaoVotacao;
import enums.StatusGovernista;
import enums.TipoProjeto;
import interfaces.PropostaLegislativa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa a abstração de um Projeto que tramita no Sistema.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public abstract class Projeto implements PropostaLegislativa, Serializable {

    /**
     * Armazena Id de Serialização do objeto
     */
    private static final long serialVersionUID = 100476511324712155L;
    /**
     * Armazena um enum do que cartacteriza o tipo do projeto em analise,
     * pode ser do tipo PL, PLP ou PEC.
     */
    private TipoProjeto tipoDoProjeto;

    /**
     * Armazena uma String codigo do projeto.
     * Atributo responsavel por identificar o projeto. Eh composto pela sequencia cronologica
     * de projetos do mesmo tipo naquele ano seguida por este ano.
     */
    private String codigo;

    /**
     * Armazena a Dni do autor do projeto.
     */
    private String dniAutor;

    /**
     * Armazena ano no qual o projeto foi criado.
     */
    private int ano;

    /**
     * Armazena uma String contendo uma breve descricao do projeto.
     */
    private String ementa;

    /**
     * Armazena os interesses realcionados ao projeto.
     */
    private String interesses;

    /**
     * Armazena uma String com a url de endereço do projeto.
     */
    private String endereco;

    /**
     * Armazena uma lista com os locais de votaçao por onde o projeto passou.
     */
    private List<String[]> votacoes; // Local e Situação

    /**
     * Constrói um projeto inicializando a lista com os locais de votaçao em CCJC e a situaçao em votaçao.
     */
    public Projeto(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco) {
        super();
        this.codigo = codigo;
        this.dniAutor = dniAutor;
        this.ano = ano;
        this.ementa = ementa;
        this.interesses = interesses;
        this.endereco = endereco;
        this.votacoes = new ArrayList<>();
        this.votacoes.add(new String[]{"CCJC", SituacaoVotacao.EM_VOTACAO.toString()});
    }

    /**
     * Método que retorna o tipo do projeto.
     *
     * @return o tipo do projeto.
     */
    public TipoProjeto getTipoDoProjeto() {
        return this.tipoDoProjeto;
    }

    /**
     * Esse método retorna uma string contendo informaçoes acerca do estado atual do projeto.
     *
     * @return string no formato Situaçao do projeto seguida pelo local onde o projeto foi votado.
     */
    public String exibeSituacaoAtual() {
        if (this.getSituacaoAtual().equals(SituacaoVotacao.ARQUIVADO.toString()))
            return "ARQUIVADO";

        else if (this.getSituacaoAtual().equals(SituacaoVotacao.APROVADO.toString()))
            return "APROVADO";

        return this.getSituacaoAtual() + " (" + this.getLocalDeVotacao() + ")";
    }

    /**
     * Retorna o ano de criaçao do projeto.
     *
     * @return inteiro que representa o ano de criacao do projeto.
     */
    public int getAno() {
        return this.ano;
    }

    /**
     * Retorna o local onde o projeto foi votado.
     *
     * @return uma String contendo o endereço onde o projeto votado.
     */
    public String getLocalDeVotacao() {
        return this.votacoes.get(this.votacoes.size() - 1)[0];
    }

    /**
     * Altera o local onde o projeto sera votado.
     */
    public void setNovoLocalDeVotacao(String novoLocalDeVotacao) {
        this.votacoes.add(new String[]{novoLocalDeVotacao, SituacaoVotacao.EM_VOTACAO.toString()});
    }

    /**
     * Retorna uma String com a situacao do projeto.
     *
     * @return String contendo a situacao atual do projeto,
     * que pode ser em votaçao, aprovado, rejeitado ou arquivado.
     */
    public String getSituacaoAtual() {
        return this.votacoes.get(this.votacoes.size() - 1)[1].replace("_", " ");
    }

    /**
     * Método que altera o resultado da votaçao no ultimo local onde ela foi votada.
     */
    public void alteraSituacaoDoLocalAnterior(SituacaoVotacao situacao) {
        this.votacoes.get(this.votacoes.size() - 2)[1] = situacao.toString();
    }

    /**
     * Esse método retorna os interesses referentes ao projeto.
     *
     * @return string dos interesses referentes ao projeto.
     */
    public String getInteresses() {
        return this.interesses;
    }

    /**
     * Altera o estado da votaçao para arquivada.
     */
    public void encerraVotacao() {
        this.votacoes.add(new String[]{"", SituacaoVotacao.ARQUIVADO.toString()});
    }

    /**
     * Altera o estado da votaçao para aprovado.
     */
    public void aprovaVotacao() {
        this.votacoes.add(new String[]{"", SituacaoVotacao.APROVADO.toString()});
    }

    /**
     * Retorna a dni do autor do projeto.
     *
     * @return string contendo a dni do autor do projeto.
     */
    public String getAutor() {
        return this.dniAutor;
    }

    public boolean votarComissao(int qntPoliticosFavoraveis, int qntDePoliticosDaComissao, StatusGovernista status) {
        boolean resultado = false;

        if (qntPoliticosFavoraveis >= qntDePoliticosDaComissao / 2 + 1)
            resultado = true;

        if (status == StatusGovernista.OPOSICAO)
            resultado = !resultado;

        return resultado;
    }

    public void alteraNovoLocal(String proximoLocal, PropostaLegislativa proposta) {
        if (proximoLocal.equals("plenario")) {
            this.setNovoLocalDeVotacao("Plenario - 1o turno");
        } else {
            this.setNovoLocalDeVotacao(proximoLocal);
        }
    }

    public abstract void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado);

    public abstract boolean votarPlenario(int qntPoliticosFavoraveis, int qntPoliticosPresentes, StatusGovernista status);

    /**
     * Retorna uma representaçao em String do projeto
     *
     * @return string no formato codigo - dni do autor do projeto - ementa.
     */
    @Override
    public String toString() {
        return this.codigo + " - " + this.dniAutor + " - " + this.ementa;
    }

    /**
     * Esse método recupera o hash do objeto partido baseado no seu codigo.
     *
     * @return inteiro que representa hash do projeto.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    /**
     * Esse método compara um objeto projeto com outro objeto qualquer e verifica se
     * são iguais.
     *
     * @return true se os objetos comparados são iguais.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Projeto other = (Projeto) obj;
        if (codigo == null) {
            return other.codigo == null;
        } else return codigo.equals(other.codigo);
    }

    /**
     * Esse método altera o tipo do projeto
     * @param tipoDoProjeto tipo do projeto
     */
    public void setTipoDoProjeto(TipoProjeto tipoDoProjeto) {
        this.tipoDoProjeto = tipoDoProjeto;
    }
}
