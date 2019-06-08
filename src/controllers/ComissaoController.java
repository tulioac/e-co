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
 * Essa classe usa o padrão Controller contendo métodos que operam sobre a classe Comissao.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 *
 */
public class ComissaoController {

	/**
	 * Service responsável por fornecer informações sobre os objetos Pessoa cadastrados no sistema.
	 */
	private PessoaService pessoaService;
	
	/**
	 * Armazena objetos Comissao assumindo como chave seu tema.
	 */
	private Map<String, Comissao> comissoes;
	
	/**
	 * Constroi um Controlador de Comissao a partir de um objeto de PessoaService.
	 * 
	 * @param pessoaService servico responsavel por fornecer informacoes sobre Pessoa
	 */
	public ComissaoController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
		this.comissoes = new HashMap<>();	
	}
	
	/**
	 * Cadastra uma comissão a partir de um tema e do conjunto de políticos que irão fazer parte
	 * dela.
	 * 
	 * @param tema tema da comissao, será a chave de acesso a comissao
	 * @param politicos String contendo os DNIs dos políticos separados por vírgula
	 * @throws NullPointerException caso algum parâmetro seja nulo
	 * @throws IllegalArgumentException caso seja passado algum parâmetro seja vazio
	 * @throws IllegalArgumentException caso algum DNI seja inválido
	 * @throws IllegalArgumentException caso o tema já exista
	 */
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
