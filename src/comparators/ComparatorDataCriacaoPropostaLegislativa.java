package comparators;

import interfaces.PropostaLegislativa;

import java.util.Comparator;

/**
 * Essa classe implementa o Comparator para a interface PropostaLegislativa. Ela compara os 
 * as datas de cadastro das propostas.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 *
 */
public class ComparatorDataCriacaoPropostaLegislativa implements Comparator<PropostaLegislativa>{

	/**
	 * Esse método compara as datas de criação das propostas
	 */
	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		return o1.getDataCriacao().compareTo(o2.getDataCriacao());
	}
}
