package interfaces;

import enums.SituacaoVotacao;
import enums.TipoProjeto;

/**
 * Essa interface representa as possibilidades de propostas legislativas
 * consideradas no sistema.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public interface PropostaLegislativa {
    /**
     * Retorna a situação da proposta legislativa no Congresso.
     *
     * @return a situação da proposta legislativa no Congresso.
     */
    public String exibeSituacaoAtual();
    /**
     * Retorna o ano em que foi criada a proposta legislativa.
     *
     * @return ano de criação da Proposta Legislativa.
     */
    public int getAno();
    /**
     * Retornao tipo da proposta legislativa, se é PL, PLP ou PEC.
     *
     * @return tipo da proposta legislativa
     */
    public TipoProjeto getTipoDoProjeto();
    /**
     * Retorna o local de votação atual da proposta.
     *
     * @return local de votação atual da proposta
     */
    public String getLocalDeVotacao();
    /**
     * Define o novo local de votação da proposta.
     *
     * @param localDeVotacao String contendo o novo local de votação da proposta
     */
    public void setNovoLocalDeVotacao(String localDeVotacao);
    /**
     * Retorna uma String contendo os interesses da proposta legislativa. Os interesses retornados
     * na String são separados por ", ".
     *
     * @return String contendo os interesses da proposta legislativa
     */
    public String getInteresses();
    /**
     * Retorna String contendo a situação atual da proposta legislativa no Congresso.
     *
     * @return String contendo a situação atual da proposta legislativa no Congresso
     */
    public String getSituacaoAtual();
    /**
     * Não possui retorno. Altera a situação de votação do local anterior, visto que já foi votado
     * naquele local. Recebe a situação de votação a ser redefinida.
     *
     * @param situacao situação de votação a sobrepor a anterior
     */
    public void alteraSituacaoDoLocalAnterior(SituacaoVotacao situacao);
    /**
     * Não possui retorno. Define como encerrada uma votação da proposta legislativa no Congresso.
     */
    public void encerraVotacao();
    /**
     * Não possui retorno. Define como aprovada uma votação da proposta legislativa no Congresso.
     */
    public void aprovaVotacao();
    /**
     * Retorna uma String com o dni do Autor da proposta legislativa.
     *
     * @return String contendo o dni do autor da proposta legislativa
     */
    public String getAutor();
    /**
     * Esse método retorna a representaçao em String de um projeto de lei.
     *
     * @return String contendo a representação em String do objeto.
     */
    public String toString();
}