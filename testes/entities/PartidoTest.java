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
	
	void testaConstrutorComNomeNulo() {
		assertThrows(NullPointerException.class,
				() -> new Partido(null));
	}
	
	void testaConstrutorComNomeVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> new Partido(""));
	}
	
	void testaEquals() {
		assertTrue(this.partido.equals(new Partido("TST")), "O equals retornou false para um partido de mesmo nome");
		assertFalse(this.partido.equals(new Partido("ACD")),"O equals retornou true para um partido de nome diferente");
		assertFalse(this.partido.equals("objeto de tipo diferente"), "O equals retornou true para um objeto de tipo diferente");
		assertFalse(this.partido.equals(null), "O equals retornou true para uma comparacao com o valor nulo");
	}

}
