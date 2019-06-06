package entities;

import util.Validador;

/**
 * Essa classe representa um Partido.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Partido {

	/**
	 * String que representa o nome do partido.
	 */
	private String nome;

	/**
	 * Constrói um partido a partir do seu nome.
	 * 
	 * @param nome String com o nome do partido.
	 */
	public Partido(String nome) {
		Validador v = new Validador();
		v.validaString(nome, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		
		this.nome = nome;
	}

	/**
	 * Retorna o nome do partido.
	 * 
	 * @return uma string com o nome do partido.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Esse método recupera o hash do objeto partido baseado no seu nome.
	 * 
	 * @return inteiro que representa hash de partido.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Esse método compara um objeto partido com outro objeto qualquer e verifica se
	 * são iguais.
	 * 
	 * @return true se os objetos comparados são iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}