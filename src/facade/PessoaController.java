package facade;

import entities.Pessoa;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class PessoaController {
	
	private Map<String, Pessoa> pessoas;

	public PessoaController() {
		this.pessoas = new HashMap<>();
	}

	private void validaString(String atributo, String mensagem) {
		if (atributo == null)
			throw new NullPointerException(mensagem);

		if (atributo.trim().equals(""))
			throw new IllegalArgumentException(mensagem);
	}
	
	private void validaDni(String dni, String mensagem) {
		String regraDni = "[0-9]{9}-[0-9]{1}";

		if (!(dni.matches(regraDni)))
			throw new IllegalArgumentException(mensagem);
	}

	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		this.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		this.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		this.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
		
		if (this.pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		
		this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses, partido));
	}

	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.cadastrarPessoa(nome, dni, estado, interesses, null); 
	}
}