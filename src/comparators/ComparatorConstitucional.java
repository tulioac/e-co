package comparators;

import java.util.Comparator;

import enums.TipoProjeto;
import interfaces.PropostaLegislativa;

public class ComparatorConstitucional implements Comparator<PropostaLegislativa>  {

	@Override
	public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
		
		if(o1.getTipoDoProjeto() == o2.getTipoDoProjeto()) {
			return 0;
		}
		if(o1.getTipoDoProjeto() == TipoProjeto.PEC) {
			return 1;
		}
		if(o2.getTipoDoProjeto() == TipoProjeto.PEC) {
			return -1;
		}
		if(o1.getTipoDoProjeto() == TipoProjeto.PLP) {
			return 1;
		}
		if(o2.getTipoDoProjeto() == TipoProjeto.PLP) {
			return -1;
		}
		return 0;
	}
}
