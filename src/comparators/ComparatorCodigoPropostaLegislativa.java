package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

public class ComparatorCodigoPropostaLegislativa implements Comparator<PropostaLegislativa>{

	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		return Integer.compare(o1.getNumeroCodigo(), o2.getNumeroCodigo());
	}

	
	
}
