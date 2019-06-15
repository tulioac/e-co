package entities;

import interfaces.CargoPolitico;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Essa classe representa um Deputado.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class Deputado implements CargoPolitico {

    /**
     * Armazena a quantidade de leis aprovadas.
     */
    private int leis;

    /**
     * Armazena a data de inicio do mandato do deputado.
     */
    private Date dataDeInicio;

    /**
     * Constrói um deputado inicializando sua quantidade de leis com 0.
     */
    public Deputado(Date dataDeInicio) {
        this.leis = 0;
        this.dataDeInicio = dataDeInicio;
    }

    /**
     * Esse método retorna o nome do cargo político do deputado.
     *
     * @return o nome do cargo.
     */
    @Override
    public String getNomeCargo() {
        return "Deputado";
    }

    /**
     * Esse método retorna a quantidade de leis elaboradas pelo deputado.
     *
     * @return a quantidade de leis.
     */
    @Override
    public int getLeis() {
        return this.leis;
    }

    /**
     * Esse método retorna a data de ínicio do mandato com o formato: dd/mm/yyyy.
     *
     * @return a data formatada.
     */
    @Override
    public String getDataDeInicio() {
        SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatado.format(this.dataDeInicio);

        return data;
    }

    /**
     * Esse método retorna uma string contendo informações do cargo político da
     * pessoa no formato datainicio - n Leis.
     *
     * @return string contendo datainicio e número de leis do cargo político.
     */
    @Override
    public String toString() {
        return this.getDataDeInicio() + " - " + this.getLeis() + " Leis";
    }


    /**
     * Esse método aumenta a quantidade de leis de um deputado em uma unidade.
     */
    @Override
    public void aumentaLeis() {
        this.leis++;
    }
}