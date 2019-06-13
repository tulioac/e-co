package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validador {

	/**
	 * Esse método válida uma string testando se é nula ou vazia.
	 * 
	 * @param dado     valor a ser verificado.
	 * @param mensagem mensagem de exceção.
	 * @throws NullPointerException     string nula.
	 * @throws IllegalArgumentException string vazia.
	 */
	public void validaString(String dado, String mensagem) {
		if (dado == null)
			throw new NullPointerException(mensagem);

		if (dado.trim().equals(""))
			throw new IllegalArgumentException(mensagem);
	}

	/**
	 * Esse método válida uma string testando se é nula.
	 * 
	 * @param dado     valor a ser verificado.
	 * @param mensagem mensagem de exceção.
	 * @throws NullPointerException     string nula.
	 * @throws IllegalArgumentException string vazia.
	 */
	public void validaNull(Object dado, String mensagem) {
		if (dado == null)
			throw new NullPointerException(mensagem);
	}

	/**
	 * Esse método válida se o documento nacional de identificação da pessoa está no
	 * formato correto.
	 * 
	 * @param dni      documento de identificação de pessoa.
	 * @param mensagem mensagem de exceção.
	 * @throws IllegalArgumentException dni com formato inválido.
	 */
	public void validaDni(String dni, String mensagem) {
		String regraDni = "[0-9]{9}-[0-9]{1}";

		if (!(dni.matches(regraDni)))
			throw new IllegalArgumentException(mensagem);
	}

	/**
	 * Esse método válida uma String no formato ddMMyyyy e confere se ela é uma data
	 * válida e que nao está no futuro.
	 * 
	 * @param data         a data a ser válidada.
	 * @param erroInvalida a mensagem de erro caso a data nao esteja no formato
	 *                     válido ou seja uma data inexistente.
	 * @param erroFutura   a mensagem de erro caso a data estejá no futuro.
	 * @throws IllegalArgumentException caso a data seja inválida ou futura.
	 */
	public Date validaData(String data, String erroInvalida, String erroFutura) {
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

	public void validaAno(String ano, String mensagem) {
		// TODO: Implementar
	}
}