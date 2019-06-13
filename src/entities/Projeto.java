package entities;

import java.util.Set;

import interfaces.PropostaLegislativa;
import util.Projetos;
import util.SituacaoVotacao;
/**
 * Essa classe representa XXXX.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public abstract class Projeto implements PropostaLegislativa{

	private String dniAutor;

    private String codigo;
	
	private int ano;
	
	private String ementa;
	
	private Set <String> interesses;
	
	private SituacaoVotacao situacaoAtual;
	
	private String endereco;

	protected Projetos tipoDoProjeto;

	public Projeto(String dniAutor, int ano, String ementa, Set<String> interesses, String endereco) {
		super();
		this.dniAutor = dniAutor;
		this.ano = ano;
		this.ementa = ementa;
		this.interesses = interesses;
		this.endereco = endereco;
		this.situacaoAtual = SituacaoVotacao.EM_VOTACAO;
	}

    @Override
    public String toString() {
        return this.codigo + this.dniAutor + this.ementa;
    }

    /**
	@Override
	public SituacaoVotacao getSituacaoAtual() {
		this.situacaoAtual;
		*/

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
