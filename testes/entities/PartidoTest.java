package entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void testaConstrutorComNomeVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> new Partido(""));
	}

	@Test
	void testaEquals() {
		assertEquals(this.partido, new Partido("TST"));
		assertNotEquals(this.partido, new Partido("ACD"));
		assertNotEquals("objeto de tipo diferente", this.partido);
		assertNotEquals(null, this.partido);
	}

}
