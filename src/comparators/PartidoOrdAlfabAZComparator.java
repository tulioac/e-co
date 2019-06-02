package comparators;

import java.util.Comparator;

import entities.Partido;

/**
 * Essa classe implementa o Comparator para a classe partido. Ela compara
 * em ordem alfabética A-Z os partidos.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PartidoOrdAlfabAZComparator implements Comparator<Partido> {

	@Override
	public int compare(Partido p1, Partido p2) {
		return p1.getNome().compareTo(p2.getNome());
	}

}
