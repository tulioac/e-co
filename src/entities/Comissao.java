package entities;

import util.Validador;

import java.io.Serializable;
import java.util.Set;

/**
 * Essa classe representa uma Comissao Legislativa.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Comissao implements Serializable {

    /**
     * Armazena o tema da comissão.
     */
    private String tema;

    /**
     * Armazena o conjunto de integrantes da comissão.
     */
    private Set<String> integrantes;

    /**
     * Constroi uma comissão a partir de um tema e de um conjunto de Pessoas.
     *  @param tema        tema da comissão
     * @param integrantes conjunto de Pessoas integrantes da comissão
     */
    public Comissao(String tema, Set<String> integrantes) {
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
        } else return tema.equals(other.tema);
        return true;
    }

    /**
     * Retorna o tema da Comissão Legislativa
     * @return string contendo nome da comissão
     */
    public String getTema() {
        return this.tema;
    }

    /**
     * Retorna os integrantes de uma Comissão Legislativa
     * @return Set de integrantes da comissão
     */
    public Set<String> getIntegrantes() {
        return this.integrantes;
    }

}