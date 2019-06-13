package interfaces;

import util.SituacaoVotacao;

import java.util.Set;

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

	public void setSituacaoAtual(SituacaoVotacao situacaoAtual);

	/**
	 * Esse método retorna um ENUM dos possiveis estados de um projeto de lei.
	 */
	public SituacaoVotacao getSituacaoAtual();


	/**
	 * Esse método retorna a representaçao em String de um projeto de lei
	 */
	public String toString();
}