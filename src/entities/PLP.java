package entities;

import enums.TipoProjeto;

import java.io.Serializable;

/**
 * Essa classe representa um projeto de lei complementar.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PLP extends Projeto implements Serializable {
    /**
     * Armazena uma string contendo os artigos que foram referenciados na PLP.
     */
    private String artigos;

    /**
     * Constrói um Projeto do tipo PLP inicializando o tipo de projeto como PLP.
     */
    public PLP(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.artigos = artigos;
        this.setTipoDoProjeto(TipoProjeto.PLP);
    }

    /**
     * Retorna uma representaçao em String da PLP sobrescrevendo o método toString que foi criado na
     * classe Projeto e exibindo os artigos que foram referenciados nessa PLP.
     *
     * @return string no formato Projeto de Emenda Constitucional - codigo - dni do autor do projeto - ementa - artigos - situacao.
     */
    @Override
    public String toString() {
        return "Projeto de Lei Complementar - " + super.toString()
                + " - " + this.getArtigos() + " - " + this.exibeSituacaoAtual();
    }

    /**
     * Retorna os artigos referenciados nessa PLP.
     *
     * @return String contendo os artigos referenciados separando-os com um espaço e virgula.
     */
    private String getArtigos() {
        return this.artigos.replace(",", ", ");
    }
}
