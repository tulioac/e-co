package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ComissaoService;
import services.PartidoBaseService;
import services.PessoaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        this.p.cadastrarPessoa("mirella", "123456789-1", "PB", "aaaaa, bbbbb", "PT");
        this.p.cadastrarDeputado("123456789-1", "01011988");
    }

    @Test
    void testaCadastrarPL() {
        this.p.cadastrarPessoa("Tulio", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");
        this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true);
        assertEquals("Projeto de Lei - PL 1/2013 - 312534654-5 - Ementa PL Conclusiva - Conclusiva - EM VOTACAO (CCJC)", this.pc.exibirProjeto("PL 1/2013"));
    }

    @Test
    void testaCadastrarPLVaziaOuNula() {
        this.p.cadastrarPessoa("Tulio", "312534654-5", "PB", "saude", "POT");
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
        this.p.cadastrarPessoa("Tulio", "312534654-5", "PB", "saude", "POT");

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPL("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", true));
    }

    @Test
    void testaCadastrarPLP() {
        this.p.cadastrarPessoa("Tulio", "312534654-5", "PB", "saude", "POT");
        this.p.cadastrarDeputado("312534654-5", "25061998");
        this.pc.cadastraPLP("312534654-5", 2013, "Ementa PLP", "saude, educacao", "wwww.ementa.com.br", "4,5");
        assertEquals("Projeto de Lei Complementar - PLP 1/2013 - 312534654-5 - Ementa PLP - 4, 5 - EM VOTACAO (CCJC)", this.pc.exibirProjeto("PLP 1/2013"));
    }

    @Test
    void testaCadastrarPLPVaziaOuNula() {
        this.p.cadastrarPessoa("Tulio", "312534654-5", "PB", "saude", "POT");
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
        this.p.cadastrarPessoa("Tulio", "312534654-5", "PB", "saude", "POT");

        assertThrows(IllegalArgumentException.class, () -> this.pc.cadastraPLP("312534654-5", 2013, "Ementa PL Conclusiva", "saude, educacao", "wwww.ementa.com.br", "4,5"));
    }
}