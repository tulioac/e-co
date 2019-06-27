package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ComissaoService;
import services.PartidoBaseService;
import services.PessoaService;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoControllerTest {
    private ProjetoController pc;
    private PessoaService ps;
    private PessoaController p;
    private ComissaoService cs;
    private ComissaoController cc;
    private PartidoBaseService pas;
    private PartidoBaseController pbc;

    @BeforeEach
    void setUp() throws Exception {
        this.p = new PessoaController();
        this.ps = new PessoaService(p);
        this.cc = new ComissaoController(ps);
        this.cs = new ComissaoService(cc);
        this.pbc = new PartidoBaseController();
        this.pas = new PartidoBaseService(pbc);
        this.pc = new ProjetoController(ps, cs, pas);
    }

    @BeforeEach
    void configuraParaVotacao() {
        this.p.cadastrarPessoa("pedrinho", "111111111-1", "PB", "educacao,seguranca publica,saude", "PartidoGov");
        this.p.cadastrarPessoa("gilberto", "222222222-2", "PE", "educacao,seguranca publica,saude", "PartidoGov");
        this.p.cadastrarPessoa("alfredo", "333333333-3", "PI", "saude,seguranca publica,trabalho", "PartidoGov");
        this.p.cadastrarPessoa("jarbas", "444444444-4", "PI", "saude,seguranca publica,trabalho", "PartidoOpo");
        this.p.cadastrarPessoa("jorete", "555555555-5", "PI", "nutricao", "PartidoOpo");

        this.p.cadastrarDeputado("111111111-1", "24012013");
        this.p.cadastrarDeputado("222222222-2", "24012013");
        this.p.cadastrarDeputado("333333333-3", "24012013");
        this.p.cadastrarDeputado("444444444-4", "24012013");
        this.p.cadastrarDeputado("555555555-5", "24012013");

        this.pbc.cadastrarPartido("PartidoGov");

        this.cc.cadastrarComissao("CCJC", "111111111-1,222222222-2,333333333-3");
        this.cc.cadastrarComissao("CTF", "111111111-1,222222222-2,444444444-4,555555555-5");
        this.cc.cadastrarComissao("CGOV", "111111111-1,333333333-3,444444444-4,555555555-5");

    }

    @Test
    void testaCadastrarPL() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");
        this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true);
        assertEquals("Projeto de Lei - PL 1/2013 - 312534654-5 - Ementa PL Conclusiva - Conclusiva - EM VOTACAO (CCJC)", this.pc.exibirProjeto("PL 1/2013"));
    }

    @Test
    void testaCadastrarPLVaziaOuNula() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");

        assertThrows(NullPointerException.class, () -> this.pc.cadastraPL(null, 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPL("312534654-5", 2013, null, "saude, educacao", "wwww.ementa.com.br", true));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", null, "wwww.ementa.com.br", true));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", null, true));

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPL("", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPL("312534654-5", 1500, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "", "saude, educacao", "wwww.ementa.com.br", true));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "", "wwww.ementa.com.br", true));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "", true));
    }

    @Test
    void testaCadastrarPLSemCadastrarPessoa() {
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true));
    }

    @Test
    void testaCadastrarPLSemPessoaSerDeputado() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true));
    }

    @Test
    void testaCadastrarPLP() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");
        this.pc.cadastraPLP("312534654-5", 2013, "Ementa PLP", "saude, educacao", "wwww.ementa.com.br", "4,5");
        assertEquals("Projeto de Lei Complementar - PLP 1/2013 - 312534654-5 - Ementa PLP - 4, 5 - EM VOTACAO (CCJC)", this.pc.exibirProjeto("PLP 1/2013"));
    }

    @Test
    void testaCadastrarPLPVaziaOuNula() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");

        assertThrows(NullPointerException.class, () -> this.pc.cadastraPLP(null, 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, null, "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", null, "wwww.ementa.com.br", "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", null, "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", null));

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("312534654-5", 1500, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", ""));
    }

    @Test
    void testaCadastrarPLPSemCadastrarPessoa() {
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
    }

    @Test
    void testaCadastrarPLPSemPessoaSerDeputado() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
    }

    @Test
    void testaCadastrarPEC() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");
        this.pc.cadastraPEC("312534654-5", 2013, "Ementa PEC", "saude, educacao", "wwww.ementa.com.br", "4,5");
        assertEquals("Projeto de Emenda Constitucional - PEC 1/2013 - 312534654-5 - Ementa PEC - 4, 5 - EM VOTACAO (CCJC)", this.pc.exibirProjeto("PEC 1/2013"));
    }

    @Test
    void testaCadastrarPECVaziaOuNula() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");

        assertThrows(NullPointerException.class, () -> this.pc.cadastraPEC(null, 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, null, "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", null, "wwww.ementa.com.br", "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", null, "4,5"));
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", null));

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPEC("", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPEC("312534654-5", 1500, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "", "saude, educacao", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", "", "wwww.ementa.com.br", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "", "4,5"));
        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", ""));
    }

    @Test
    void testaCadastrarPECSemCadastrarPessoa() {
        assertThrows(NullPointerException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
    }

    @Test
    void testaCadastrarPECSemPessoaSerDeputado() {
        this.p.cadastrarPessoa("Pedrao", "312534654-5", "PB", "saude", "POT");

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPEC("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
    }

    @Test
    void testaVotarProjetoInexistente() {
        assertThrows(NullPointerException.class, () -> this.pc.votarComissao("PP 1/2018", "OPOSICAO", "CGOV"));
    }

    @Test
    void testaVotarProjetoComStatusInvalido() {
        assertThrows(IllegalArgumentException.class, () -> this.pc.votarComissao("PP 1/2018", "A FAVOR", "CGOV"));
    }

    @Test
    void testaVotarProjetoComLocalVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.pc.votarComissao("PP 1/2018", "A FAVOR", ""));
    }

    @Test
    void testaVotarProjetoNoPlenarioDaComissao() {
        this.pc.cadastraPL("111111111-1", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true);
        assertThrows(IllegalArgumentException.class, () -> this.pc.votarPlenario("PL 1/2013", "OPOSICAO", "111111111-1,333333333-3,555555555-5"));
    }

    @Test
    void testaVotarPLComissaoConclusiva() {

        this.pc.cadastraPL("111111111-1", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true);

        assertTrue(this.pc.votarComissao("PL 1/2013", "GOVERNISTA", "CTF"));
        assertFalse(this.pc.votarComissao("PL 1/2013", "GOVERNISTA", "CGOV"));

        assertThrows(IllegalArgumentException.class, () -> this.pc.votarComissao("PL 1/2013", "OPOSICAO", "-"));
    }

    @Test
    void testaVotarPLComissaoNaoConclusiva() {

        this.pc.cadastraPL("111111111-1", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", false);

        assertTrue(this.pc.votarComissao("PL 1/2013", "GOVERNISTA", "CTF"));
        assertFalse(this.pc.votarComissao("PL 1/2013", "GOVERNISTA", "plenario"));

        assertTrue(this.pc.votarPlenario("PL 1/2013", "OPOSICAO", "111111111-1,444444444-4,555555555-5"));

        assertEquals("POL: pedrinho - 111111111-1 (PB) - PartidoGov - Interesses: educacao,seguranca publica,saude - 24/01/2013 - 1 Leis", this.p.exibirPessoa("111111111-1"));
    }
}