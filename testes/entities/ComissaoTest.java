package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ComissaoTest {
    private Comissao c1;
    private Comissao c2;
    private Comissao c3;
    private Set<String> integrantes1;
    private Set<String> integrantes2;

    @BeforeEach
    void setUp() {
        integrantes1 = new HashSet<>();
        integrantes2 = new HashSet<>();
        c1 = new Comissao("Treloso", integrantes1);
        c2 = new Comissao("Treloso", integrantes2);
        c3 = new Comissao("Barulhao", integrantes2);
    }

    @Test
    void testaConstrutorComissaoComTemaVazio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Comissao("", integrantes1));
    }

    @Test
    void testaConstrutorComissaoComTemaNulo() {
        assertThrows(NullPointerException.class,
                () -> new Comissao(null, integrantes1));
    }

    @Test
    void testaEqualsComissaoIgual() {
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testaEqualsComissaoDiferente() {
        assertNotEquals(c1, c3);
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }

    @Test
    void testaGetTemaComissao() {
        assertNotEquals("", c1.getTema());
        assertEquals("Treloso", c1.getTema());
    }

    @Test
    void testaGetIntegrantes() {
        integrantes1.add("123456789-0");
        assertEquals(1, c1.getIntegrantes().size());
    }
}