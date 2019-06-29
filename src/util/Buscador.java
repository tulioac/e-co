package util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import comparators.ComparatorAprovacaoPropostaLegislativa;
import comparators.ComparatorConclusaoPropostaLegislativa;
import comparators.ComparatorConstitucionalPropostaLegislativa;
import comparators.ComparatorIdadePropostaLegislativa;
import comparators.ComparatorOrdemCriacaoPropostaLegislativa;
import enums.EstrategiaBusca;
import interfaces.PropostaLegislativa;

/**
 * Classe que simula um buscador de propostas cadastradas.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Buscador {

    /**
     * Estratégia de desempate atual entre propostas legislativas do buscador.
     */
    private Comparator<PropostaLegislativa> estrategiaAtual;

    /**
     * Conjunto de propostas legislativas cadastradas no sistema
     */
    private Set<PropostaLegislativa> propostas;

    /**
     * Constroi um buscador com base num conjunto de propostas do sistema
     *
     * @param propostas conjunto de propostas já cadastrado no sistema
     */
    public Buscador(Set<PropostaLegislativa> propostas) {
        this.estrategiaAtual = new ComparatorConstitucionalPropostaLegislativa();
        this.propostas = propostas;
    }

    /**
     * Não possui retorno. Redefine o conjunto de propostas do buscador.
     *
     * @param propostas conjunto de propostas já cadastrado no sistema
     */
    public void setPropostas(Set<PropostaLegislativa> propostas) {
        this.propostas = propostas;
    }

    /**
     * Redefine a estratégia de desempate atual por outra presente no enum
     * EstrategiaBusca.
     *
     * @param estrategiaAtual enum contendo qual tipo de critério desempate será usado
     */
    public void setEstrategiaAtual(EstrategiaBusca estrategiaAtual) {
        if (EstrategiaBusca.APROVACAO.equals(estrategiaAtual)) {
            this.estrategiaAtual = new ComparatorAprovacaoPropostaLegislativa();
        }
        if (EstrategiaBusca.CONCLUSAO.equals(estrategiaAtual)) {
            this.estrategiaAtual = new ComparatorConclusaoPropostaLegislativa();
        }
        if (EstrategiaBusca.CONSTITUCIONAL.equals(estrategiaAtual)) {
            this.estrategiaAtual = new ComparatorConstitucionalPropostaLegislativa();
        }
    }

    /**
     * Retorna o código da proposta mais relacionada com os interesses passados como parâmetro.
     * Se não houver interesses em comum entre as propostas do sistema e as propostas passadas
     * como parâmetro, é retornada uma String vazia.
     *
     * @param interessesUsuario array de String contendo os interesses do usuário
     * @return String com o código da proposta mais relacionada com os interesses do usuário
     */
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
        if (propostasMaisRelacionadas.isEmpty()) {
            return "";
        } else if (propostasMaisRelacionadas.size() == 1) {
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
        if (propostasMaisRelacionadas.size() == 1) {
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
        if (propostasMaisRelacionadas.size() == 1) {
            return propostasMaisRelacionadas.get(0).getCodigo();
        }

        //Finalmente pega a primeira proposta a ser cadastrada no conjunto e a retorna
        PropostaLegislativa propostaMaisRelacionada = propostasMaisRelacionadas
                .stream()
                .min(new ComparatorOrdemCriacaoPropostaLegislativa())
                .get();

        return propostaMaisRelacionada.getCodigo();
    }

    /**
     * Retorna a quantidade de interesses em comum que uma proposta tem com o array de interesses
     * passado como parâmetro.
     *
     * @param proposta          proposta legislativa que se quer saber o número de interesses em comum
     * @param interessesUsuario array de String contendo os interesses do usuário
     * @return numero de interesses em comum entre a propostas e os interesses do usuario
     */
    private int getQntdInteressesEmComum(PropostaLegislativa proposta, String[] interessesUsuario) {

        List<String> interessesProposta = new ArrayList<>();
        for (String interesseProposta : proposta.getInteresses().split(",")) {
            interessesProposta.add(interesseProposta);
        }

        int interessesEmComum = 0;
        for (String interesseUsuario : interessesUsuario) {
            if (interessesProposta.contains(interesseUsuario)) {
                interessesEmComum++;
            }
        }
        return interessesEmComum;
    }

    /**
     * Retorna o número maximo de interesses comuns existentes com alguma proposta
     * do conjunto de propostas existentes no buscador.
     *
     * @param interessesUsuario array de String contendo os interesses dos usuarios
     * @return máximo valor de interesses em comum existentes entre alguma das propostas do sistema
     */
    private int getQtdMaxInteressesComuns(String[] interessesUsuario) {
        int maiorQntdInteressesComuns = 0;
        for (PropostaLegislativa proposta : this.propostas) {
            int qntInterComuns = getQntdInteressesEmComum(proposta, interessesUsuario);
            if (qntInterComuns > maiorQntdInteressesComuns) {
                maiorQntdInteressesComuns = qntInterComuns;
            }
        }
        return maiorQntdInteressesComuns;
    }
}
