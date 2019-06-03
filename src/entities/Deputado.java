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
public class Deputado implements CargoPolitico{
	
	/**
	 * Armazena a quantidade de leis aprovadas.
	 */
	private int leis;
	
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

	public int getLeis() {
		return this.leis;
	}

	public String getDataDeInicio() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		String data = formatado.format(this.dataDeInicio);
		
		return data;
	}
}