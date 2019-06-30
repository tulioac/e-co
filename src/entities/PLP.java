package entities;

import enums.SituacaoVotacao;
import enums.StatusGovernista;
import enums.TipoProjeto;

import java.io.Serializable;

/**
 * Essa classe representa um projeto de lei complementar.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PLP extends Projeto implements Serializable {
    /**
     * Armazena uma string contendo os artigos que foram referenciados na PLP.
     */
    private String artigos;

    /**
     * Constrói um Projeto do tipo PLP inicializando o tipo de projeto como PLP.
     */
    public PLP(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, String artigos) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.artigos = artigos;
        this.setTipoDoProjeto(TipoProjeto.PLP);
    }

    /**
     * Esse método altera o próximo local de votação da comissão.
     * Fazendo a análise para caso o mesmo vá para o plenário e
     * o encaminha para o primeiro turno.
     *
     * @param proximoLocal o próximo local de votação.
     */
    public void alteraNovoLocal(String proximoLocal) {
        if (proximoLocal.equals("plenario")) {
            this.setNovoLocalDeVotacao("Plenario - 1o turno");
        } else {
            this.setNovoLocalDeVotacao(proximoLocal);
        }
    }

    /**
     * Retorna uma representaçao em String da PLP sobrescrevendo o método toString que foi criado na
     * classe Projeto e exibindo os artigos que foram referenciados nessa PLP.
     *
     * @return string no formato Projeto de Emenda Constitucional - codigo - dni do autor do projeto - ementa - artigos - situacao.
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

        if (qntPoliticosFavoraveis >= qntPoliticosPresentes / 2 + 1)
            resultado = true;

        return (status == StatusGovernista.OPOSICAO) != resultado;
    }

    /**
     * Esse método avalia o resultado da votação do Plenário. Sendo possível encerrá-la ou aprová-la e aumentando
     * a quantidade de leis criadas pelo autor. Sendo necessário aprovar no 1º e 2º turno.
     *
     * @param resultado       o resultado da votação.
     * @param autorDaProposta o deputado autor da proposta.
     */
    @Override
    public void avaliaResultado(boolean resultado, Pessoa autorDaProposta) {
        if (this.getLocalDeVotacao().equals("Plenario - 1o turno")) {
            if (resultado) {
                this.alteraSituacaoDoUltimoLocal(SituacaoVotacao.APROVADO);
                this.setNovoLocalDeVotacao("Plenario - 2o turno");
            } else {
                this.encerraVotacao();
            }

        } else if (this.getLocalDeVotacao().equals("Plenario - 2o turno")) {
            if (resultado) {
                this.aprovaVotacao();

                autorDaProposta.aumentaLeis();
            } else {
                this.encerraVotacao();
            }
        }
    }

    /**
     * Retorna uma representaçao em String do projeto. No formato:
     * Projeto de Lei Complementar - codigo - dniAutor - ementa - artigos - status de votacão atual (local de votação atual)
     *
     * @return string no formato codigo - dni do autor do projeto - ementa.
     */
    @Override
    public String toString() {
        StringBuilder representacaoDeProjeto =
                new StringBuilder("Projeto de Lei Complementar - " + super.toString() + " - " + this.getArtigos() + " - ");

        if (this.exibeSituacaoAtual().equals("REJEITADO"))
            representacaoDeProjeto.append("ARQUIVADO");
        else
            representacaoDeProjeto.append(this.exibeSituacaoAtual());

        return representacaoDeProjeto.toString();
    }

    /**
     * Retorna os artigos referenciados nessa PLP.
     *
     * @return String contendo os artigos referenciados separando-os com um espaço e virgula.
     */
    private String getArtigos() {
        return this.artigos.replace(",", ", ");
    }
}