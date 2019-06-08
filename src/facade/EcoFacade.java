package facade;

import controllers.ComissaoController;
import controllers.PartidoController;
import controllers.PessoaController;
import easyaccept.EasyAccept;
import services.PessoaService;

/**
 * Essa classe usa o padrão Facade contendo métodos de acesso ao E-Camara
 * Organizada, provendo uma interface mais simples de acesso ao subsistema.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class EcoFacade {
	/**
	 * Armazena uma instância da classe controladora de pessoa.
	 */
	private PessoaController pessoaController;
	private PartidoController partidoController;
	private ComissaoController comissaoController;

	/**
	 * Constrói a classe EcoFacade e inicializa uma instância da classe
	 * PessoaController para operar sobre pessoa.
	 */
	public EcoFacade() {
		this.pessoaController = new PessoaController();
		this.partidoController = new PartidoController();
		this.comissaoController = new ComissaoController(new PessoaService(pessoaController));
	}

	public void limparSistema() {
		// Persistência de dados
	}

	public void salvarSistema() {
		// Persistência de dados
	}

	public void carregarSistema() {
		// Persistência de dados
	}

	/**
	 * Cadastra uma pessoa com nome, documento nacional de identificação, estado e
	 * interesses.
	 * 
	 * @param nome       nome da pessoa.
	 * @param dni        documento de identificação da pessoa.
	 * @param estado     estado da pessoa.
	 * @param interesses interesses da pessoa.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses);
	}

	/**
	 * Cadastra uma pessoa com nome, documento nacional de identificação, estado,
	 * interesses e partido.
	 * 
	 * @param nome       nome da pessoa.
	 * @param dni        documento de identificação da pessoa .
	 * @param estado     estado da pessoa.
	 * @param interesses interesses da pessoa.
	 * @param partido    partido da pessoa.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}

	/**
	 * Cadastra uma pessoa como deputado acessando-a pelo dni.
	 * 
	 * @param dni          documento de identificação da pessoa.
	 * @param dataDeInicio a data de início do cargo da pessoa.
	 */
	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.pessoaController.cadastrarDeputado(dni, dataDeInicio);
	}

	/**
	 * Exibe a descrição de uma pessoa em forma de String através do seu dni. Caso a
	 * pessoa possua cargo político, deve haver uma indicação de seu caráter
	 * político, além do partido ao qual pertence e a quantidade de leis aprovadas.
	 * Os interesses somente são exibidos se esse atributo não for vazio para a
	 * pessoa em exibição.
	 * 
	 * @param dni documento de identificação da pessoa.
	 * @return Nome - Dni - Estado - Interesses - Partido.
	 */
	public String exibirPessoa(String dni) {
		return this.pessoaController.exibirPessoa(dni);
	}

	/**
	 * Cadastra um partido a partir do seu nome.
	 * 
	 * @param partido nome do partido a ser cadastrado.
	 */
	public void cadastrarPartido(String partido) {
		this.partidoController.cadastrarPartido(partido);
	}

	/**
	 * Exibe, em ordem alfabética A-Z, os partidos cadastrados na base.
	 * 
	 * @return String contendo o nome dos partidos cadastrados em ordem alfabética.
	 */
	public String exibirBase() {
		return this.partidoController.exibeBase();
	}
	
	public void cadastrarComissao(String tema, String politicos) {
		this.comissaoController.cadastrarComissao(tema, politicos);
	}

	/**
	 * Método de testes do EasyAccept.
	 * 
	 * @param args argumentos para execução do EasyAccept.
	 */
	public static void main(String[] args) {

		args = new String[] { "facade.EcoFacade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt",
				"acceptance_tests/use_case_3.txt", "acceptance_tests/use_case_4.txt", "acceptance_tests/use_case_5.txt" };
		EasyAccept.main(args);
	}
}