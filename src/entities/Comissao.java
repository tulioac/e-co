package entities;

import util.Validador;

import java.util.Set;

/**
 * Essa classe representa uma Comissao Legislativa.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Comissao {

    /**
     * Armazena o tema da comissão.
     */
    private String tema;

    /**
     * Armazena o conjunto de integrantes da comissão.
     */
    private Set<Pessoa> integrantes;

    /**
     * Constroi uma comissão a partir de um tema e de um conjunto de Pessoas.
     *
     * @param tema        tema da comissão
     * @param integrantes conjunto de Pessoas integrantes da comissão
     */
    public Comissao(String tema, Set<Pessoa> integrantes) {
        Validador v = new Validador();
        v.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
        v.validaNull(integrantes, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");

        this.tema = tema;
        this.integrantes = integrantes;
    }

    /**
     * Esse método retorna o hash do objeto calculado a partir do seu tema.
     *
     * @return inteiro que representa hash da Comissao.
     */
    @Override
    public int hashCode() {
        return this.tema.hashCode();
    }

    /**
     * Esse método compara um objeto pessoa com outro objeto qualquer e verifica se
     * são iguais.
     *
     * @return true para temas iguais, false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comissao other = (Comissao) obj;
        if (tema == null) {
            if (other.tema != null)
                return false;
        } else if (!tema.equals(other.tema))
            return false;
        return true;
    }
}