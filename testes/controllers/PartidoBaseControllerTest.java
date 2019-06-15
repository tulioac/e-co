package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PartidoBaseControllerTest {

    private PartidoBaseController pc1;

    @BeforeEach
    void setUp() {
        this.pc1 = new PartidoBaseController();
    }

    @Test
    void testaCadastrarPartidoValido() {
        this.pc1.cadastrarPartido("PeteDuBê");
        this.pc1.cadastrarPartido("PeSól");

        assertEquals("PeSól,PeteDuBê",
                this.pc1.exibeBase());
    }

    @Test
    void testaCadastrarPartidoComNomeNulo() {
        assertThrows(NullPointerException.class,
                () -> this.pc1.cadastrarPartido(null));
    }

    @Test
    void testaCadastrarPartidoComNomeVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> this.pc1.cadastrarPartido(""));
    }

    @Test
    void testaCadastrarPartidoExistente() {
        this.pc1.cadastrarPartido("PeteDuBê");

        assertThrows(IllegalArgumentException.class,
                () -> this.pc1.cadastrarPartido("PeteDuBê"));
    }

    @Test
    void testaExibeBaseComPartidos() {
        this.pc1.cadastrarPartido("Pra frente Brasil");
        this.pc1.cadastrarPartido("Ele Não");
        this.pc1.cadastrarPartido("Lula acima de todos!");

        assertEquals("Ele Não,Lula acima de todos!,Pra frente Brasil",
                this.pc1.exibeBase());
    }

    @Test
    void testaExibeBaseSemPartidos() {
        assertEquals("",
                this.pc1.exibeBase());
    }
}