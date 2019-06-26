package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import comparators.ComparatorAprovacaoPropostaLegislativa;
import comparators.ComparatorCodigoPropostaLegislativa;
import comparators.ComparatorConclusaoPropostaLegislativa;
import comparators.ComparatorConstitucionalPropostaLegislativa;
import comparators.ComparatorIdadePropostaLegislativa;
import enums.EstrategiaBusca;
import interfaces.PropostaLegislativa;

public class Buscador{
	
	private Comparator<PropostaLegislativa> estrategiaAtual;
	private Set<PropostaLegislativa> propostas;
	
	public Buscador(Set<PropostaLegislativa> propostas) {
		this.estrategiaAtual = new ComparatorConstitucionalPropostaLegislativa();
		this.propostas = propostas;
	}
	
	public void setPropostas(Set<PropostaLegislativa> propostas) {
		this.propostas = propostas;
	}
	
	public void setEstrategiaAtual(EstrategiaBusca estrategiaAtual) {
		if(EstrategiaBusca.APROVACAO.equals(estrategiaAtual)) {
			this.estrategiaAtual = new ComparatorAprovacaoPropostaLegislativa();
		}
		if(EstrategiaBusca.CONCLUSAO.equals(estrategiaAtual)) {
			this.estrategiaAtual = new ComparatorConclusaoPropostaLegislativa();
		}
		if(EstrategiaBusca.CONSTITUCIONAL.equals(estrategiaAtual)) {
			this.estrategiaAtual = new ComparatorConstitucionalPropostaLegislativa();
		}
	}
	
	public String buscaMaisRelacionado(String[] interessesUsuario) {
		//Filtra propostas "EM VOTACAO"
		Set<PropostaLegislativa> propostasEmVotacao = this.propostas
				.stream()
				.filter(proposta -> "EM VOTACAO".equals(proposta.getSituacaoAtual()))
				.collect(Collectors.toSet());

		//Filtra propostas com mais interesses em comum
		Set<PropostaLegislativa> propostasMaisInteressantes = propostasEmVotacao
				.stream()
				.filter(proposta -> (getQntdInteressesEmComum(proposta, interessesUsuario) == getQtdMaxInteressesComuns(interessesUsuario) 
				&& getQntdInteressesEmComum(proposta, interessesUsuario) != 0))
				.collect(Collectors.toSet());	

		List<PropostaLegislativa> propostasMaisRelacionadas = new ArrayList<>(propostasMaisInteressantes);
		
		//Se possuir uma proposta de maior interesse, a retorna
		if(propostasMaisRelacionadas.isEmpty()) {
			return "";
		}else if(propostasMaisRelacionadas.size() == 1) {
			return propostasMaisRelacionadas.get(0).getCodigo();
		}
		
		//Define o modelo a se seguir na filtragem de acordo com a estratégia definida
		PropostaLegislativa propModelo = propostasMaisRelacionadas
				.stream()
				.min(this.estrategiaAtual)
				.get();
		
		//Filtra os itens que não seguirem a estratégia definida
		propostasMaisRelacionadas = propostasMaisRelacionadas
				.stream()
				.filter
				(proposta -> (this.estrategiaAtual.compare(proposta, propModelo) == 0))
				.collect(Collectors.toList());
		
		//Se encontrar uma proposta única, a retorna
		if(propostasMaisRelacionadas.size() == 1) {
			return propostasMaisRelacionadas.get(0).getCodigo();
		}
		
		//Define o menor ano a ser buscado como desempate
		int menorAno = propostasMaisRelacionadas
				.stream()
				.min(new ComparatorIdadePropostaLegislativa())
				.get()
				.getAno();
		//Mantem na lista somente os itens de menor ano
		propostasMaisRelacionadas = propostasMaisRelacionadas.stream().filter(proposta -> proposta.getAno() == menorAno).collect(Collectors.toList());
		
		//Retorna se chegar a somente uma proposta
		if(propostasMaisRelacionadas.size() == 1) {
			return propostasMaisRelacionadas.get(0).getCodigo();
		}

		//Finalmente pega a primeira proposta a ser cadastrada no conjunto e a retorna
		PropostaLegislativa propostaMaisRelacionada = propostasMaisRelacionadas
				.stream()
				.min(new ComparatorCodigoPropostaLegislativa())
				.get();
	
		return propostaMaisRelacionada.getCodigo();
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

	private int getQtdMaxInteressesComuns(String[] interessesUsuario) {
		int maiorQntdInteressesComuns = 0;
		for(PropostaLegislativa proposta: this.propostas) {
			int qntInterComuns = getQntdInteressesEmComum(proposta, interessesUsuario);
			if(qntInterComuns > maiorQntdInteressesComuns) {
				maiorQntdInteressesComuns = qntInterComuns;
			}
		}
		return maiorQntdInteressesComuns;
	}
}
