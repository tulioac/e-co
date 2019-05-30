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

	private String cargo;
	
	private int leis;
	
	public Deputado() {
		this.cargo = "Deputado";
		this.leis = 0;
	}
	
	@Override
	public String getCargo() {
		return this.cargo;
	}
}