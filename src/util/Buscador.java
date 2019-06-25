package util;

import java.util.Set;

import enums.EstrategiaBusca;
import interfaces.PropostaLegislativa;

public class Buscador {

	private EstrategiaBusca estrategiaAtual;
	private Set<PropostaLegislativa> propostas;
	
	public Buscador(Set<PropostaLegislativa> propostas) {
		this.estrategiaAtual = EstrategiaBusca.CONSTITUCIONAL;
		this.propostas = propostas;
	}
	
	public void setEstrategiaAtual(EstrategiaBusca estrategiaAtual) {
		this.estrategiaAtual = estrategiaAtual;
	}
	
	public PropostaLegislativa buscaMaisRelacionado(String[] interessesUsuario) {
		//- Transforma o Set de PropostasLegislativas em ArrayList
		//- Confere os interesses em comum do usuário com o das PropostasLegislativas
		//- Confere se o status da PropostaLegislativa é EM_TRAMITACAO
		//- Caso haja uma proposta com mais interesses em comum, a retorna
		//- Caso não haja nenhuma proposta com pelo menos um interesse em comum retorna ""
		//- Caso dê empate entre mais de uma proposta em número de interesses em comum,
		//popula um ArrayList contendo somente essas propostas e utiliza o Collections.sort
		//para descobrir a mais relacionada, usando o comparator definido em estrategiaAtual.
		//- Se o primeiro lugar for único, retorna ele como mais relacionado.
		//- Se não o for, retorna um ArrayList das propostas equivalentes.
		//- Desse ArrayList vê a proposta mais antiga pelo ano.
		//- Se ainda for igual retorna o que tiver sido cadastrado primeiro. Vê isso através
		//do código da proposta.
		//- Retorna a proposta mais relacionada.
		
		
	}
	
}
