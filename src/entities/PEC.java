package entities;

import enums.StatusGovernistas;
import enums.TipoDeProjetos;

import java.io.Serializable;

/**
 * Essa classe representa um projeto de emenda constitucional.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PEC extends Projeto implements Serializable {
    /**
     * Armazena uma string contendo os artigos que foram referenciados na PEC.
     */
    private String artigos;

    /**
     * Constrói um projeto de emenda constitucional inicializando o tipo de projeto como PEC.
     */
    public PEC(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.artigos = artigos;
        this.setTipoDoProjeto(TipoProjeto.PEC);
    }

    /**
     * Retorna uma representaçao em String da PEC sobreescrevendo o método toString que foi criado na
     * classe Projeto e exibindo os artigos que foram referenciados nessa PEC.
     *
     * @return string no formato Projeto de Emenda Constitucional - codigo - dni do autor do projeto - ementa - artigos - situacao.
     */
    @Override
    public void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado) {
        if (qntDeputadosPresentes < 3 * qntTotalDeputado / 5 + 1)
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
    }

    @Override
    public boolean votarPlenario(int qntPoliticosFavoraveis, int qntPoliticosPresentes, StatusGovernistas status) {
        boolean resultado = false;

        if (qntPoliticosFavoraveis >= 3 * qntPoliticosPresentes / 5 + 1)
            resultado = true;

        if (status == StatusGovernistas.OPOSICAO)
            resultado = !resultado;

        return resultado;
    }

    @Override
    public String toString() {
        return "Projeto de Emenda Constitucional - " + super.toString()
                + " - " + this.getArtigos() + " - " + this.exibeSituacaoAtual();
    }

    /**
     * Retorna os artigos referenciados nessa PEC.
     *
     * @return String contendo os artigos referenciados separando-os com um espaço e virgula.
     */
    private String getArtigos() {
        return this.artigos.replace(",", ", ");
    }


}