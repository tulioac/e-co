package util;

import enums.StatusGovernistas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Validador {

    /**
     * Esse método válida uma string testando se é nula ou vazia.
     *
     * @param dado     valor a ser verificado.
     * @param mensagem mensagem de exceção.
     * @throws NullPointerException     string nula.
     * @throws IllegalArgumentException string vazia.
     */
    public void validaString(String dado, String mensagem) {
        if (dado == null)
            throw new NullPointerException(mensagem);

        if (dado.trim().equals(""))
            throw new IllegalArgumentException(mensagem);
    }

    /**
     * Esse método valida uma string testando se é nula.
     *
     * @param dado     valor a ser verificado.
     * @param mensagem mensagem de exceção.
     * @throws NullPointerException     string nula.
     * @throws IllegalArgumentException string vazia.
     */
    public void validaNull(Object dado, String mensagem) {
        if (dado == null)
            throw new NullPointerException(mensagem);
    }

    /**
     * Esse método valida se o documento nacional de identificação da pessoa está no
     * formato exigido pelo sistema.
     *
     * @param dni      documento de identificação de pessoa.
     * @param mensagem mensagem de exceção.
     * @throws IllegalArgumentException dni com formato inválido.
     */
    public void validaDni(String dni, String mensagem) {
        String regraDni = "[0-9]{9}-[0-9]{1}";

        if (!(dni.matches(regraDni)))
            throw new IllegalArgumentException(mensagem);
    }

    /**
     * Esse método valida uma String no formato ddMMyyyy. Ele confere se a data passada
     * é uma data válida, contendo todos os caracteres que representam dia, mês e ano,
     * respectivamente, além de garantir que ela nao seja posterior a data atual
     *
     * @param data         a data a ser validada.
     * @param erroInvalida a mensagem de erro caso a data nao esteja no formato
     *                     válido ou seja uma data inexistente.
     * @param erroFutura   a mensagem de erro caso a data esteja no futuro.
     * @throws IllegalArgumentException caso a data seja inválida ou futura.
     */
    public Date validaData(String data, String erroInvalida, String erroFutura) {
        DateFormat formato = new SimpleDateFormat("ddMMyyyy");
        formato.setLenient(false);
        Date dataFormatada;

        try {
            dataFormatada = formato.parse(data);
        } catch (IllegalArgumentException | ParseException erro) {
            throw new IllegalArgumentException(erroInvalida);
        }

        if (dataFormatada.after(new Date()))
            throw new IllegalArgumentException(erroFutura);

        return dataFormatada;
    }

    /**
     * Método valida um inteiro que representa um ano. Duas situaçoes sao analisadas
     * no momento da validaçao: anos anteriores à 1988(ano da constituiçao brasileira) e
     * anos posteriores ao ano vigente.
     *
     * @param ano         ano a ser validado.
     * 
     * @throws IllegalArgumentException caso o ano seja anterior à 1988.
     * @throws IllegalArgumentException caso o ano seja posterior ao ano vigente.
     */
    public void validaAno(int ano) {
        if (ano < 1988)
            throw new IllegalArgumentException("Erro ao cadastrar projeto: ano anterior a 1988");

        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);

        if (ano > anoAtual)
            throw new IllegalArgumentException("Erro ao cadastrar projeto: ano posterior ao ano atual");
    }

    /**
     * Esse método valida uma String verificando se esta é igual à algum dos valores
     * estabelecidos para dentro do enum StatusGovernista.
     *
     * @param statusGovernista         status a ser validado.
     * @param mensagem 				   mensagem de erro a ser lançada em caso de 
     * 								   strings diferentes das estabelecidas no enum.
     * 
     * @throws IllegalArgumentException caso o status nao seja nenhum dos valores estabecidos
     * 									no enum.
     */
    public void validaStatus(String statusGovernista, String mensagem) {
        try{
            StatusGovernistas status = StatusGovernistas.valueOf(statusGovernista);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(mensagem);
        }
    }
}