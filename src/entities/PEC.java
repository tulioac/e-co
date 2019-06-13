package entities;

import java.util.Set;

import util.SituacaoVotacao;

public class PEC extends Projeto{
	
	private Set <String> artigos;
	
	private String nome;
	
	public PEC(String dniAutor, int ano, String ementa, Set<String> interesses, String endereco, Set <String> artigo) {
		super(dniAutor, ano, ementa, interesses, endereco);
		this.artigos = artigos;
		this.nome = "Projeto de Emenda Constitucional";
	}

	@Override
	public SituacaoVotacao getSituacaoAtual() {
		return ;
	}
	
	//falta situacao
	@Override
	public String toString() {
		return this.nome + " - " + this.codigo + " - " + this.dniAutor + " - " + this.ementa + " - " + this.artigos + " - ";
	}
	
}
