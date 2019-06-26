package controllers;

import entities.*;
import enums.SituacaoVotacao;
import enums.StatusGovernista;
import enums.TipoProjeto;
import interfaces.PropostaLegislativa;
import services.ComissaoService;
import services.PartidoBaseService;
import services.PessoaService;
import util.Validador;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Essa classe usa o padrão Controller contendo métodos que operam sobre os diferentes
 * tipos de Propostas Legislativas.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class ProjetoController implements Serializable {

    /**
     * Armazena Id de serialização de ProjetoController.
     */
    private static final long serialVersionUID = 5355952377322155764L;
    /**
     * Armazena instancia de PessoaService.
     */
    private PessoaService pessoaService;
    /**
     * Armazena instancia de ComissaoService.
     */
    private ComissaoService comissaoService;
    /**
     * Armazena instancia de PartidoService.
     */
    private PartidoBaseService partidoService;
    /**
     * Armazena um mapa de propostas legislativas em que
     * a chave segue o formato: TipoProjeto numero/ano e
     * o valor é do tipo PropostaLegislativa.
     */
    private Map<String, PropostaLegislativa> propostas;

    /**
     * Constrói um Controlador de Projetos que inicializa um mapa que guarda
     * as propostas legislativas do sistema.
     *
     * @param pessoaService   instancia de PessoaService.
     * @param comissaoService instancia de ComissaoService.
     * @param partidoService  instancia de PartidoService.
     */
    public ProjetoController(PessoaService pessoaService, ComissaoService comissaoService, PartidoBaseService partidoService) {
        this.pessoaService = pessoaService;
        this.comissaoService = comissaoService;
        this.partidoService = partidoService;
        this.propostas = new HashMap<>();
    }

    /**
     * Esse método retorna um inteiro que representa a quantidade
     * de projetos de um ano específico e de um tipo específico.
     *
     * @param tipoProjeto tipo do projeto.
     * @param ano         ano do projeto.
     * @return quantidade de projetos de um ano específico.
     */
    private int contaProjetoEmAno(TipoProjeto tipoProjeto, int ano) {
        int qntProjetosNoAno = 0;

        for (PropostaLegislativa proposta : this.propostas.values())
            if (proposta.getTipoDoProjeto() == tipoProjeto && proposta.getAno() == ano)
                qntProjetosNoAno++;

        return qntProjetosNoAno;
    }

    /**
     * Esse método retorna a string que representa o código
     * gerado para um projeto.
     *
     * @param tipoProjeto tipo do projeto.
     * @param ano         ano do projeto.
     * @return codigo gerado para o projeto.
     */
    private String criaCodigo(TipoProjeto tipoProjeto, int ano) {
        int numeroDoProjeto = contaProjetoEmAno(tipoProjeto, ano) + 1;

        return tipoProjeto.toString() + " " + numeroDoProjeto + "/" + ano;
    }

    /**
     * Esse método verifica o documento nacional de identificação.
     *
     * @param dni documento nacional de identificação.
     * @throws NullPointerException     pessoa inexistente.
     * @throws IllegalArgumentException pessoa nao é deputado.
     */
    private void verificaDni(String dni) {
        if (!(this.pessoaService.ehPessoaCadastrada(dni)))
            throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");

        if (!(this.pessoaService.ehDeputado(dni)))
            throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
    }

    /**
     * Esse método valida as entradas de projeto controller.
     *
     * @param dni        dni do autor do projeto.
     * @param ano        ano do projeto.
     * @param ementa     ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url        url do projeto.
     */
    private void validaEntradasDoProjeto(String dni, int ano, String ementa, String interesses, String url) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
        this.verificaDni(dni);
        v.validaAno(ano);
        v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
    }

    /**
     * Esse método cadastra um novo projeto de lei e retorna o seu código.
     *
     * @param dni        dni do autor.
     * @param ano        ano do projeto.
     * @param ementa     ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url        url do projeto.
     * @param conclusivo conclusividade do projeto.
     * @return string que representa codigo do projeto de lei.
     */
    public String cadastraPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        validaEntradasDoProjeto(dni, ano, ementa, interesses, url);
        new Validador().validaNull(conclusivo, "Erro ao cadastrar projeto: conclusivo nao pode ser nula");

        String codigo = criaCodigo(TipoProjeto.PL, ano);
        this.propostas.put(codigo, new PL(codigo, dni, ano, ementa, interesses, url, conclusivo));

        return codigo;
    }

    /**
     * Esse método cadastra um novo projeto de lei complementar e retorna seu código.
     *
     * @param dni        dni do autor.
     * @param ano        ano do projeto.
     * @param ementa     ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url        url do projeto.
     * @param artigos    artigos do projeto.
     * @return string que representa código do projeto de lei complementar.
     */
    public String cadastraPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaEntradasDoProjeto(dni, ano, ementa, interesses, url);
        new Validador().validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");

        String codigo = criaCodigo(TipoProjeto.PLP, ano);
        this.propostas.put(codigo, new PLP(codigo, dni, ano, ementa, interesses, url, artigos));

        return codigo;
    }

    /**
     * Esse método cadastra um novo projeto de emenda constitucional e retorna o seu código.
     *
     * @param dni        dni do autor.
     * @param ano        ano do projeto.
     * @param ementa     ementa do projeto.
     * @param interesses interesses do projeto.
     * @param url        url do projeto.
     * @param artigos    artigos do projeto.
     * @return string que representa o codigo do projeto de emenda constitucional.
     */
    public String cadastraPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        validaEntradasDoProjeto(dni, ano, ementa, interesses, url);
        new Validador().validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");

        String codigo = criaCodigo(TipoProjeto.PEC, ano);
        this.propostas.put(codigo, new PEC(codigo, dni, ano, ementa, interesses, url, artigos));

        return codigo;
    }

    /**
     * Esse método retorna uma string contendo a descrição do projeto.
     *
     * @param codigo codigo do projeto.
     * @return string que representa o projeto.
     */
    public String exibirProjeto(String codigo) {
        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao exibir projeto: codigo nao cadastrado");

        return this.propostas.get(codigo).toString();
    }

    /**
     * Esse método avalia o resultado da votação e encaminha ou encerra tramitação.
     *
     * @param proximoLocal proximo local do projeto.
     * @param proposta     projeto.
     * @param resultado    resultado da votação atual.
     */
    private void avaliaResultado(String proximoLocal, PropostaLegislativa proposta, boolean resultado) {
        Pessoa autorDaProposta = pessoaService.getPessoaPeloDni(proposta.getAutor());

        proposta.avaliaResultado(proximoLocal, resultado, autorDaProposta);
    }

    /**
     * Esse método cruza os interesses dos políticos com os da proposta e
     * retorna a quantidade de políticos interessados.
     *
     * @param comissao comissão.
     * @param projeto  projeto.
     * @return inteiro que representa quantidade de políticos interessados na proposta.
     */
    private int contaPoliticosInteressados(Comissao comissao, PropostaLegislativa projeto) {
        int politicosInteressados = 0;

        for (String deputado : comissao.getIntegrantes())
            for (String interesses : this.pessoaService.getPessoaPeloDni(deputado).getInteresses().split(","))
                if (projeto.getInteresses().contains(interesses)) {
                    politicosInteressados++;
                    break;
                }

        return politicosInteressados;
    }

    /**
     * Esse método conta os políticos governistas que fazem parte
     * de uma comissão.
     *
     * @param comissao comissão.
     * @return inteiro que representa a quantidade de políticos governistas.
     */
    private int contaPoliticosGovernistas(Comissao comissao) {
        int qntPoliticosGovernistas = 0;

        for (String deputado : comissao.getIntegrantes())
            if (this.partidoService.containsPartido(this.pessoaService.getPessoaPeloDni(deputado).getPartido()))
                qntPoliticosGovernistas++;

        return qntPoliticosGovernistas;
    }

    /**
     * Esse método vota o projeto em uma comissao com base em um status governista
     * e retorna o resultado da votação.
     *
     * @param status   status do projeto na votação.
     * @param comissao comissão.
     * @param proposta proposta.
     * @return true se aprovado.
     */
    private boolean votarComissao(StatusGovernista status, Comissao comissao, PropostaLegislativa proposta) {
        int qntDePoliticosDaComissao = comissao.getIntegrantes().size();

        int qntPoliticosFavoraveis;

        if (status == StatusGovernista.LIVRE)
            qntPoliticosFavoraveis = contaPoliticosInteressados(comissao, proposta);
        else
            qntPoliticosFavoraveis = contaPoliticosGovernistas(comissao);

        return proposta.votarComissao(qntPoliticosFavoraveis, qntDePoliticosDaComissao, status);
    }

    /**
     * Esse método vota o projeto na comissão e retorna o resultado
     * da votação.
     *
     * @param codigo           codigo do projeto.
     * @param statusGovernista status do projeto.
     * @param proximoLocal     próximo local de votação do projeto.
     * @return resultado da votação.
     */
    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        Validador v = new Validador();
        v.validaString(proximoLocal, "Erro ao votar proposta: proximo local vazio");
        v.validaStatus(statusGovernista, "Erro ao votar proposta: status invalido");

        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao votar proposta: projeto inexistente");

        PropostaLegislativa proposta = this.propostas.get(codigo);

        if (proposta.getLocalDeVotacao().equals("Plenario - 1o turno") || proposta.getLocalDeVotacao().equals("Plenario - 2o turno") || proposta.getLocalDeVotacao().equals("plenario"))
            throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");

        if (proposta.getSituacaoAtual().equals(SituacaoVotacao.REJEITADO.toString()))
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");

        Comissao comissao;

        if (!(this.comissaoService.containsComissao(proposta.getLocalDeVotacao())))
            throw new NullPointerException("Erro ao votar proposta: " + proposta.getLocalDeVotacao() + " nao cadastrada");
        else {
            comissao = this.comissaoService.getComissao(proposta.getLocalDeVotacao());
        }

        StatusGovernista status = StatusGovernista.valueOf(statusGovernista);

        boolean resultado = this.votarComissao(status, comissao, proposta);

        avaliaResultado(proximoLocal, proposta, resultado);

        proposta.alteraNovoLocal(proximoLocal);

        return resultado;
    }

    /**
     * Esse método verifica se existe quórum mínimo para votação do projeto.
     *
     * @param presentes políticos presentes na votação.
     * @param proposta  proposta.
     */
    private void verificaQuorumMinimo(String presentes, PropostaLegislativa proposta) {
        int qntDeputadosPresentes = presentes.split(",").length;
        int qntTotalDeputado = pessoaService.contaDeputados();

        proposta.verificaQuorumMinimo(qntDeputadosPresentes, qntTotalDeputado);
    }

    /**
     * Esse método vota o projeto no plenário e retorna se foi aprovado ou não.
     *
     * @param status    status do projeto.
     * @param proposta  projeto.
     * @param presentes presentes na votação.
     * @return true se aprovado.
     */
    private boolean votarPlenario(StatusGovernista status, PropostaLegislativa proposta, String presentes) {
        String[] listaDePresentes = presentes.split(",");

        int qntPoliticosFavoraveis;

        if (status == StatusGovernista.LIVRE)
            qntPoliticosFavoraveis = contaPoliticosInteressados(listaDePresentes, proposta);
        else
            qntPoliticosFavoraveis = contaPoliticosGovernistas(listaDePresentes);

        int qntPoliticosPresentes = listaDePresentes.length;

        return proposta.votarPlenario(qntPoliticosFavoraveis, qntPoliticosPresentes, status);
    }

    /**
     * Esse método cruza os interesses dos políticos com os da proposta e retorna
     * a quantidade de políticos interessados.
     *
     * @param listaDePresentes presentes na votação.
     * @param projeto          projeto.
     * @return quantidade de políticos interessados no projeto.
     */
    private int contaPoliticosInteressados(String[] listaDePresentes, PropostaLegislativa projeto) {
        int qntPoliticosInteressados = 0;

        for (String dniPolitico : listaDePresentes) {
            Pessoa politico = this.pessoaService.getPessoaPeloDni(dniPolitico);
            for (String interesseDoProjeto : projeto.getInteresses().split(","))
                if (politico.getInteresses().contains(interesseDoProjeto))
                    qntPoliticosInteressados++;
        }
        return qntPoliticosInteressados;
    }

    /**
     * Esse método conta a quantidade de políticos governistas e retorna.
     *
     * @param listaDePresentes governistas presentes.
     * @return quantidade de políticos governistas.
     */
    private int contaPoliticosGovernistas(String[] listaDePresentes) {
        int qntPoliticosGovernistas = 0;

        for (String dniPolitico : listaDePresentes) {
            Pessoa politico = this.pessoaService.getPessoaPeloDni(dniPolitico);
            if (this.partidoService.containsPartido(politico.getPartido()))
                qntPoliticosGovernistas++;
        }
        return qntPoliticosGovernistas;
    }

    /**
     * Esse método avalia o resultado da votação e avança ou encerra tramitação.
     *
     * @param proposta  projeto.
     * @param resultado resultado da votação.
     */
    private void avaliaResultado(PropostaLegislativa proposta, boolean resultado) {
        Pessoa autorDaProposta = pessoaService.getPessoaPeloDni(proposta.getAutor());

        proposta.avaliaResultado(resultado, autorDaProposta);
    }

    /**
     * Esse método vota o projeto no plenário e retorna se foi aprovado ou não.
     *
     * @param codigo           código do projeto.
     * @param statusGovernista status do projeto.
     * @param presentes        presentes na votação.
     * @return true se for aprovado.
     */
    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao votar proposta: codigo nao existe");

        PropostaLegislativa proposta = this.propostas.get(codigo);

        if (proposta.getSituacaoAtual().equals(SituacaoVotacao.REJEITADO.toString()) || proposta.getSituacaoAtual().equals(SituacaoVotacao.APROVADO.toString()))
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");

        this.verificaQuorumMinimo(presentes, proposta);

        if (!(proposta.getLocalDeVotacao().equals("Plenario - 1o turno")) && !((proposta.getLocalDeVotacao().equals("Plenario - 2o turno"))) && !((proposta.getLocalDeVotacao().equals("plenario"))))
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");

        StatusGovernista status = StatusGovernista.valueOf(statusGovernista);

        boolean resultado = votarPlenario(status, proposta, presentes);

        avaliaResultado(proposta, resultado);

        return resultado;
    }

    /**
     * Esse método exibe toda a tramitação de um projeto, mostrando os locais de votação e seus respectivos resultados.
     *
     * @param codigo o código do projeto que se deseja exibir a tramitação.
     */
    public String exibirTramitacao(String codigo) {
        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao exibir tramitacao: projeto inexistente");

        PropostaLegislativa proposta = this.propostas.get(codigo);

        return proposta.exibirTramitacao();
    }
}