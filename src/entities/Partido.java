package entities;

/**
 * Essa classe representa um Partido.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Partido {

	/**
	 * String que representa o nome do partido.
	 */
	private String nome;
	
	/**
	 * Constrói um partido a partir do seu nome.
	 * 
	 * @param nome String com o nome do partido.
	 */
	public Partido(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Retorna o nome do partido.
	 * 
	 * @return uma string com o nome do partido.
	 */
	public String getNome() {
		return this.nome;
	}
}