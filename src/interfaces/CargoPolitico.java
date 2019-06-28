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
    CargosPoliticos getNomeCargo();

    /**
     * Esse método retorna a data de início do mandato político.
     */
    String getDataDeInicio();

    /**
     * Esse método retorna a quantidade de leis elaboradas por um politico.
     */
    int getLeis();

    /**
     * Esse método retorna a representação textual adicional do cargo político.
     */
    String toString();

    /**
     * Esse método adiciona uma lei aprovada à uma pessoa que possui um Cargo Político.
     */
    void aumentaLeis();
}
