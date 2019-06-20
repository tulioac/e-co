package entities;

import enums.StatusGovernistas;
import enums.TipoDeProjetos;

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

    public PL(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, boolean conclusivo) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.conclusivo = conclusivo;
        this.tipoDoProjeto = TipoDeProjetos.PL;
    }

    @Override
    public void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado) {
        if (qntDeputadosPresentes < qntTotalDeputado / 2 + 1)
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
    }

    @Override
    public boolean votarPlenario(int qntPoliticosFavoraveis, int qntPoliticosPresentes, StatusGovernistas status) {
        boolean resultado = false;

        if (status == StatusGovernistas.OPOSICAO) {
            if (qntPoliticosPresentes - qntPoliticosFavoraveis >= qntPoliticosPresentes / 2 + 1)
                resultado = true;
        } else {
            if (qntPoliticosFavoraveis >= qntPoliticosPresentes / 2 + 1)
                resultado = true;
        }

        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Lei - " + super.toString() + " - ");

        if (conclusivo)
            representacaoDeProjeto.append("Conclusiva - ");

        representacaoDeProjeto.append(this.exibeSituacaoAtual());

        return representacaoDeProjeto.toString();
    }
}