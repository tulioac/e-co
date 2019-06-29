package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

/**
 * Essa classe implementa o Comparator para a interface PropostaLegislativa. Ela compara a
 * o número sequencial de criação de uma proposta legislativa.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class ComparatorOrdemCriacaoPropostaLegislativa implements Comparator<PropostaLegislativa>{

	/**
	 * Esse método compara os números sequenciais de criação das propostas legislativas.
	 */
	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		return Integer.compare(o1.getNumCriacaoProjeto(), o2.getNumCriacaoProjeto());
	}
	
}
