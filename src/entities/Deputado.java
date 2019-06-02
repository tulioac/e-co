package entities;

import interfaces.CargoPolitico;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Essa classe representa um Deputado.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Deputado implements CargoPolitico{
	
	/**
	 * Armazena a quantidade de leis aprovadas.
	 */
	private int leis;
	
	/**
	 * Armazena a data de inicio do mandato do deputado..
	 */
	private String dataDeInicio;
	/**
	 * Constrói um deputado inicializando sua quantidade de leis com 0.
	 */
	public Deputado(String dataDeInicio) {
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
	 * Esse método retorna a data de início do mandato do deputado.
	 */
	@Override
	public String getDataDeInicio(){
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		formato.setLenient(false);
		Date dataFormatada;
		
		try {
			dataFormatada = formato.parse(this.dataDeInicio);
		} catch (IllegalArgumentException | ParseException erro) {
			throw new IllegalArgumentException("Erro ao cadastrar deputado: data invalida");
		}
		return formato.format(dataFormatada);
	}
	
	/**
	 * Esse método retorna a quantidade de leis de autoria do deputado.
	 */
	@Override
	public int getLeis() {
		return this.leis;
	}
}