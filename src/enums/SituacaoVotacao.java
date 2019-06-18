package enums;

import java.io.Serializable;

/**
 * Enumera todas as situações de votação de um projeto.
 */
public enum SituacaoVotacao implements Serializable {
    EM_VOTACAO, REJEITADA, APROVADO, ARQUIVADO;
}
