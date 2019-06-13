package services;

import controllers.PessoaController;
import entities.Pessoa;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe que segue o padrão Service e é responsável por retornar as informações
 * necessárias sobre objetos Pessoa já cadastrados no sistema.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PessoaService {

    /**
     * Armazena um controlador de Pessoas.
     */
    private PessoaController pessoas;

    /**
     * Constroi uma classe Service a partir de um Controlador de Pessoas.
     *
     * @param pessoas controlador de pessoas
     */
    public PessoaService(PessoaController pessoas) {
        this.pessoas = pessoas;
    }

    /**
     * Retorna um Set de objetos Pessoa. Obtém um grupo de todas as Pessoas
     * cadastradas no sistema.
     *
     * @return Set de Pessoa contendo todas as pessoas que foram cadastradas no
     * sistema até o momento
     */
    public Set<Pessoa> getPessoas() {
        return new HashSet<Pessoa>(this.pessoas.getPessoas());
    }

    /**
     * Retorna booleano sobre o fato de uma Pessoa estar ou não cadastrada no
     * sistema.
     *
     * @param dni String com o dni buscado
     * @return true para uma pessoa cadastrada, false caso contrário.
     */
    public boolean ehPessoaCadastrada(String dni) {
        for (Pessoa pessoa : getPessoas()) {
            if (pessoa.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna um objeto Pessoa a partir de uma busca pelo seu dni.
     *
     * @param dni String contendo o dni da Pessoa que se quer obter
     * @return Pessoa dona do dni passado como parâmetro caso exista, null caso
     * contrário
     */
    public Pessoa getPessoaPeloDni(String dni) {
        for (Pessoa pessoa : getPessoas()) {
            if (pessoa.getDni().equals(dni)) {
                return pessoa;
            }
        }
        return null;
    }

    /**
     * Retorna um booleano sobre o fato de uma Pessoa com o dni passado possuir ou
     * não o cargo de deputado.
     *
     * @param dni String contendo o dni da Pessoa que se quer consultada
     * @return true para quando a pessoa com o dni passado possuir o cargo de
     * Deputado, false caso contrário
     */
    public boolean ehDeputado(String dni) {
        if (ehPessoaCadastrada(dni)) {
            if ("Sem Cargo".equals(getPessoaPeloDni(dni).getCargoPolitico()))
                return false;

            if ("Deputado".equals(getPessoaPeloDni(dni).getCargoPolitico()))
                return true;
        }
        return false;
    }
}