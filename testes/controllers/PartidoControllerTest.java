package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartidoControllerTest {

	private PartidoController pc1;
	
	@BeforeEach
	void setUp(){
		this.pc1 = new PartidoController();
	}


	@Test
	void testaCadastrarPartido() {
		this.pc1.cadastrarPartido("PeteDuBê");
		this.pc1.cadastrarPartido("PeSól");
	}
	
	@Test
	void testaCadastrarPartidoInvalido() {
		assertThrows(NullPointerException.class, () -> this.pc1.cadastrarPartido(null));
		assertThrows(IllegalArgumentException.class, () -> this.pc1.cadastrarPartido(""));
	}
	
	@Test
	void testaCadastrarPartidoJaCadastrado() {
		this.pc1.cadastrarPartido("PeteDuBê");
		assertThrows(IllegalArgumentException.class, () -> this.pc1.cadastrarPartido("PeteDuBê"));
	}

	@Test
	void testaExibeBase() {
		this.pc1.cadastrarPartido("Pra frente Brasil");
		this.pc1.cadastrarPartido("Ele Não");
		this.pc1.cadastrarPartido("Lula acima de todos!");
		assertEquals("Ele Não,Lula acima de todos!,Pra frente Brasil", this.pc1.exibeBase());
	}
}