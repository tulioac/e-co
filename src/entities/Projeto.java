package entities;

import java.util.Set;

import interfaces.PropostaLegislativa;
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
	protected String dniAutor;
	
	protected String codigo;
	
	protected int ano;
	
	protected String ementa;
	
	protected Set <String> interesses;
	
	protected SituacaoVotacao situacaoAtual;
	
	protected String endereco;

	public Projeto(String dniAutor, int ano, String ementa, Set<String> interesses, String endereco) {
		super();
		this.dniAutor = dniAutor;
		this.ano = ano;
		this.ementa = ementa;
		this.interesses = interesses;
		this.endereco = endereco;
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
