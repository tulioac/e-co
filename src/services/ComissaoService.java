package services;

import controllers.ComissaoController;
import entities.Comissao;

import java.util.HashSet;
import java.util.Set;

public class ComissaoService {

    private ComissaoController comissoes;

    public ComissaoService(ComissaoController comissaoController){
        this.comissoes = comissaoController;
    }

    public Set<Comissao> getComissoes(){
        return new HashSet<Comissao>(this.comissoes.getComissoes());
    }

    public boolean containsComissao(String comissaoDesejada){
        for (Comissao comissao : this.getComissoes())
            if (comissao.getTema().equals(comissaoDesejada))
                return true;

        return false;
    }

    public Comissao getComissao(String comissaoDesejada) {
        for (Comissao comissao : this.getComissoes())
            if (comissao.getTema().equals(comissaoDesejada))
                return comissao;

        return null;
    }
}