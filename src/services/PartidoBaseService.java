package services;

import controllers.PartidoBaseController;
import entities.Partido;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de serviço que busca objetos Partido da Base e retorna informações sobre os
 * mesmos.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PartidoBaseService {

    /**
     * Controlador de Partidos da Base, fonte dos objetos conhecidos pelo serviço
     */
    private PartidoBaseController partidos;

    /**
     * Constroi um serviço a partir de um controlador de Partidos da Base
     *
     * @param comissaoController controlador de partidos da base PartidosBaseController
     */
    public PartidoBaseService(PartidoBaseController partidos) {
        this.partidos = partidos;
    }

    /**
     * Retorna um conjunto de partidos da Base. Caso não haja partidos da base governista,
     * retorna um conjunto vazio.
     *
     * @return Set de Partidos pertencentes a Base governista.
     */
    public Set<Partido> getPartidos() {
        return new HashSet<Partido>(this.partidos.getPartidos());
    }

    /**
     * Retorna um booleano sobre um partido consultado ser ou não da Base Governista.
     * Caso não exista tal partido na base governista retornará false.
     *
     * @param partidoDesejado String com o nome do partido que se quer consultar se é da base governista
     * @return true para quando o partido consultado for da base, false caso contrário
     */
    public boolean containsPartido(String partidoDesejado) {
        for (Partido partido : getPartidos())
            if (partido.getNome().equals(partidoDesejado))
                return true;

        return false;
    }
}
