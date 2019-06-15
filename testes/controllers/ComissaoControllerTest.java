package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.PessoaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ComissaoControllerTest {
    private ComissaoController cc;
    private PessoaService ps;
    private PessoaController pc;

    @BeforeEach
    void setUp() {
        pc = new PessoaController();
        ps = new PessoaService(pc);
        cc = new ComissaoController(ps);
    }

    @Test
    void testaCadastrarComissaoComTemaVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> cc.cadastrarComissao("", "123456789-0"));
    }

    @Test
    void testaCadastrarComissaoComTemaNulo() {
        assertThrows(NullPointerException.class,
                () -> cc.cadastrarComissao(null, "123456789-0"));
    }

    @Test
    void testaCadastrarComissaoComPoliticosVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> cc.cadastrarComissao("Chimichanga", ""));
    }

    @Test
    void testaCadastrarComissaoComPoliticosNulo() {
        assertThrows(NullPointerException.class,
                () -> cc.cadastrarComissao("ChibataNaTrancosa", null));
    }

    @Test
    void testaCadastrarComissaoComTemaExistente() {
        pc.cadastrarPessoa("Dr. Hans Chucrutes", "123456789-0", "PB", "pica-pau", "PP");
        pc.cadastrarDeputado("123456789-0", "15062019");
        cc.cadastrarComissao("ChibataNaTrancosa", "123456789-0");

        assertThrows(IllegalArgumentException.class,
                () -> cc.cadastrarComissao("ChibataNaTrancosa", "222222222-2"));
    }

    @Test
    void testaCadastrarComissaoComDniPessoaInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> cc.cadastrarComissao("CheiradaNaFarofa", "1a2b3c4d"));
    }

    @Test
    void testaCadastrarComissaoComPessoaInexistente() {
        assertThrows(NullPointerException.class,
                () -> cc.cadastrarComissao("CheiradaNaFarofa", "000000000-1"));
    }

    @Test
    void testaCadastrarComissaoComPessoaNaoDeputada() {
        pc.cadastrarPessoa("Baba Ovo", "000000000-1", "Proprio", "Esquerda Caviar", "OVO");
        assertThrows(IllegalArgumentException.class,
                () -> cc.cadastrarComissao("US7NaoEhDeDeus", "000000000-1"));
    }

    @Test
    void testaGetComissoes() {
        pc.cadastrarPessoa("Chico Bala", "111111111-1", "Proprio", "Esquerda Caviar", "U");
        pc.cadastrarPessoa("Ronald Agulha", "222222222-2", "Proprio", "Esquerda Caviar", "U");
        pc.cadastrarPessoa("Franciscleiton", "333333333-3", "Proprio", "Esquerda Caviar", "U");

        pc.cadastrarDeputado("111111111-1", "15062019");
        pc.cadastrarDeputado("222222222-2", "15062019");
        pc.cadastrarDeputado("333333333-3", "15062019");

        cc.cadastrarComissao("Felicidade", "111111111-1");

        assertEquals(1, cc.getComissoes().size());

        cc.cadastrarComissao("Alegria", "222222222-2");

        assertEquals(2, cc.getComissoes().size());

        cc.cadastrarComissao("Finalizando Testes", "333333333-3");

        assertNotEquals(2, cc.getComissoes().size());
        assertEquals(3, cc.getComissoes().size());
    }
}