package services;

import controllers.PartidoBaseController;
import entities.Partido;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Essa classe representa uma serviço que funciona como um comunicador para
 * PartidoBaseController.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PartidoBaseService implements Serializable {

    /**
     * Armazena Id de serialização do objeto PartidoBaseService
     */
    private static final long serialVersionUID = 8814742272344710601L;
    /**
     * Controlador de Partidos da Base, fonte dos objetos conhecidos pelo serviço
     */
    private PartidoBaseController partidos;

    /**
     * Constroi um serviço a partir de um controlador de Partidos da Base
     *
     * @param partidos controlador de partidos da base PartidosBaseController
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
    private Set<Partido> getPartidos() {
        return new HashSet<>(this.partidos.getPartidos());
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
