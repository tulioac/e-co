package enums;

import java.io.Serializable;

/**
 * Enumera todas as possíveis situações de votação de um projeto.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public enum SituacaoVotacao implements Serializable {
    EM_VOTACAO, REJEITADO, APROVADO
}
