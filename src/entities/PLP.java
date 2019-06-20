package entities;

import enums.TipoDeProjetos;

public class PLP extends Projeto {

    private String artigos;

    public PLP(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.artigos = artigos;
        this.tipoDoProjeto = TipoDeProjetos.PLP;
    }

    @Override
    public void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado) {
        if (qntDeputadosPresentes < qntTotalDeputado / 2 + 1)
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
    }

    @Override
    public String toString() {
        StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Lei Complementar - " + super.toString() + " - " + this.getArtigos() + " - " + this.exibeSituacaoAtual());

        return representacaoDeProjeto.toString();
    }

    private String getArtigos() {
        return this.artigos.replace(",", ", ");
    }
}
