package entities;

import java.util.Set;

import util.SituacaoVotacao;

/**
 * Essa classe representa um projeto de lei.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PL extends Projeto {
	
	private boolean conclusivo;
	
	private String nome;

	public PL(String dniAutor, int ano, String ementa, Set<String> interesses, String endereco, boolean conclusivo) {
		super(dniAutor, ano, ementa, interesses, endereco);
		this.conclusivo = conclusivo;
		this.nome = "Projeto de Lei";
	}
	
	public String ehConclusivo() {
		if (this.conclusivo) 
			return "Conclusivo";
		return "";
	}
	
	//falta add o codigo - criar o método para geracao do codigo
	@Override
	public String toString() {
		return this.nome + " - "  + " - " + this.dniAutor +
				" - " + this.ementa + " - " + this.ehConclusivo();
	}

	@Override
	public SituacaoVotacao getSituacaoAtual() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
