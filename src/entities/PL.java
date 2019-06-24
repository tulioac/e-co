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

    /**
     * Esse método realiza a votação do plenário.
     *
     * @param qntPoliticosFavoraveis a quantidade de políticos favoráveis a votação.
     * @param qntPoliticosPresentes  a quantidade políticos presentes na votação.
     * @param status                 o status da votação.
     */
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

    /**
     * Esse método avalia o resultado da votação da Comissão. Sendo possível encerrá-la ou aprová-la e aumentando
     * a quantidade de leis criadas pelo autor. E finalizando-a caso seja recusada e a PL seja conclusiva.
     *
     * @param proximoLocal    o próximo local de votação.
     * @param resultado       o resultado da votação.
     * @param autorDaProposta o deputado autor da proposta.
     */
    @Override
    public void avaliaResultado(String proximoLocal, boolean resultado, Pessoa autorDaProposta) {
        if (conclusivo && !resultado)
            this.encerraVotacao();

        super.avaliaResultado(proximoLocal, resultado, autorDaProposta);
    }

    /**
     * Esse método avalia o resultado da votação do Plenário. Sendo possível encerrá-la ou aprová-la e aumentando
     * a quantidade de leis criadas pelo autor.
     *
     * @param resultado       o resultado da votação.
     * @param autorDaProposta o deputado autor da proposta.
     */
    @Override
    public void avaliaResultado(boolean resultado, Pessoa autorDaProposta) {
        if (resultado) {
            this.aprovaVotacao();

            autorDaProposta.aumentaLeis();
        } else {
            this.encerraVotacao();
        }
    }

    /**
     * Retorna uma representaçao em String do projeto. No formato:
     * Projeto de Lei - codigo - dniAutor - ementa - status de votacão atual (local de votação atual)
     * ou
     * Projeto de Lei - codigo - dniAutor - ementa - Conclusiva - status de votacão atual (local de votação atual)
     * caso seja conclusiva.
     *
     * @return string no formato codigo - dni do autor do projeto - ementa.
     */
    @Override
    public String toString() {
        StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Lei - " + super.toString() + " - ");

        if (conclusivo)
            representacaoDeProjeto.append("Conclusiva - ");
        representacaoDeProjeto.append(this.exibeSituacaoAtual());

        return representacaoDeProjeto.toString();
    }
}