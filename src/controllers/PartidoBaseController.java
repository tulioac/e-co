package controllers;

import comparators.ComparatorOrdemAlfabeticaPartido;
import entities.Partido;
import util.Validador;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Essa classe usa o padrão Controller contendo métodos que operam sobre a
 * classe partido.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PartidoBaseController implements Serializable {

    /**
     * Armazena Id de Serialização de PartidoBaseController
     */
    private static final long serialVersionUID = 2659674081170951377L;
    /**
     * Armazena objetos do tipo Partido e utiliza como chave o nome do partido
     * cadastrado.
     */
    private Map<String, Partido> partidos;

    /**
     * Constrói o controller e inicializa o mapa que armazena Partidos.
     */
    public PartidoBaseController() {
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

        if (this.partidos.containsKey(partido))
            throw new IllegalArgumentException("Erro ao cadastrar partido: partido já cadastrado");

        this.partidos.put(partido, new Partido(partido));
    }

    /**
     * Retorna String contendo todos os partidos em ordem alfabética.
     *
     * @return uma string com todos os partidos ordenados alfabeticamente.
     */
    public String exibeBase() {
        List<Partido> partidos = new ArrayList<>(this.partidos.values());
        Collections.sort(partidos, new ComparatorOrdemAlfabeticaPartido());

        return partidos.stream().map(Partido::getNome).collect(Collectors.joining(","));
    }

    /**
     * Retorna o conjunto de partidos de base cadastrados no sistema
     * @return Set de partidos
     */
    public Set<Partido> getPartidos() {
        return new HashSet<>(this.partidos.values());
    }
}