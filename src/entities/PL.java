package entities;

import enums.StatusGovernista;
import enums.TipoProjeto;
import java.io.Serializable;

/**
 * Essa classe representa um projeto de lei.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PL extends Projeto implements Serializable {
    /**
     * Armazena se a PL é conclusiva ou nao.
     */
    private boolean conclusivo;

    /**
     * Constrói uma PL a partir dos parametros estabelecidos na classe abstrata projeto. Altera
     * o tipo do projeto para PL e conclusivo para true.
     */
    public PL(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, boolean conclusivo) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.conclusivo = conclusivo;
        this.setTipoDoProjeto(TipoProjeto.PL);
    }

    /**
     * Retorna uma representaçao em String da PL sobreescrevendo o método
     * que foi criado na classe Projeto exibindo se o a PL foi conclusiva ou nao.
     *
     * @return string no formato Projeto de lei - codigo - dni do autor do projeto - ementa.
     */
    @Override
    public void verificaQuorumMinimo(int qntDeputadosPresentes, int qntTotalDeputado) {
        if (qntDeputadosPresentes < qntTotalDeputado / 2 + 1)
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
    }

    @Override
    public boolean votarPlenario(int qntPoliticosFavoraveis, int qntPoliticosPresentes, StatusGovernista status) {
        boolean resultado = false;

        if (status == StatusGovernista.OPOSICAO) {
            if (qntPoliticosPresentes - qntPoliticosFavoraveis >= qntPoliticosPresentes / 2 + 1)
                resultado = true;
        } else {
            if (qntPoliticosFavoraveis >= qntPoliticosPresentes / 2 + 1)
                resultado = true;
        }

        return resultado;
    }

    @Override
    public void avaliaResultado(String proximoLocal, boolean resultado, Pessoa autorDaProposta)  {
        if (conclusivo && !resultado)
            this.encerraVotacao();

        super.avaliaResultado(proximoLocal, resultado, autorDaProposta);
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