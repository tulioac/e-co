package interfaces;

import enums.CargosPoliticos;

/**
 * Essa interface representa o cargo político de uma pessoa.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */

public interface CargoPolitico {

    /**
     * Esse método retorna o nome do cargo político.
     */
    public CargosPoliticos getNomeCargo();

    /**
     * Esse método retorna a data de início do mandato político.
     */
    public String getDataDeInicio();

    /**
     * Esse método retorna a quantidade de leis elaboradas por um politico.
     */
    public int getLeis();


    /**
     * Esse método retorna a representação textual adicional do cargo político.
     */
    public String toString();
}
