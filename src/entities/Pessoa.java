package entities;

import java.util.Date;

import interfaces.CargoPolitico;
import util.Validador;

/**
 * Essa classe representa uma Pessoa.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Pessoa {
	/**
	 * Armazena o nome da pessoa.
	 */
	private String nome;
	/**
	 * Armazena o documento de identificação da pessoa.
	 */
	private String dni;
	/**
	 * Armazena o estado da pessoa.
	 */
	private String estado;
	/**
	 * Armazena os interesses da pessoa.
	 */
	private String interesses;
	/**
	 * Armazena o partido da pessoa.
	 */
	private Partido partido;
	/**
	 * Armazena o cargo politico da pessoa.
	 */
	private CargoPolitico cargoPolitico;
	
	/**
	 * Constrói uma pessoa dado seu nome, documento de identificação, estado,
	 * interesses e partido.
	 * 
	 * @param nome       nome da pessoa.
	 * @param dni        documento de identificação da pessoa.
	 * @param estado     estado da pessoa.
	 * @param interesses interesses da pessoa.
	 * @param partido    partido da pessoa.
	 * @throws IllegalArgumentException dni já existe.
	 * @throws IllegalArgumentException caso algum parâmetro seja vazio.
	 * @throws NullPointerException     caso algum parâmetro seja nulo.
	 */
	public Pessoa(String nome, String dni, String estado, String interesses, String partido) {
		Validador v = new Validador();
		v.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		v.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		v.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		v.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
		
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
		this.partido = new Partido(partido);
	}

	/**
	 * Constrói uma pessoa dado seu nome, documento de identificação, estado e
	 * interesses.
	 * 
	 * @param nome       nome da pessoa.
	 * @param dni        documento de identificação da pessoa.
	 * @param estado     estado da pessoa.
	 * @param interesses interesses da pessoa.
	 * @throws IllegalArgumentException dni já existe.
	 * @throws IllegalArgumentException caso algum parâmetro seja vazio.
	 * @throws NullPointerException     caso algum parâmetro seja nulo.
	 */
	public Pessoa(String nome, String dni, String estado, String interesses) {
		this(nome, dni, estado, interesses, "");
	}

	/**
	 * Esse método recupera o nome da pessoa.
	 * 
	 * @return nome da pessoa.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Esse método recupera o documento de identificação da pessoa.
	 * 
	 * @return dni da pessoa.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Esse método recupera o estado da pessoa.
	 * 
	 * @return estado da pessoa.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Esse método recupera os interesses da pessoa.
	 * 
	 * @return interesses da pessoa.
	 */
	public String getInteresses() {
		return interesses;
	}

	/**
	 * Esse método recupera o partido da pessoa.
	 * 
	 * @return partido da pessoa.
	 */
	public String getPartido() {
		return partido.getNome();
	}

	/**
	 * Esse método recupera o cargo político da pessoa caso ele não seja nulo. Se
	 * for nulo, é retornado a string "Sem Cargo"
	 * 
	 * @return cargo politico da pessoa
	 */
	public String getCargoPolitico() {
		return (this.cargoPolitico != null) ? this.cargoPolitico.getNomeCargo() : "Sem Cargo";
	}

	/**
	 * Esse método recupera o hash do objeto pessoa baseado no seu documento de
	 * identificação.
	 * 
	 * @return inteiro que representa hash de pessoa.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	/**
	 * Esse método compara um objeto pessoa com outro objeto qualquer e verifica se
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
		Pessoa other = (Pessoa) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	/**
	 * Esse método altera o cargo político da pessoa para o novo cargo passado por
	 * parâmetro. As opções de cargos políticos disponíveis são: Deputado.
	 * 
	 * @param novoCargo o novo cargo político da pessoa.
	 * @throws IllegalArgumentException se o cargo for vazio ou não estiver nas
	 *                                  opções disponíveis.
	 * @throws NullPointerException     se o cargo for nulo.
	 */
	public void setCargoPolitico(String novoCargo, Date dataInicialValidada) {
		if (dataInicialValidada == null)
			throw new NullPointerException("Data nula!");
		
		if (novoCargo == null)
			throw new NullPointerException("Cargo nulo!");

		if (novoCargo.trim().equals(""))
			throw new IllegalArgumentException("Cargo vazio!");

		if (novoCargo.equals("Deputado"))
			this.cargoPolitico = new Deputado(dataInicialValidada);
		else
			throw new IllegalArgumentException("Cargo inválido!");
	}

	/**
	 * Esse método retorna uma string contendo informações da pessoa no formato nome
	 * - dni (estado).
	 * 
	 * @return string contendo nome - dni (estado) da pessoa.
	 */
	private String informacoesBasicas() {
		return this.nome + " - " + this.dni + " (" + this.estado + ")";
	}

	/**
	 * Método que exibe a representação em String de uma pessoa a partir dos
	 * atributos que ela possui. Caso o partido e/ou interesses daquela pessoa sejam
	 * vazios, estes não devem ser exibidos. Quando a pessoa é também um político, a
	 * data de início do mandato e a quantidade de leis de sua autoria também devem
	 * ser exibidas.
	 * 
	 * @return Nome - dni (Estado) - Partido - Interesses;
	 * @return POL: Nome - dni (Estado) - Partido - Interesses - Data de início -
	 *         quantidade de leis;
	 */
	public String toString() {
		if (this.getCargoPolitico().equals("Sem Cargo")) {
			if (this.interesses.equals("") && this.getPartido().equals(""))
				return this.informacoesBasicas();

			if (this.partido.getNome().equals(""))
				return this.informacoesBasicas() + " - Interesses: " + this.interesses;

			if (this.interesses.equals(""))
				return this.informacoesBasicas() + " - " + this.getPartido();

			return this.informacoesBasicas() + " - " + this.getPartido() + " - Interesses: " + this.interesses;
		}

		if (this.getCargoPolitico().equals("Deputado")) {
			if (this.interesses.equals(""))
				return "POL: " + this.informacoesBasicas() + " - " + this.getPartido() + " - "
						+ this.cargoPolitico.toString();
			;

			return "POL: " + this.informacoesBasicas() + " - " + this.getPartido() + " - Interesses: " + this.interesses
					+ " - " + this.cargoPolitico.toString();
		}

		return "Algo deu errado!!";
	}
}