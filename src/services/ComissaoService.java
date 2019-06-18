package services;

import controllers.ComissaoController;
import entities.Comissao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe de serviço que busca objetos Comissao e retorna informações sobre os
 * mesmos.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class ComissaoService implements Serializable {

    /**
     * Armazena Id de serialização de ComissaoService
     */
    private static final long serialVersionUID = -2013457738366725452L;
    /**
     * Controlador de Comissões, fonte dos objetos conhecidos pelo serviço
     */
    private ComissaoController comissoes;

    /**
     * Constroi um serviço a partir de um controlador de Comissoes
     *
     * @param comissaoController
     */
    public ComissaoService(ComissaoController comissaoController) {
        this.comissoes = comissaoController;
    }

    /**
     * Retorna um conjunto de Comissões já cadastradas no sistema. Caso não haja comissões
     * cadastradas, retornará um conjunto vazio.
     *
     * @return Set de objetos Comissao cadastrados no sistema
     */
    public Set<Comissao> getComissoes() {
        return new HashSet<Comissao>(this.comissoes.getComissoes());
    }

    /**
     * Retorna um booleano sobre a existência da Comissão consultado no sistema.
     *
     * @param comissaoDesejada tema da comissão que se quer consultar a existência no sistema
     * @return true para a existência da Comissão, false caso contrário
     */
    public boolean containsComissao(String comissaoDesejada) {
        for (Comissao comissao : this.getComissoes())
            if (comissao.getTema().equals(comissaoDesejada))
                return true;

        return false;
    }

    /**
     * Retorna o objeto comissão que possui o tema passado como parâmetro. Caso não
     * exista a comissão com o tema passado como parâmetro retornará null.
     *
     * @param comissaoDesejada tema da Comissão que se quer obter
     * @return objeto Comissão com o tema passado como parâmetro, null para quando não existir Comissão com tal tema
     */
    public Comissao getComissao(String comissaoDesejada) {
        for (Comissao comissao : this.getComissoes())
            if (comissao.getTema().equals(comissaoDesejada))
                return comissao;

        return null;
    }
}