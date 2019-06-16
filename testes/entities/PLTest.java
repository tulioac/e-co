package entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PLTest {
	private PL pl;
	
	@BeforeEach
	void setUp() {
		this.pl = new PL("10/2019","111111111-1", 2019, "Reduz a tributacao em cima de bicicletas", "meio-ambiente, transportes, esportes", "https://example.net/bicicleta%40tributos", true);
	}

	@Test
	void testaConstrutor() {
		new PL("1/2005","111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes", "https://example.net/multas%22acessibilidade", true);
		new PL("1/2019","991111000-1", 2019, "Proibe a venda de produtos com cafeina a menores",
				"juventude", "https://example.net/cafeina%23menores", true);
	}
	
	@Test
	void testaToString() {
		assertEquals("Projeto de Lei - 10/2019 - " +
                "111111111-1 - Reduz a tributacao em cima de bicicletas - " +
                "Conclusiva - EM VOTACAO (CCJC)",
        this.pl.toString());
	}
	
}
