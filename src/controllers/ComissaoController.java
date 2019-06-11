package controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import entities.Comissao;
import entities.Pessoa;
import services.PessoaService;
import util.Validador;

/**
 * Essa classe usa o padr√£o Controller contendo m√©todos que operam sobre a
 * classe Comissao.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 *
 */
public class ComissaoController {

	/**
	 * Service respons√°vel por fornecer informa√ß√µes sobre os objetos Pessoa
	 * cadastrados no sistema.
	 */
	private PessoaService pessoaService;

	/**
	 * Armazena objetos Comissao assumindo como chave seu tema.
	 */
	private Map<String, Comissao> comissoes;

	/**
	 * Constroi um Controlador de Comissao a partir de um objeto de PessoaService.
	 * 
	 * @param pessoaService servico responsavel por fornecer informacoes sobre
	 *                      Pessoa
	 */
	public ComissaoController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
		this.comissoes = new HashMap<>();
	}

	/**
	 * Esse mÈtodo valida dnis passados como uma String separadas por vÌrgula (CSV)
	 * conferindo se cada dni È de uma pessoa cadastrada que È polÌtica.
	 * 
	 * @param politicos a lista de dnis para validar.
	 * @return a lista de dnis validadas.
	 * @throws NullPointerException se alguma dni n„o for cadastrada.
	 * @throws IllegalArgumentException se alguma dni n„o for de um polÌtico.
	 */
	private String[] validaDnis(String politicos) {
		String[] dnis = politicos.split(",");
		Validador v = new Validador();

		for (String dniPolitico : dnis) {
			v.validaDni(dniPolitico, "Erro ao cadastrar comissao: dni invalido");

			if (!pessoaService.ehPessoaCadastrada(dniPolitico))
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");

			if (!pessoaService.ehDeputado(dniPolitico))
				throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
		}

		return dnis;
	}

	/**
	 * Cadastra uma comiss√£o a partir de um tema e do conjunto de pol√≠ticos que
	 * ir√£o fazer parte dela.
	 * 
	 * @param tema      tema da comissao, ser√° a chave de acesso a comissao
	 * @param politicos String contendo os DNIs dos pol√≠ticos separados por
	 *                  v√≠rgula
	 * @throws NullPointerException     caso algum par√¢metro seja nulo
	 * @throws IllegalArgumentException caso seja passado algum par√¢metro seja
	 *                                  vazio
	 * @throws IllegalArgumentException caso algum DNI seja inv√°lido
	 * @throws IllegalArgumentException caso o tema j√° exista
	 */
	public void cadastrarComissao(String tema, String politicos) {
		Validador v = new Validador();
		v.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		v.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");

		if (this.comissoes.containsKey(tema)) 
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		
		String[] dnisValidadas = validaDnis(politicos);

		Set<Pessoa> integrantesComissao = new HashSet<>();
		for (String dniValida : dnisValidadas) 
			integrantesComissao.add(this.pessoaService.getPessoaByDni(dniValida));
		
		this.comissoes.put(tema, new Comissao(tema, integrantesComissao));
	}
}