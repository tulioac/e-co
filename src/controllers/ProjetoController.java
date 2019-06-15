package controllers;

import entities.*;
import enums.SituacaoVotacao;
import enums.StatusGovernistas;
import enums.TipoDeProjetos;
import interfaces.PropostaLegislativa;
import services.ComissaoService;
import services.PartidoBaseService;
import services.PessoaService;
import util.Validador;

import java.util.HashMap;
import java.util.Map;

public class ProjetoController {

    private PessoaService pessoaService;
    private ComissaoService comissaoService;
    private PartidoBaseService partidoService;
    private Map<String, PropostaLegislativa> propostas;

    public ProjetoController(PessoaService pessoaService, ComissaoService comissaoService, PartidoBaseService partidoService) {
        this.pessoaService = pessoaService;
        this.comissaoService = comissaoService;
        this.partidoService = partidoService;
        this.propostas = new HashMap<>();
    }

    private int contaProjetoEmAno(TipoDeProjetos tipoProjeto, int ano) {
        int qntProjetosNoAno = 0;

        for (PropostaLegislativa proposta : this.propostas.values())
            if (proposta.getTipoDoProjeto().equals(tipoProjeto) && proposta.getAno() == ano)
                qntProjetosNoAno++;

        return qntProjetosNoAno;
    }

    private String criaCodigo(TipoDeProjetos tipoProjeto, int ano) {
        int qntProjetosNoAno = contaProjetoEmAno(tipoProjeto, ano);

        qntProjetosNoAno++;

        StringBuilder codigo = new StringBuilder(tipoProjeto.toString() + " " + qntProjetosNoAno + "/" + ano);
        return codigo.toString();
    }

    private void verificaDni(String dni) {
        if (!(this.pessoaService.ehPessoaCadastrada(dni)))
            throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");

        if (!(this.pessoaService.ehDeputado(dni)))
            throw new IllegalArgumentException("Erro ao cadastrar projeto: pessoa nao eh deputado");
    }

    public String cadastraPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
        this.verificaDni(dni);
        v.validaAno(ano);
        v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        v.validaNull(conclusivo, "Erro ao cadastrar projeto: conclusivo nao pode ser nula");

        String codigo = criaCodigo(TipoDeProjetos.PL, ano);
        this.propostas.put(codigo, new PL(codigo, dni, ano, ementa, interesses, url, conclusivo));

        return codigo;
    }

    public String cadastraPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
        this.verificaDni(dni);
        v.validaAno(ano);
        v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        v.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");

        String codigo = criaCodigo(TipoDeProjetos.PLP, ano);
        this.propostas.put(codigo, new PLP(codigo, dni, ano, ementa, interesses, url, artigos));

        return codigo;
    }

    public String cadastraPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
        this.verificaDni(dni);
        v.validaAno(ano);
        v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        v.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");

        String codigo = criaCodigo(TipoDeProjetos.PEC, ano);
        this.propostas.put(codigo, new PEC(codigo, dni, ano, ementa, interesses, url, artigos));

        return codigo;
    }

    public String exibirProjeto(String codigo) {
        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao exibir projeto: codigo nao cadastrado");

        return this.propostas.get(codigo).toString();
    }

    private void avaliaResultado(String proximoLocal, PropostaLegislativa proposta, boolean resultado) {
        if (proposta.toString().contains("Conclusiva") && resultado == false)
            proposta.encerraVotacao();

        if (proximoLocal.equals("-")) {
            if (resultado) {
                proposta.aprovaVotacao();
                String dniAutor = proposta.getAutor();

                pessoaService.getPessoaPeloDni(dniAutor).aumentaLeis();
            } else
                proposta.encerraVotacao();
        }

        if (resultado)
            proposta.alteraSituacaoDoLocalAnterior(SituacaoVotacao.APROVADO);
        else
            proposta.alteraSituacaoDoLocalAnterior(SituacaoVotacao.REJEITADA);
    }

    private int contaPoliticosInteressados(Comissao comissao, PropostaLegislativa projeto) {
        int politicosInteressados = 0;

        for (Pessoa deputado : comissao.getIntegrantes())
            for (String interesses : deputado.getInteresses().split(","))
                if (projeto.getInteresses().contains(interesses)) {
                    politicosInteressados++;
                    break;
                }

        return politicosInteressados;
    }

    private int contaPoliticosGovernistas(Comissao comissao) {
        int qntPoliticosGovernistas = 0;

        for (Pessoa deputado : comissao.getIntegrantes())
            if (this.partidoService.containsPartido(deputado.getPartido()))
                qntPoliticosGovernistas++;

        return qntPoliticosGovernistas;
    }

    private boolean votaComissao(StatusGovernistas status, Comissao comissao, PropostaLegislativa projeto) {
        boolean resultado = false;

        int qntDePoliticosDaComissao = comissao.getIntegrantes().size();

        if (status == StatusGovernistas.LIVRE) {
            int qntPoliticosInteressados = contaPoliticosInteressados(comissao, projeto);

            if (qntPoliticosInteressados >= (qntDePoliticosDaComissao / 2 + 1))
                resultado = true;

        } else {
            int qntPoliticosGovernistas = contaPoliticosGovernistas(comissao);

            if (status == StatusGovernistas.GOVERNISTA) {
                if (qntPoliticosGovernistas >= qntDePoliticosDaComissao / 2 + 1)
                    resultado = true;

            } else // StatusGovernistas.OPOSICAO
                if (qntPoliticosGovernistas < qntDePoliticosDaComissao / 2 + 1)
                    resultado = true;
        }
        return resultado;
    }

    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        Validador v = new Validador();
        v.validaString(proximoLocal, "Erro ao votar proposta: proximo local vazio");
        v.validaStatus(statusGovernista, "Erro ao votar proposta: status invalido");

        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao votar proposta: projeto inexistente");

        PropostaLegislativa proposta = this.propostas.get(codigo);

        if (proposta.getLocalDeVotacao().equals("Plenario - 1o turno"))
            throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");

        if (proposta.getSituacaoAtual().equals(SituacaoVotacao.ARQUIVADO.toString()))
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");

        if (!(this.comissaoService.containsComissao(proposta.getLocalDeVotacao())))
            throw new NullPointerException("Erro ao votar proposta: " + proposta.getLocalDeVotacao() + " nao cadastrada");

        StatusGovernistas status = StatusGovernistas.valueOf(statusGovernista);

        boolean resultado = this.votaComissao(status, this.comissaoService.getComissao(proposta.getLocalDeVotacao()), proposta);

        alteraNovoLocal(proximoLocal, proposta);

        avaliaResultado(proximoLocal, proposta, resultado);

        // TODO: Conferir se ao votar a tramitação já está encerrada
        // TODO: Ao aprovar um projeto, aumentar a quantidade de leis de um deputado
        // TODO: Verificar se o projeto foi encaminhado ao plenário

        return resultado;
    }

    private void alteraNovoLocal(String proximoLocal, PropostaLegislativa proposta) {
        if (proximoLocal.equals("plenario")) {
            proposta.setNovoLocalDeVotacao("Plenario - 1o turno");
        } else {
            proposta.setNovoLocalDeVotacao(proximoLocal);
        }
    }

    private void verificaQuorumMinimo(String presentes, TipoDeProjetos tipoDoProjeto) {
        int qntDeputadosPresentes = presentes.split(",").length;

        int qntTotalDeputado = pessoaService.contaDeputados();

        if (tipoDoProjeto == TipoDeProjetos.PEC) {
            if (qntDeputadosPresentes < 3 * qntTotalDeputado / 5 + 1)
                throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");

        } else if (qntDeputadosPresentes < qntTotalDeputado / 2 + 1)
            throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
    }

    private boolean votacaoPlenario(StatusGovernistas status, PropostaLegislativa proposta, String presentes) {
        TipoDeProjetos tipoDaProposta = proposta.getTipoDoProjeto();
        String[] listaDePresentes = presentes.split(",");

        boolean resultado = false;

        if (tipoDaProposta == TipoDeProjetos.PL) {
            resultado = votaMaioriaSimples(status, proposta, listaDePresentes);
        } else if (tipoDaProposta == TipoDeProjetos.PLP) {
            resultado = votaMaioriaAbsoluta(status, proposta, listaDePresentes);
        } else {  // TipoDeProjetos.PEC
            resultado = votaMaioriaQualificada(status, proposta, listaDePresentes);
        }

        return resultado;
    }

    private boolean votaMaioriaSimples(StatusGovernistas status, PropostaLegislativa proposta, String[] listaDePresentes) {
        boolean resultado = false;

        int qntPoliticosGovernistas = contaPoliticosGovernistas(listaDePresentes);

        if (status == StatusGovernistas.GOVERNISTA) {
            if (qntPoliticosGovernistas >= listaDePresentes.length / 2 + 1)
                resultado = true;
        } else { // StatusGovernistas.OPOSICAO
            if (listaDePresentes.length - qntPoliticosGovernistas >= listaDePresentes.length / 2 + 1)
                resultado = true;
        }

        return resultado;
    }

    private boolean votaMaioriaAbsoluta(StatusGovernistas status, PropostaLegislativa proposta, String[] listaDePresentes) {
        boolean resultado = false;

        int qntPoliticosPresentes = listaDePresentes.length;

        if (status == StatusGovernistas.LIVRE) {
            int qntPoliticosInteressados = contaPoliticosInteressados(listaDePresentes, proposta);

            if (qntPoliticosInteressados >= qntPoliticosPresentes / 2 + 1)
                resultado = true;
        } else {
            int qntPoliticosGovernistas = contaPoliticosGovernistas(listaDePresentes);

            if (status == StatusGovernistas.GOVERNISTA) {
                if (qntPoliticosGovernistas >= qntPoliticosPresentes / 2 + 1)
                    resultado = true;
            } else { // StatusGovernistas.OPOSICAO
                if (qntPoliticosGovernistas < qntPoliticosPresentes / 2 + 1)
                    resultado = true;
            }
        }

        return resultado;
    }

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

    private boolean votaMaioriaQualificada(StatusGovernistas status, PropostaLegislativa proposta, String[] listaDePresentes) {
        boolean resultado = false;

        int qntPoliticosGovernistas = contaPoliticosGovernistas(listaDePresentes);

        int qntPoliticosPresentes = listaDePresentes.length;

        if (status == StatusGovernistas.GOVERNISTA) {
            if (qntPoliticosGovernistas >= 3 * qntPoliticosPresentes / 5 + 1)
                resultado = true;
        } else {// StatusGovernistas.OPOSICAO
            if (qntPoliticosGovernistas < 3 * qntPoliticosPresentes / 5 + 1)
                resultado = true;
        }

        return resultado;
    }

    private int contaPoliticosGovernistas(String[] listaDePresentes) {
        int qntPoliticosGovernistas = 0;

        for (String dniPolitico : listaDePresentes) {
            Pessoa politico = this.pessoaService.getPessoaPeloDni(dniPolitico);
            if (this.partidoService.containsPartido(politico.getPartido()))
                qntPoliticosGovernistas++;
        }
        return qntPoliticosGovernistas;
    }

    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao votar proposta: codigo nao existe");

        PropostaLegislativa proposta = this.propostas.get(codigo);

        verificaQuorumMinimo(presentes, proposta.getTipoDoProjeto());

        if (proposta.getSituacaoAtual().equals(SituacaoVotacao.ARQUIVADO.toString()) || proposta.getSituacaoAtual().equals(SituacaoVotacao.APROVADO.toString()))
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");

        if (!(proposta.getLocalDeVotacao().equals("Plenario - 1o turno")))
            throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");

        StatusGovernistas status = StatusGovernistas.valueOf(statusGovernista);

        boolean resultado = votacaoPlenario(status, proposta, presentes);

        avaliaResultado(proposta, resultado);

        return resultado;
    }

    private void avaliaResultado(PropostaLegislativa proposta, boolean resultado) {
        TipoDeProjetos tipoDaProposta = proposta.getTipoDoProjeto();

        // PL
        if (resultado) {
            proposta.aprovaVotacao();
            String dniAutor = proposta.getAutor();

            pessoaService.getPessoaPeloDni(dniAutor).aumentaLeis();
        } else {
            proposta.encerraVotacao();
        }

        // TODO: Corrigir
        // PLP e PEC
        if (proposta.getSituacaoAtual().equals("Plenario - 1o turno")) {
            if (resultado) {
                proposta.setNovoLocalDeVotacao("Plenario - 2o turno");
            } else {
                proposta.alteraSituacaoDoLocalAnterior(SituacaoVotacao.REJEITADA);
            }
        } else if (proposta.getSituacaoAtual().equals("Plenario - 2o turno")) {
            if (resultado) {
                proposta.alteraSituacaoDoLocalAnterior(SituacaoVotacao.APROVADO);
                proposta.aprovaVotacao();
                String dniAutor = proposta.getAutor();

                pessoaService.getPessoaPeloDni(dniAutor).aumentaLeis();
            } else {
                proposta.alteraSituacaoDoLocalAnterior(SituacaoVotacao.REJEITADA);
                proposta.encerraVotacao();
            }
        }
    }


    public String exibirTramitacao(String codigo) {
        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao exibir projeto: codigo nao cadastrado");

        return "Ainda nao implementado!";
    }
}