package entities;

import enums.TipoDeProjetos;

public class PEC extends Projeto {

    private String artigos;

    public PEC(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.artigos = artigos;
        this.tipoDoProjeto = TipoDeProjetos.PEC;
    }

    @Override
    public void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado) {
        if (qntDeputadosPresentes < 3 * qntTotalDeputado / 5 + 1)
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
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