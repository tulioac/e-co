package controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import entities.Comissao;
import entities.Pessoa;
import services.PessoaService;
import util.Validador;

public class ComissaoController {

	private PessoaService pessoaService;
	private Map<String, Comissao> comissoes;
	
	public ComissaoController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
		this.comissoes = new HashMap<>();	
	}
	
	public void cadastrarComissao(String tema, String politicos) {
		Validador v = new Validador();
		v.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		v.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		
		if(this.comissoes.containsKey(tema)) {
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		}
		
		String [] dnis = politicos.split(",");
		for(String dniPolitico: dnis) {
			v.validaDni(dniPolitico, "Erro ao cadastrar comissao: dni invalido");
			if(!pessoaService.ehPessoaCadastrada(dniPolitico)) {
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");
			}
			if(!pessoaService.ehDeputado(dniPolitico)) {
				throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
			}
		}
		
		Set<Pessoa> integrantesComissao = new HashSet<>();
		for(String dniValida: dnis) {
			integrantesComissao.add(this.pessoaService.getPessoaByDni(dniValida));
		}
		this.comissoes.put(tema, new Comissao(tema, integrantesComissao));
	}
}
