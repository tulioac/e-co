package interfaces;

import enums.SituacaoVotacao;
import enums.TipoDeProjetos;

/**
 * Essa interface representa as possibilidades de propostas legislativas
 * consideradas no sistema.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */

public interface PropostaLegislativa {

    public String exibeSituacaoAtual();

    /**
     * Esse método retorna a representaçao em String de um projeto de lei
     */
    public String toString();

    public int getAno();

    public TipoDeProjetos getTipoDoProjeto();

    public String getLocalDeVotacao();

    public void setNovoLocalDeVotacao(String localDeVotacao);

    public String getInteresses();

    public String getSituacaoAtual();

    public void alteraSituacaoDoLocalAnterior(SituacaoVotacao situacao);

    public void encerraVotacao();

    public void aprovaVotacao();

    public String getAutor();
}