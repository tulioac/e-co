package facade;

public class EcoFacade {

	private PessoaController pessoaController;

	public void cadastraPessoa(String nome, String dni, String estado, String interesses) {
		this.pessoaController.cadastraPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastraPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pessoaController.cadastraPessoa(nome, dni, estado, interesses, partido);
	}
}
