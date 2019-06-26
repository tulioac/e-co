package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

public class ComparatorConclusaoPropostaLegislativa implements Comparator<PropostaLegislativa> {

	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		if(o1.getLocalDeVotacao().equals(o2.getLocalDeVotacao())) {
			return 0;
		}
		if("plenario".equals(o1.getLocalDeVotacao())) {
			return -1;
		}
		if("plenario".equals(o2.getLocalDeVotacao())) {
			return 1;
		}
		
		return Double.compare(o2.getVotacoes().size(), o1.getVotacoes().size());
		
	}

	
	
}
