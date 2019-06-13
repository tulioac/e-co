package entities;

import java.util.Set;

import util.Projetos;
import util.SituacaoVotacao;

public class PLP extends Projeto{
	
	private String artigos;
	
	public PLP(String dniAutor, String ano, String ementa, Set<String> interesses, String endereco, String artigos) {
		super(dniAutor, ano, ementa, interesses, endereco);
		this.artigos = artigos;
		this.tipoDoProjeto = Projetos.PLP;
	}

	@Override
	public void setSituacaoAtual(SituacaoVotacao situacaoAtual) {

	}

	@Override
	public SituacaoVotacao getSituacaoAtual() {
		// TODO: Colocar local
		return null;
	}

	@Override
	public String toString() {
		StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Lei Complementar - " + super.toString() + " - " + this.getArtigos() + " - " + this.getSituacaoAtual());

		return representacaoDeProjeto.toString();
	}

	private String getArtigos() {
		return this.artigos.replace(",", ", ");
	}
}
