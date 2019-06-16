package entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PLPTest {
	private PLP plp;
	
	@BeforeEach
	void setUp() {
		this.plp = new PLP("10/2019","111111111-1", 2019, "Reduz a tributacao em cima de bicicletas", "meio-ambiente, transportes, esportes", "https://example.net/bicicleta%40tributos", "123, 12");

	}
	
	@Test
	void testaConstrutor() {
		new PLP("1/2005","111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes", "https://example.net/multas%22acessibilidade", "36, 70");
		new PLP("1/2019","991111000-1", 2019, "Proibe a venda de produtos com cafeina a menores",
				"juventude", "https://example.net/cafeina%23menores", "23, 75");
	}

	@Test
	void testaToString() {
		assertEquals("Projeto de Lei Complementar - 10/2019 - " +
                "111111111-1 - Reduz a tributacao em cima de bicicletas - " +
                "123,  12 - EM VOTACAO (CCJC)",
        this.plp.toString());
	}
	
}
