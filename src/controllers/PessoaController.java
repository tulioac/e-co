package controllers;

import entities.Pessoa;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Essa classe usa o padrao Controller contendo metodos que operam sobre a
 * classe pessoa.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PessoaController {
	/**
	 * Armazena um mapa de pessoas em que a chave e o documento de identificacao e
	 * aponta para um objeto do tipo Pessoa.
	 */
	private Map<String, Pessoa> pessoas;

	/**
	 * Constroi uma classe controladora de pessoa e inicializa um mapa que armazena
	 * as pessoas.
	 */
	public PessoaController() {
		this.pessoas = new HashMap<>();
	}

	/**
	 * Esse metodo valida uma string testando se e nula ou vazia.
	 * 
	 * @param atributo atribulo a ser verificado
	 * @param mensagem mensagem de excecao
	 * @throws NullPointerException     string nula
	 * @throws IllegalArgumentException string vazia
	 */
	private void validaString(String atributo, String mensagem) {
		if (atributo == null)
			throw new NullPointerException(mensagem);

		if (atributo.trim().equals(""))
			throw new IllegalArgumentException(mensagem);
	}

	/**
	 * Esse metodo valida se o documento nacional de identificacao da pessoa esta no
	 * formato correto.
	 * 
	 * @param dni      documento de identificacao de pessoa
	 * @param mensagem mensagem de excecao
	 * @throws IllegalArgumentException dni com formato invalido
	 */
	private void validaDni(String dni, String mensagem) {
		String regraDni = "[0-9]{9}-[0-9]{1}";

		if (!(dni.matches(regraDni)))
			throw new IllegalArgumentException(mensagem);
	}

	/**
	 * Esse metodo cadastra uma pessoa que possui nome, documento de identificacao,
	 * estado, interesses e partido.
	 * 
	 * @param nome       nome da pessoa
	 * @param dni        documento de identificacao da pessoa
	 * @param estado     estado da pessoa
	 * @param interesses interesses da pessoa
	 * @param partido    partido da pessoa
	 * @throws IllegalArgumentException dni ja existe
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
	 * Esse metodo cadastra uma pessoa que possui nome, documento de identificacao,
	 * estado e interesses.
	 * 
	 * @param nome       nome da pessoa
	 * @param dni        documento de identificacao da pessoa
	 * @param estado     estado da pessoa
	 * @param interesses interesses da pessoa
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.cadastrarPessoa(nome, dni, estado, interesses, "");
	}

	private void validaData(String data, String erroInvalida, String erroFutura) {
		DateFormat formato = new SimpleDateFormat("ddMMyyyy");
		formato.setLenient(false); 
		Date dataFormatada;
		
		try {
			dataFormatada = formato.parse(data);
		} catch (IllegalArgumentException | ParseException erro) {
			throw new IllegalArgumentException(erroInvalida);
		}
		
		if(dataFormatada.after(new Date()))
			throw new IllegalArgumentException(erroFutura);
	}

	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		this.validaDni(dni, "Erro ao cadastrar deputado: dni invalido");
		
		if (!(this.pessoas.containsKey(dni)))
			throw new NullPointerException("Erro ao cadastrar deputado: pessoa nao encontrada");
		
		this.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		this.validaData(dataDeInicio, "Erro ao cadastrar deputado: data invalida", "Erro ao cadastrar deputado: data futura");
			
		if (this.pessoas.get(dni).getPartido().equals(""))
			throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
		
		if (this.pessoas.get(dni).getCargoPolitico().equals("Deputado"))
			throw new IllegalArgumentException("Erro ao cadastrar deputado: deputado ja cadastrado");
		
		this.pessoas.get(dni).setCargoPolicito("Deputado");
	}
}