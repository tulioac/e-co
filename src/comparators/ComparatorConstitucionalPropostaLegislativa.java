package comparators;

import java.util.Comparator;

import enums.TipoProjeto;
import interfaces.PropostaLegislativa;

public class ComparatorConstitucionalPropostaLegislativa implements Comparator<PropostaLegislativa>  {

	
	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		
		//Considera 1-PEC, 2-PLP, 3-PL
		
		if(o1.getTipoDoProjeto().equals(o2.getTipoDoProjeto())) {
			return 0;
		}
		if(o1.getTipoDoProjeto().equals(TipoProjeto.PEC)) {
			return -1;
		}
		if(o2.getTipoDoProjeto().equals(TipoProjeto.PEC)) {
			return 1;
		}
		if(o1.getTipoDoProjeto().equals(TipoProjeto.PLP)) {
			return -1;
		}
		if(o2.getTipoDoProjeto().equals(TipoProjeto.PLP)) {
			return 1;
		}
		if(o1.getTipoDoProjeto().equals(TipoProjeto.PL)) {
			return -1;
		}
		if(o2.getTipoDoProjeto().equals(TipoProjeto.PL)) {
			return 1;
		}
		return 0;
	}
}
