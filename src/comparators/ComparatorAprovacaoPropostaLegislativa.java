package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

public class ComparatorAprovacaoPropostaLegislativa implements Comparator<PropostaLegislativa>{

	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		
		int votacoesAprovadas1 = o1.getVotacoes().size()-1;
		int votacoesAprovadas2 = o2.getVotacoes().size()-1;
		
		return votacoesAprovadas1 - votacoesAprovadas2;
	}

	
	
}
