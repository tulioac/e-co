package entities;

import enums.Projetos;
import enums.SituacaoVotacao;
import interfaces.PropostaLegislativa;

import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa XXXX.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public abstract class Projeto implements PropostaLegislativa {

    protected Projetos tipoDoProjeto;
    protected String localDeVotacao;
    private String codigo;
    private String dniAutor;
    private int ano;
    private String ementa;
    private String interesses;
    private SituacaoVotacao situacaoAtual;
    private String endereco;
    private List<String> votacoes;

    public Projeto(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco) {
        super();
        this.codigo = codigo;
        this.dniAutor = dniAutor;
        this.ano = ano;
        this.ementa = ementa;
        this.interesses = interesses;
        this.endereco = endereco;
        this.situacaoAtual = SituacaoVotacao.EM_VOTACAO;
        this.localDeVotacao = "CCJC";
        this.votacoes = new ArrayList<>();
    }

    public Projetos getTipoDoProjeto() {
        return this.tipoDoProjeto;
    }

    public String exibeSituacaoAtual() {
        StringBuilder situacaoAtual = new StringBuilder(this.situacaoAtual.toString().replace("_", " ") + " (" + this.localDeVotacao + ")");
        return situacaoAtual.toString();
    }

    public int getAno() {
        return this.ano;
    }

    public String getLocalDeVotacao(){
        return this.localDeVotacao;
    }

    public void setLocalDeVotacao(String localDeVotacao){
        this.localDeVotacao = localDeVotacao;
    }

    public String getInteresses() {
        return this.interesses;
    }


    @Override
    public String toString() {
        return this.codigo + " - " + this.dniAutor + " - " + this.ementa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Projeto other = (Projeto) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }
}
