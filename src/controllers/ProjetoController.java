package controllers;

import interfaces.PropostaLegislativa;
import util.Projetos;
import util.Validador;

import java.util.HashMap;
import java.util.Map;

public class ProjetoController {

    private Map<String, PropostaLegislativa> propostas;

    public ProjetoController(){
        this.propostas = new HashMap<>();
    }

    private int contaProjetoEmAno(Projetos tipoProjeto, String ano){
        // TODO: Implementar
        return 0;
    }

    private String criaCodigo(Projetos tipoProjeto, String ano){
        // TODO: Implementar
        int qntProjetosNoAno = contaProjetoEmAno(tipoProjeto, ano);
        return "";
    }

    public void cadastraPL(String dni, String ano, String ementa, String interesses, String url, boolean conclusivo) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
        v.validaAno(ano, "Erro ao cadastrar projeto: ano anterior a 1988");
        v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        v.validaNull(conclusivo, "Erro ao cadastrar projeto: conclusivo nao pode ser nula");
    }

    public void cadastraPLP(String dni, String ano, String ementa, String interesses, String url, String artigos) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
        v.validaAno(ano, "Erro ao cadastrar projeto: ano anterior a 1988");
        v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        v.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
    }

    public void cadastraPEC(String dni, String ano, String ementa, String interesses, String url, String artigos) {
        Validador v = new Validador();
        v.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
        v.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
        v.validaAno(ano, "Erro ao cadastrar projeto: ano anterior a 1988");
        v.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
        v.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
        v.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
        v.validaString(artigos, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
    }

    public String exibirProjeto(String codigo) {
        if (!(this.propostas.containsKey(codigo)))
            throw new NullPointerException("Erro ao exibir projeto: codigo nao cadastrado");

        return this.propostas.get(codigo).toString();
    }
}