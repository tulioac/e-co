package entities;

import interfaces.CargoPolitico;

/**
 * Essa classe representa um Deputado.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Deputado implements CargoPolitico{
	
	/**
	 * Armazena a quantidade de leis aprovadas.
	 */
	private int leis;
	
	/**
	 * Constrói um deputado inicializando sua quantidade de leis com 0.
	 */
	public Deputado() {
		this.leis = 0;
	}
	
	
	/**
	 * Esse método retorna o nome do cargo político do deputado.
	 */
	@Override
	public String getNomeCargo() {
		return "Deputado";
	}
}