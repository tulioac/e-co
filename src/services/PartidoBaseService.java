package services;

import controllers.PartidoBaseController;
import entities.Partido;

import java.util.HashSet;
import java.util.Set;

public class PartidoBaseService {

    private PartidoBaseController partidos;

    public PartidoBaseService(PartidoBaseController partidos){
        this.partidos = partidos;
    }

    public Set<Partido> getPartidos(){
        return new HashSet<Partido>(this.partidos.getPartidos());
    }

    public boolean containsPartido(String partidoDesejado){
        for (Partido partido : getPartidos())
            if (partido.getNome().equals(partidoDesejado))
                return true;

        return false;
    }
}
