package controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import services.ComissaoService;
import services.PartidoBaseService;
import services.PessoaService;

class ProjetoControllerTest {
    private ProjetoController pc2;
    private PessoaService ps;
    private PessoaController p;
    private ComissaoService cs;
    private ComissaoController cc;
    private PartidoBaseService pas;
    private PartidoBaseController pbc;

    @BeforeEach
    void setUp() throws Exception {
        p = new PessoaController();
        ps = new PessoaService(p);
        cc = new ComissaoController(ps);
        cs = new ComissaoService(cc);
        pbc = new PartidoBaseController();
        pas = new PartidoBaseService(pbc);
        pc2 = new ProjetoController(ps, cs, pas);
        p.cadastrarPessoa("mirella", "123456789-1", "PB", "aaaaa, bbbbb", "PT");
        p.cadastrarDeputado("123456789-1", "01011988");
    }


    @Test
    void testaCadastraPLDniVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("", 2011, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true));
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL(" ", 2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void testaCadastraPLDniNulo() {
        assertThrows(NullPointerException.class,
                () -> pc2.cadastraPL(null, 2011, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true));
        assertThrows(NullPointerException.class,
                () -> pc2.cadastraPL(null, 2016, "Ementa PL conc", "saude", "http://example.com/semana_saude", true));
    }

    @Test
    void testaCadastraPLDniInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123123123123132", 2011, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true));
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("1-1-1-1-1",  2011, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true));
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123123123123132",  2011, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true));
    }

    @Test
    void testaCadastraPLAno() {
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123456789-1", 1987, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true));
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123456789-1", 2023, "Ementa PL conc", "saude,educacao basica", "http://example.com/semana_saude", true));
    }

    @Test
    void testaCadastraPLEmentaVazia() {
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123456789-1", 1999, "", "saude,educacao basica", "http://example.com/semana_saude", true));
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123456789-1", 1999, " ", "saude,educacao basica", "http://example.com/semana_saude", true));
    }

    @Test
    void testaCadastraPLEmentaNula() {
        assertThrows(NullPointerException.class,
                () -> pc2.cadastraPL("123456789-1", 1999, null, "saude,educacao basica", "http://example.com/semana_saude", true));
        assertThrows(NullPointerException.class,
                () -> pc2.cadastraPL("123456789-1", 1999, null, "saude,educacao basica", "http://example.com/semana_saude", true));
    }

    @Test
    void testaCadastraPLInteressesVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123456789-1", 1999, "Ementa PL conc", "", "http://example.com/semana_saude", true));
        assertThrows(IllegalArgumentException.class,
                () -> pc2.cadastraPL("123456789-1", 2016, "Ementa PL conc", " ", "http://example.com/semana_saude", true));
    }

    @Test
    void testaCadastraPLInteresseNulo() {
        assertThrows(NullPointerException.class,
                () -> pc2.cadastraPL("123456789-1", 1999, "Ementa PL conc", null, "http://example.com/semana_saude", true));
    }
}
