package comparators;

import interfaces.PropostaLegislativa;

import java.util.Comparator;

/**
 * Essa classe implementa o Comparator para a interface PropostaLegislativa. Ela compara o 
 * ano de criação das propostas.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 *
 */
public class ComparatorIdadePropostaLegislativa implements Comparator<PropostaLegislativa>{

	/**
	 * Esse método compara o ano de criação das propostas.
	 */
	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		return o1.getAno() - o2.getAno();
	}

	
	
}
