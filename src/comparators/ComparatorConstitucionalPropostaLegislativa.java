package comparators;

import enums.TipoProjeto;
import interfaces.PropostaLegislativa;

import java.util.Comparator;

/**
 * Essa classe implementa o Comparator para a interface PropostaLegislativa. Ela compara os
 * tipos de propostas. A prioradade natural é: PEC > PLP > PL
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class ComparatorConstitucionalPropostaLegislativa implements Comparator<PropostaLegislativa> {

    /**
     * Esse método compara os tipos de propostas legislativas.
     */
    @Override
    public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {

        //Considera 1-PEC, 2-PLP, 3-PL

        if (o1.getTipoDoProjeto().equals(o2.getTipoDoProjeto())) {
            return 0;
        }
        if (o1.getTipoDoProjeto().equals(TipoProjeto.PEC)) {
            return -1;
        }
        if (o2.getTipoDoProjeto().equals(TipoProjeto.PEC)) {
            return 1;
        }
        if (o1.getTipoDoProjeto().equals(TipoProjeto.PLP)) {
            return -1;
        }
        if (o2.getTipoDoProjeto().equals(TipoProjeto.PLP)) {
            return 1;
        }
        if (o1.getTipoDoProjeto().equals(TipoProjeto.PL)) {
            return -1;
        }
        if (o2.getTipoDoProjeto().equals(TipoProjeto.PL)) {
            return 1;
        }
        return 0;
    }
}
