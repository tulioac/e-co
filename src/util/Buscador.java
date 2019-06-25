package util;

import java.util.ArrayList;
import java.util.List;
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
		//[ok]- Transforma o Set de PropostasLegislativas em List
		//[ok]- Confere os interesses em comum do usuário com o das PropostasLegislativas
		//[ok]- Confere se o status da PropostaLegislativa é EM_VOTACAO
		//[ok]- Caso haja uma proposta com mais interesses em comum, a retorna
		//[ok]- Caso não haja nenhuma proposta com pelo menos um interesse em comum retorna ""
		//[]- Caso dê empate entre mais de uma proposta em número de interesses em comum,
		//popula um ArrayList contendo somente essas propostas e utiliza o Collections.sort
		//para descobrir a mais relacionada, usando o comparator definido em estrategiaAtual.
		//[]- Se o primeiro lugar for único, retorna ele como mais relacionado.
		//[]- Se não o for, retorna um ArrayList das propostas equivalentes.
		//[]- Desse ArrayList vê a proposta mais antiga pelo ano.
		//[]- Se ainda for igual retorna o que tiver sido cadastrado primeiro. Vê isso através
		//do código da proposta.
		//[]- Retorna a proposta mais relacionada.
		
		//List<String> interessesComuns = new ArrayList<>();
		
		//Filtra propostas que não possuem Situacao EM_VOTACAO
		List<PropostaLegislativa> propostasEmVotacao = filtraPropostasPorSituacaoVotacao(propostas, "em votacao");
		
		//Confere proposta por proposta vendo qual o maior número de interesses em comum
		int maiorQntdInteressesComuns = -1;
		for(PropostaLegislativa proposta: propostasEmVotacao) {
			int qntInterComuns = getQntdInteressesEmComum(proposta, interessesUsuario);
			if(qntInterComuns > maiorQntdInteressesComuns) {
				maiorQntdInteressesComuns = qntInterComuns;
			}
		}
		
		//Percorre as propostas válidas novamente e remove as que possuem número de interesses
		//em comum menor que a maior quantidade de interesses em comum encontrada no passo anterior
		for(PropostaLegislativa proposta: propostasEmVotacao) {
			if(maiorQntdInteressesComuns > getQntdInteressesEmComum(proposta, interessesUsuario)) {
				propostasEmVotacao.remove(proposta);
			}
		}
		
		//Mudando a referência para deixar o código mais legível
		List<PropostaLegislativa> propostasMaisRelacionadas = propostasEmVotacao;
		
		if(propostasMaisRelacionadas.size() == 0) {
			return null;
		}else if(propostasMaisRelacionadas.size() == 1) {
			return propostasMaisRelacionadas.get(0);
		}
		
		
	}
	
	private List<PropostaLegislativa> filtraPropostasPorSituacaoVotacao(Set<PropostaLegislativa> propostas, String situacao){
		//Filtra propostas que não possuem Situacao passada
		List<PropostaLegislativa> propostasSituacao = new ArrayList<>(propostas);
		for(PropostaLegislativa proposta: propostas) {
			if(!proposta.getSituacaoAtual().equals(situacao)) {
				propostasSituacao.remove(proposta);
			}
		}
		return propostasSituacao;
	}
	
	private int getQntdInteressesEmComum(PropostaLegislativa proposta, String[] interessesUsuario) {
		
		List<String> interessesProposta = new ArrayList<>();
		for(String interesseProposta: proposta.getInteresses().split(",")) {
			interessesProposta.add(interesseProposta);
		}
		
		int interessesEmComum = 0;
		for(String interesseUsuario: interessesUsuario) {
			if(interessesProposta.contains(interesseUsuario)) {
				interessesEmComum++;
			}
		}
		return interessesEmComum;
	}
	
}
