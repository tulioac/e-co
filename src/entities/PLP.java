package entities;

import java.util.Set;

import util.SituacaoVotacao;

public class PLP extends Projeto{
	
	private Set<String> artigos;
	
	private String nome;

	public PLP(String dniAutor, int ano, String ementa, Set<String> interesses, String endereco, Set<String> artigos) {
		super(dniAutor, ano, ementa, interesses, endereco);
		this.artigos = artigos;
		this.nome = "Projeto de Lei Complementar";
	}

	@Override
	public SituacaoVotacao getSituacaoAtual() {
		return null;
	}
	
	@Override
	public String toString() {
		return this.nome + " - " + this.codigo + " - " + this.dniAutor + " - " + this.ementa + " - " + this.artigos + " - ";
	}
	
	
	
}
