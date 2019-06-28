package comparators;

import interfaces.PropostaLegislativa;

import java.util.Comparator;

/**
 * Essa classe implementa o Comparator para a interface PropostaLegislativa. Ela compara a
 * proximidade da conclusão das propostas.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class ComparatorConclusaoPropostaLegislativa implements Comparator<PropostaLegislativa> {

    /**
     * Esse método compara a proximidade da conclusão das propostas.
     */
    @Override
    public int compare(PropostaLegislativa o1, PropostaLegislativa o2) {
        if (o1.getLocalDeVotacao().equals(o2.getLocalDeVotacao())) {
            return 0;
        }
        if ("plenario".equals(o1.getLocalDeVotacao())) {
            return -1;
        }
        if ("plenario".equals(o2.getLocalDeVotacao())) {
            return 1;
        }

        return Double.compare(o2.getVotacoes().size(), o1.getVotacoes().size());
    }
}
