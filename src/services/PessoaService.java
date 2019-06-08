package services;

import java.util.HashSet;
import java.util.Set;

import controllers.PessoaController;
import entities.Pessoa;

public class PessoaService {

	private PessoaController pessoas;
	
	public PessoaService(PessoaController pessoas){
		this.pessoas = pessoas;
	}
	
	public Set<Pessoa> getPessoas() {
		return new HashSet<Pessoa>(this.pessoas.getPessoas());
	}
	
	public boolean ehPessoaCadastrada(String dni) {
		for(Pessoa pessoa: getPessoas()) {
			if(pessoa.getDni().equals(dni)) {
				return true;
			}
		}
		return false;
	}
	
	public Pessoa getPessoaByDni(String dni) {
		for(Pessoa pessoa: getPessoas()) {
			if(pessoa.getDni().equals(dni)) {
				return pessoa;
			}
		}
		return null;
	}
	
	public boolean ehDeputado(String dni) {
		if(ehPessoaCadastrada(dni)) {
			if("Sem Cargo".equals(getPessoaByDni(dni).getCargoPolitico())) {
				return false;
			}
			if("Deputado".equals(getPessoaByDni(dni).getCargoPolitico())) {
				return true;
			}
			return false;
		}
		return false;
	}
	
}
