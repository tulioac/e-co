package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comparators.ComparatorOrdemAlfabeticaPartido;
import entities.Partido;
import util.Validador;

/**
 * Essa classe usa o padrão Controller contendo métodos que operam sobre a
 * classe partido.
 * 
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PartidoController {

	/**
	 * Armazena objetos do tipo Partido e utiliza como chave o nome do partido
	 * cadastrado.
	 */
	private Map<String, Partido> partidos;

	/**
	 * Constrói o controller e inicializa o mapa que armazena Partidos.
	 */
	public PartidoController() {
		this.partidos = new HashMap<>();
	}

	/**
	 * Cadastra um partido a partir do seu nome.
	 * 
	 * @param partido nome do partido a ser cadastrado.
	 * @throws NullPointerException     para um nome nulo.
	 * @throws IllegalArgumentException para um nome vazio.
	 */
	public void cadastrarPartido(String partido) {
		Validador v = new Validador();
		v.validaString(partido, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");

		if (this.partidos.containsKey(partido)) {
			throw new IllegalArgumentException("Partido já cadastrado");
		}

		this.partidos.put(partido, new Partido(partido));
	}

	/**
	 * Retorna String contendo todos os partidos em ordem alfabética.
	 * 
	 * @return uma string com todos os partidos ordenados alfabeticamente.
	 */
	public String exibeBase() {
		List<Partido> ordenaPartidos = new ArrayList<>(this.partidos.values());
		Collections.sort(ordenaPartidos, new ComparatorOrdemAlfabeticaPartido());

		String mensagem = "";
		for (Partido partido : ordenaPartidos)
			mensagem += partido.getNome() + ",";

		if (!("".equals(mensagem)))
			mensagem = mensagem.substring(0, mensagem.length() - 1);

		return mensagem;
	}
}