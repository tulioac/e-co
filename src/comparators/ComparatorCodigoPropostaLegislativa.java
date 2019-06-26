package comparators;

import java.util.Comparator;

import interfaces.PropostaLegislativa;

public class ComparatorCodigoPropostaLegislativa implements Comparator<PropostaLegislativa>{

	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		return o2.getNumeroCodigo() - o1.getNumeroCodigo();
	}

	
	
}
