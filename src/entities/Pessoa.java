package entities;

/**
 * Essa classe representa uma Pessoa
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Pessoa {
	/**
	 * Armazena o nome da pessoa
	 */
	private String nome;
	/**
	 * Armazena o documento de identificacao da pessoa
	 */
	private String dni;
	/**
	 * Armazena o estado da pessoa
	 */
	private String estado;
	/**
	 * Armazena os interesses da pessoa
	 */
	private String interesses;
	/**
	 * Armazena o partido da pessoa
	 */
	private String partido;

	/**
	 * Constroi uma pessoa dado seu nome, documento de 
	 * identificacao, estado, interesses e partido.
	 * @param nome nome da pessoa
	 * @param dni documento de identificacao da pessoa
	 * @param estado estado da pessoa
	 * @param interesses interesses da pessoa
	 * @param partido partido da pessoa
	 */
	public Pessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
		this.partido = partido;
	}
	
	/**
	 * Constroi uma pessoa dado seu nome, documento de 
	 * identificacao, estado e interesses.
	 * @param nome nome da pessoa
	 * @param dni documento de identificacao da pessoa
	 * @param estado estado da pessoa
	 * @param interesses interesses da pessoa
	 */
	public Pessoa(String nome, String dni, String estado, String interesses) {
		this(nome, dni, estado, interesses, null);
	}

	/**
	 * Esse metodo recupera o nome da pessoa
	 * @return nome da pessoa
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Esse metodo recupera o documento de identificacao 
	 * da pessoa.
	 * @return dni da pessoa
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Esse metodo recupera o estado da pessoa.
	 * @return estado da pessoa
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Esse metodo recupera os interesses da pessoa.
	 * @return interesses da pessoa
	 */
	public String getInteresses() {
		return interesses;
	}

	/**
	 * Esse metodo recupera o partido da pessoa.
	 * @return partido da pessoa
	 */
	public String getPartido() {
		return partido;
	}

	/**
	 * Esse metodo recupera o hash do objeto pessoa
	 * baseado no seu documento de identificacao
	 * @return inteiro que representa hash de pessoa
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	/**
	 * Esse metodo compara um objeto pessoa com 
	 * outro objeto qualquer e verifica se sao 
	 * iguais.
	 * @return true se os objetos comparados sao iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
}