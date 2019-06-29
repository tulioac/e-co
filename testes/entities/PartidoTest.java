package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidoTest {

    private Partido partido;

    @BeforeEach
    void setUp() throws Exception {
        this.partido = new Partido("TST");
    }

    @Test
    void testaConstrutorValido() {
        new Partido("ABC");
        new Partido("CDF");
    }

    @Test
    void testaConstrutorComNomeNulo() {
        assertThrows(NullPointerException.class,
                () -> new Partido(null));
    }

    @Test
    void testaEquals() {
        assertEquals(partido, new Partido("TST"));
        assertNotEquals(partido, new Partido("ACD"));
        assertNotEquals("objeto de tipo diferente", partido);
        assertNotEquals(null, partido);
    }

}