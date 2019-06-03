package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import interfaces.CargoPolitico;

/**
 * Essa classe representa um Deputado.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Deputado implements CargoPolitico {

	/**
	 * Armazena a quantidade de leis aprovadas.
	 */
	private int leis;

	/**
	 * Armazena a data de inicio do mandato do deputado.
	 */
	private Date dataDeInicio;

	/**
	 * Constrói um deputado inicializando sua quantidade de leis com 0.
	 */
	public Deputado(Date dataDeInicio) {
		this.leis = 0;
		this.dataDeInicio = dataDeInicio;
	}

	/**
	 * Esse método retorna o nome do cargo político do deputado.
	 */
	@Override
	public String getNomeCargo() {
		return "Deputado";
	}

	/**
	 * Esse método retorna a quantidade de leis elaboradas pelo deputado.
	 */
	@Override
	public int getLeis() {
		return this.leis;
	}

	/**
	 * Esse método retorna a data de ínicio do mandato com o formato: dd/mm/yyyy.
	 */
	@Override
	public String getDataDeInicio() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		String data = formatado.format(this.dataDeInicio);

		return data;
	}
}