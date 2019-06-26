package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
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
		//[ok]- Transforma o Set de PropostasLegislativas em List
		//[ok]- Confere os interesses em comum do usuário com o das PropostasLegislativas
		//[ok]- Confere se o status da PropostaLegislativa é EM_VOTACAO
		//[ok]- Caso haja uma proposta com mais interesses em comum, a retorna
		//[ok]- Caso não haja nenhuma proposta com pelo menos um interesse em comum retorna ""
		//[ok]- Caso dê empate entre mais de uma proposta em número de interesses em comum,
		//popula um ArrayList contendo somente essas propostas e utiliza o Collections.sort
		//para descobrir a mais relacionada, usando o comparator definido em estrategiaAtual.
		//[ok]- Se o primeiro lugar for único, retorna ele como mais relacionado.
		//[ok]- Se não o for, retorna um ArrayList das propostas equivalentes.
		//[ok]- Desse ArrayList vê a proposta mais antiga pelo ano.
		//[ok]- Se ainda for igual retorna o que tiver sido cadastrado primeiro. Vê isso através
		//do código da proposta.
		//[ok]- Retorna a proposta mais relacionada.
		
		//List<String> interessesComuns = new ArrayList<>();
		
		//Filtra propostas que não possuem Situacao EM_VOTACAO
		Set<PropostaLegislativa> propostasAll = new HashSet<>(this.propostas);
		
		Set<PropostaLegislativa> propostasEmVotacao = this.propostas
				.stream()
				.filter(proposta -> "EM VOTACAO".equals(proposta.getSituacaoAtual()))
				.collect(Collectors.toSet());
		
		System.out.println("|||||||||entrada|||||||||");
		for(PropostaLegislativa proposta: propostasEmVotacao) {
			System.out.println("CODIGO: " + proposta.getCodigo());
		}
		System.out.println("|||||||||entrada|||||||||");
		
		//propostasEmVotacao.stream().
		
		
		
		//Confere proposta por proposta vendo qual o maior número de interesses em comum
//		int maiorQntdInteressesComuns = -1;
//		for(PropostaLegislativa proposta: propostasEmVotacao) {
//			int qntInterComuns = getQntdInteressesEmComum(proposta, interessesUsuario);
//			if(qntInterComuns > maiorQntdInteressesComuns) {
//				maiorQntdInteressesComuns = qntInterComuns;
//			}
//		}
		//Percorre as propostas válidas novamente e remove as que possuem número de interesses
		//em comum menor que a maior quantidade de interesses em comum encontrada no passo anterior
//		for(PropostaLegislativa proposta: new HashSet<PropostaLegislativa>(propostasEmVotacao)) {
//			if(getQntdInteressesEmComum(proposta, interessesUsuario) == 0) {
//				propostasEmVotacao.remove(proposta);
//			}
//			if(getQntdInteressesEmComum(proposta, interessesUsuario) < maiorQntdInteressesComuns) {
//				propostasEmVotacao.remove(proposta);
//			}
//		}
		
		//Mudando a referência para deixar o código mais legível
		//List<PropostaLegislativa> 
		Set<PropostaLegislativa> propostasMaisInteressantes = propostasEmVotacao
				.stream()
				.filter(
						proposta -> 
						(getQntdInteressesEmComum(proposta, interessesUsuario) == getQtdMaxInteressesComuns(interessesUsuario)
						&& getQntdInteressesEmComum(proposta, interessesUsuario) != 0))
				.collect(Collectors.toSet());	
		
		List<PropostaLegislativa> propostasMaisRelacionadas = new ArrayList<>(propostasMaisInteressantes);
		
		//Se não houver nenhuma proposta relacionada retorna null, se houver uma, a retorna
		if(propostasMaisRelacionadas.isEmpty()) {
			return "";
		}else if(propostasMaisRelacionadas.size() == 1) {
			return propostasMaisRelacionadas.get(0).getCodigo();
		}
		
		//Ordena propostasMaisRelacionadas de acordo com a estratégia de busca definida
		Collections.sort(propostasMaisRelacionadas, this.estrategiaAtual);
		System.out.println(this.estrategiaAtual);
		//Confere se há mais de um elegível no critério desempate da estratégia
		propostasMaisRelacionadas = filtraPropostasElegiveisPorEstrategia(propostasMaisRelacionadas, this.estrategiaAtual);
		
		if(propostasMaisRelacionadas.size() == 1) {
			return propostasMaisRelacionadas.get(0).getCodigo();
		}
		
		//Ordena pelo ano das entidades restantes para saber qual é a mais antiga
		Collections.sort(propostasMaisRelacionadas, new ComparatorIdadePropostaLegislativa());
		
		System.out.println("|||||||lista por ano|||||||||||");
		for(int i=0;i<propostasMaisRelacionadas.size();i++) {
			System.out.println("CODIGO: " + propostasMaisRelacionadas.get(i).getCodigo());
		}
		System.out.println("||||||||lista por ano||||||||||");
		
		//int menorAno = propostasMaisRelacionadas.get(0)
		
		//Confere se há mais de um elegível no critério desempate de idade
		propostasMaisRelacionadas = filtraPropostasElegiveisPorEstrategia(propostasMaisRelacionadas, new ComparatorIdadePropostaLegislativa());
		
		
		if(propostasMaisRelacionadas.size() == 1) {
			System.out.println("SAIU AQUI Ó " + propostasMaisRelacionadas.get(0).getCodigo());
			return propostasMaisRelacionadas.get(0).getCodigo();
		}
		System.out.println("|||||||lista|||||||||||");
		for(int i=0;i<propostasMaisRelacionadas.size();i++) {
			System.out.println("CODIGO: " + propostasMaisRelacionadas.get(i).getCodigo());
		}
		System.out.println("||||||||lista||||||||||");
		//Ordena pelo código para alcançar a entidade cadastrada primeiro
		PropostaLegislativa propostaMaisRelacionada = propostasMaisRelacionadas.stream().min(new ComparatorCodigoPropostaLegislativa()).get();
		//Collections.sort(propostasMaisRelacionadas, new ComparatorCodigoPropostaLegislativa());
		
		//Retorna a proposta com o código menor
		//PropostaLegislativa propostaMaisRelacionada = propostasMaisRelacionadas.get(0);
		System.out.println("||||||||final||||||||||");
		System.out.println("CODIGO: " + propostasMaisRelacionadas.get(0).getCodigo());
		System.out.println("|||||||||final|||||||||");
		return propostaMaisRelacionada.getCodigo();
	}
	
	private Set<PropostaLegislativa> filtraPropostasPorSituacaoVotacao(Set<PropostaLegislativa> propostas, String situacao){
		
		Set<PropostaLegislativa> propostasFiltradas = new HashSet<>();
		
		//Filtra propostas que não possuem Situacao passada
		for(PropostaLegislativa proposta: propostas) {
			if(proposta.getSituacaoAtual().equals(situacao)) {
				propostasFiltradas.add(proposta);
				//propostas.remove(proposta);
			}
		}
		return propostasFiltradas;
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

	private List<PropostaLegislativa> filtraPropostasElegiveisPorEstrategia(List<PropostaLegislativa> propostas, Comparator<PropostaLegislativa> comparator){
		List<PropostaLegislativa> propostasElegiveis = new ArrayList<>();
		for(int i=0; i < propostas.size()-1; i++) {
			if(comparator.compare(propostas.get(i), propostas.get(i+1)) == 0) {
				propostasElegiveis.add(propostas.get(i));
				if(i == propostas.size()-2) {
					propostasElegiveis.add(propostas.get(i+1));
				}
			}else {
				propostasElegiveis.add(propostas.get(i));
				break;
			}
		}
		return propostasElegiveis;
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
