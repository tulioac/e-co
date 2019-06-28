package comparators;

import interfaces.PropostaLegislativa;

import java.util.Comparator;

/**
 * Essa classe implementa o Comparator para a interface PropostaLegislativa. Ela compara a
 * aprovação de entre propostas.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 *
 */
public class ComparatorAprovacaoPropostaLegislativa implements Comparator<PropostaLegislativa>{

	/**
	 * Esse método compara duas propostas pelo seus números de aprovações 
	 */
	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		
		int votacoesAprovadas1 = o1.getVotacoes().size()-1;
		int votacoesAprovadas2 = o2.getVotacoes().size()-1;
		
		return votacoesAprovadas2 - votacoesAprovadas1;
	}

	
	
}
