package entities;

import enums.TipoDeProjetos;

/**
 * Essa classe representa um projeto de lei.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PL extends Projeto {
	/**
	 * Armazena se a PL era conclusiva ou nao.
	 */
    private boolean conclusivo;
    
    /**
     * Constrói uma PL a partir dos parametro estabelecidos na classe abstrata projeto.
     * Altera o tipo do projeto para PL e conclusivo para true.
     */
    public PL(String codigo, String dniAutor, int ano, String ementa, String interesses, String endereco, boolean conclusivo) {
        super(codigo, dniAutor, ano, ementa, interesses, endereco);
        this.conclusivo = conclusivo;
        this.tipoDoProjeto = TipoDeProjetos.PL;
    }
    
    /**
     * Retorna uma representaçao em String da PL sobreescrevendo
     * o método que foi criado na classe Projeto exibindo se o a PL foi conclusiva ou nao.
     *
     * @return string no formato Projeto de lei - codigo - dni do autor do projeto - ementa.
     */
    @Override
    public String toString() {
        StringBuilder representacaoDeProjeto = new StringBuilder("Projeto de Lei - " + super.toString() + " - ");

        if (conclusivo)
            representacaoDeProjeto.append("Conclusiva - ");

        representacaoDeProjeto.append(this.exibeSituacaoAtual());

        return representacaoDeProjeto.toString();
    }
}