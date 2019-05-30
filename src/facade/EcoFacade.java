package facade;

import controllers.PessoaController;
import easyaccept.EasyAccept;

/**
 * Essa classe usa o padrao Facade contendo metodos de acesso ao
 * E-Camara Organizada, provendo uma interface mais simples de 
 * acesso ao subsistema. 
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class EcoFacade {
	/**
	 * Armazena uma instancia da classe controladora de pessoa.
	 */
	private PessoaController pessoaController;
	
	/**
	 * Constroi a classe EcoFacade e inicializa uma instancia da 
	 * classe PessoaController para operar sobre pessoa.
	 */
	public EcoFacade() {
		this.pessoaController = new PessoaController();
	}

	public void limparSistema() {
		// Persistencia de dados
	}
	
	public void salvarSistema() {
		// Persistencia de dados
	}
	
	public void carregarSistema() {
		// Persistencia de dados
	}
	
	/**
	 * Cadastra uma pessoa com nome, documento nacional de identificacao, 
	 * estado e interesses.
	 * @param nome nome da pessoa
	 * @param dni documento de identificacao da pessoa
	 * @param estado estado da pessoa
	 * @param interesses interesses da pessoa
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	/**
	 * Cadastra uma pessoa com nome, documento nacional de identificacao,
	 * estado, interesses e partido.
	 * @param nome nome da pessoa
	 * @param dni documento de identificacao da pessoa 
	 * @param estado estado da pessoa
	 * @param interesses interesses da pessoa
	 * @param partido partido da pessoa
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.pessoaController.cadastrarDeputado(dni, dataDeInicio);
	}
	
	public static void main(String[] args) {
		args = new String[] { "facade.EcoFacade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt" };
		
		EasyAccept.main(args);
	}
}
