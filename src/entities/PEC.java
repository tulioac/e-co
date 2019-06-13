package entities;

import enums.Projetos;

public class PEC extends Projeto{
	
	private String artigos;
	
	public PEC(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
		super(codigo, dniAutor, ano, ementa, interesses, endereco);
		this.artigos = artigos;
		this.tipoDoProjeto = Projetos.PEC;
	}

    @Override
    public String toString() {
        StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Emenda Constitucional - " + super.toString() + " - " + this.getArtigos() + " - " + this.exibeSituacaoAtual());

        return representacaoDeProjeto.toString();
    }

    private String getArtigos() {
        return this.artigos.replace(",", ", ");
    }
}