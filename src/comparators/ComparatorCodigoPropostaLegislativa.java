package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

/**
 * Essa classe implementa o Comparator para a interface PropostaLegislativa. Ela compara os
 * códigos ordinais das propostas.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 *
 */
public class ComparatorCodigoPropostaLegislativa implements Comparator<PropostaLegislativa>{

	/**
	 * Esse método compara os códigos das propostas, prevalecendo o menor
	 */
	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		return Integer.compare(o1.getNumeroCodigo(), o2.getNumeroCodigo());
	}

	
	
}
