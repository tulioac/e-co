package controllers;

import entities.Pessoa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Essa classe usa o padrão Controller contendo métodos que operam sobre a
 * classe pessoa.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PessoaController {
	/**
	 * Armazena um mapa de pessoas em que a chave e o documento de identificação e
	 * aponta para um objeto do tipo Pessoa.
	 */
	private Map<String, Pessoa> pessoas;

	/**
	 * Constrói uma classe controladora de pessoa e inicializa um mapa que armazena
	 * as pessoas.
	 */
	public PessoaController() {
		this.pessoas = new HashMap<>();
	}

	/**
	 * Esse método válida uma string testando se é nula ou vazia.
	 * 
	 * @param atributo atribulo a ser verificado.
	 * @param mensagem mensagem de exceção.
	 * @throws NullPointerException     string nula.
	 * @throws IllegalArgumentException string vazia.
	 */
	private void validaString(String atributo, String mensagem) {
		if (atributo == null)
			throw new NullPointerException(mensagem);

		if (atributo.trim().equals(""))
			throw new IllegalArgumentException(mensagem);
	}

	/**
	 * Esse método válida se o documento nacional de identificação da pessoa está no
	 * formato correto.
	 * 
	 * @param dni      documento de identificação de pessoa.
	 * @param mensagem mensagem de exceção.
	 * @throws IllegalArgumentException dni com formato inválido.
	 */
	private void validaDni(String dni, String mensagem) {
		String regraDni = "[0-9]{9}-[0-9]{1}";

		if (!(dni.matches(regraDni)))
			throw new IllegalArgumentException(mensagem);
	}

	/**
	 * Esse método cadastra uma pessoa que possui nome, documento de identificação,
	 * estado, interesses e partido.
	 * 
	 * @param nome       nome da pessoa.
	 * @param dni        documento de identificação da pessoa.
	 * @param estado     estado da pessoa.
	 * @param interesses interesses da pessoa.
	 * @param partido    partido da pessoa.
	 * @throws IllegalArgumentException dni já existe.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		this.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		this.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		this.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");

		if (this.pessoas.containsKey(dni))
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");

		this.pessoas.put(dni, new Pessoa(nome, dni, estado, interesses, partido));
	}

	/**
	 * Esse método cadastra uma pessoa que possui nome, documento de identificação,
	 * estado e interesses.
	 * 
	 * @param nome       nome da pessoa.
	 * @param dni        documento de identificação da pessoa.
	 * @param estado     estado da pessoa.
	 * @param interesses interesses da pessoa.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.cadastrarPessoa(nome, dni, estado, interesses, "");
	}

	/**
	 * Esse método válida uma String no formato ddMMyyyy e confere se ela é uma data
	 * válida e que nao está no futuro.
	 * 
	 * @param data         a data a ser válidada.
	 * @param erroInválida a mensagem de erro caso a data nao esteja no formato
	 *                     válido ou seja uma data inexistente.
	 * @param erroFutura   a mensagem de erro caso a data estejá no futuro.
	 * @throws IllegalArgumentException caso a data seja inválida ou futura.
	 */
	private Date validaData(String data, String erroInvalida, String erroFutura) {
		DateFormat formato = new SimpleDateFormat("ddMMyyyy");
		formato.setLenient(false);
		Date dataFormatada;

		try {
			dataFormatada = formato.parse(data);
		} catch (IllegalArgumentException | ParseException erro) {
			throw new IllegalArgumentException(erroInvalida);
		}
		
		if (dataFormatada.after(new Date()))
			throw new IllegalArgumentException(erroFutura);

		return dataFormatada;
	}

	/**
	 * Esse método cadastra um deputado com sua data de início, alterando o cargo
	 * político de uma pessoa para deputado.
	 * 
	 * @param dni          o dni da pessoa que se deseja cadastrar como deputado.
	 * @param dataDeInicio a data de início do cargo como deputado.
	 * @throws NullPointerException     caso algum parâmetro seja nulo
	 * @throws IllegalArgumentException caso algum parâmetro seja vazio ou de
	 *                                  formato inválido.
	 */
	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		this.validaDni(dni, "Erro ao cadastrar deputado: dni invalido");

		if (!(this.pessoas.containsKey(dni)))
			throw new NullPointerException("Erro ao cadastrar deputado: pessoa nao encontrada");

		this.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		Date dataInicialValidada = this.validaData(dataDeInicio, "Erro ao cadastrar deputado: data invalida",
				"Erro ao cadastrar deputado: data futura");

		if (this.pessoas.get(dni).getPartido().equals(""))
			throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");

		if (this.pessoas.get(dni).getCargoPolitico().equals("Deputado"))
			throw new IllegalArgumentException("Erro ao cadastrar deputado: deputado ja cadastrado");

		this.pessoas.get(dni).setCargoPolitico("Deputado", dataInicialValidada);
	}

	public String exibirPessoa(String dni) {
		this.validaString(dni, "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		this.validaDni(dni, "Erro ao exibir pessoa: dni invalido");

		if (!(this.pessoas.containsKey(dni)))
			throw new NullPointerException("Erro ao exibir pessoa: pessoa nao encontrada");

		return this.pessoas.get(dni).toString();
	}
}