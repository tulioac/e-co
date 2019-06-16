package entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PECTest {
	private PEC pec;
	
	@BeforeEach
	void setUp() {
		this.pec = new PEC("10/2013","111111111-7", 2013, "Obriga o ensino de programacao nas escolas", "educacao, ciencia e tecnologia", "https://example.net/programacao%40escolas", "22, 64");
	}
	
	@Test
	void testaConstrutor() {
		new PEC("1/2005","111111000-1", 2005, "Reduz a distancia entre paradas de transporte publico",
				"transportes", "https://example.net/distancia%22transporte", "36, 70");
		new PEC("1/2019","991111000-1", 2019, "Regulamenta o descarte de lixo reciclavel",
				"mei-ambiente", "https://example.net/lixo%23reciclar", "23, 75");
	}
	
	@Test
	void testaToString() {
		assertEquals("Projeto de Emenda Constitucional - 10/2013 - " +
                "111111111-7 - Obriga o ensino de programacao nas escolas - " +
                "22,  64 - EM VOTACAO (CCJC)",
        this.pec.toString());
	}

}
