package facade;

import controllers.PessoaController;
import easyaccept.EasyAccept;

public class EcoFacade {

	private PessoaController pessoaController;
	
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
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public static void main(String[] args) {
		args = new String[] { "facade.EcoFacade", "acceptance_tests/use_case_1.txt"};

		EasyAccept.main(args);
	}
}
