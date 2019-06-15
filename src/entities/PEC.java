package entities;

import enums.TipoDeProjetos;

public class PEC extends Projeto {
	/**
     * Armazena uma string contendo os artigos que foram referenciados na PEC.
     */
    private String artigos;
    /**
     * Constrói uma PEC inicializando o tipo de projeto como PEC.
     */
    public PEC(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.artigos = artigos;
        this.tipoDoProjeto = TipoDeProjetos.PEC;
    }
    
    /**
     * Retorna uma representaçao em String da PEC sobreescrevendo o método que foi criado na 
     * classe Projeto exibindo os artigos que foram referenciados nessa PEC.
     *
     * @return string no formato Projeto de Emenda Constitucional - codigo - dni do autor do projeto - ementa - artigos - situacao.
     */
    @Override
    public String toString() {
        StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Emenda Constitucional - " + super.toString() + " - " + this.getArtigos() + " - " + this.exibeSituacaoAtual());

        return representacaoDeProjeto.toString();
    }

    /**
     * Retorna os artigos referenciados nessa PEC.
     *
     * @return String contendo os artigos referenciados separando-os com um espaço e virgula.
     */
    private String getArtigos() {
        return this.artigos.replace(",", ", ");
    }
}