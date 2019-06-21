package entities;

import enums.StatusGovernista;
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
    public void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado) {
        if (qntDeputadosPresentes < qntTotalDeputado / 2 + 1)
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
    }

    @Override
    public boolean votarPlenario(int qntPoliticosFavoraveis, int qntPoliticosPresentes, StatusGovernista status) {
        boolean resultado = false;

        if (qntPoliticosFavoraveis >= qntPoliticosPresentes / 2 + 1)
            resultado = true;

        return (status == StatusGovernista.OPOSICAO) ? !resultado : resultado;
    }

    @Override
    public void avaliaResultado(boolean resultado, Pessoa autorDaProposta) {
        if (this.getLocalDeVotacao().equals("Plenario - 1o turno")) {
            if (resultado) {
                this.setNovoLocalDeVotacao("Plenario - 2o turno");
            } else {
                this.encerraVotacao();
            }

        } else if (this.getLocalDeVotacao().equals("Plenario - 2o turno")) {
            if (resultado) {
                this.aprovaVotacao();

                autorDaProposta.aumentaLeis();
            } else {
                this.encerraVotacao();
            }
        }
    }

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
