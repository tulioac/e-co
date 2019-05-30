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
	private int leis;
	
	public Deputado() {
		this.leis = 0;
	}
	
	@Override
	public String getNomeCargo() {
		return "Deputado";
	}
}