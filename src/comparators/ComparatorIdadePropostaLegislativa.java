package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

public class ComparatorIdadePropostaLegislativa implements Comparator<PropostaLegislativa>{

	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		return o1.getAno() - o2.getAno();
	}

	
	
}
