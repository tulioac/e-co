package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

public class ComparatorConclusaoPropostaLegislativa implements Comparator<PropostaLegislativa> {

	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		if(o1.getLocalDeVotacao().equals(o2.getLocalDeVotacao())) {
			return 0;
		}
		if("Plenario - 2o turno".equals(o1.getLocalDeVotacao())) {
			return -1;
		}
		if("Plenario - 2o turno".equals(o2.getLocalDeVotacao())) {
			return 1;
		}
		if("Plenario - 1o turno".equals(o1.getLocalDeVotacao())) {
			return -1;
		}
		if("Plenario - 1o turno".equals(o2.getLocalDeVotacao())) {
			return 1;
		}
		
		return Double.compare(o2.getVotacoes().size(), o1.getVotacoes().size());
		
	}

	
	
}
