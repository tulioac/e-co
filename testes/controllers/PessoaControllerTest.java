package controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PessoaControllerTest {
	private PessoaController pc1;

	@BeforeEach
	void setUp() throws Exception {
		pc1 = new PessoaController();
	}

	@Test
	void testaCadastroDePessoaComNomeNulo() {
		assertThrows(NullPointerException.class,
				() -> pc1.cadastrarPessoa(null, "111111111-1", "SP", "musica"));
		assertThrows(NullPointerException.class,
				() -> pc1.cadastrarPessoa(null, "111111111-1", "SP", "musica", "A"));
	}
	
	@Test
	void testaCadastroDePessoaComNomeVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("", "111111111-1", "SP", "musica"));
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("   ", "111111111-1", "SP", "musica", "A"));
	}
	
	@Test
	void testaCadastroDePessoaComDniNulo() {
		assertThrows(NullPointerException.class,
				() -> pc1.cadastrarPessoa("Joao", null, "SP", "musica"));
		assertThrows(NullPointerException.class,
				() -> pc1.cadastrarPessoa("Joao", null, "SP", "musica", "A"));
	}
	
	@Test
	void testaCadastroDePessoaComDniVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("Joao", "", "SP", "musica"));
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("Joao", "  ", "SP", "musica", "A"));
	}
	
	@Test
	void testaCadastroDePessoaComDniInvalido() {
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("Joao", "123-1", "SP", "musica"));
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("Joao", "1-1-1-1-1", "SP", "musica", "A"));
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("Joao", "123123123123132", "SP", "musica", "A"));
	}
	
	@Test
	void testaCadastroDePessoaSemPartidoComSucesso() {
		pc1.cadastrarPessoa("Ana", "123456789-1", "RN", "arte,danca");
		assertEquals("Ana - 123456789-1 (RN) - Interesses: arte,danca", pc1.exibirPessoa("123456789-1"));
	}
	
	@Test
	void testaCadastroDePessoaComPartidoComSucesso() {
		pc1.cadastrarPessoa("Ana", "123456789-1", "RN", "arte,danca", "PB");
		assertEquals("Ana - 123456789-1 (RN) - PB - Interesses: arte,danca", pc1.exibirPessoa("123456789-1"));
	}
	
	@Test
	void testaCadastroDePessoaComDniJaExistente() {
		pc1.cadastrarPessoa("Arlequina", "222222222-1", "RN", "vaquejada");
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarPessoa("Joao", "222222222-1", "SP", "musica"));
	}
	
	@Test
	void testaCadastroDePessoaComInteressesVazio() {
		pc1.cadastrarPessoa("Fabio", "987654321-1", "AM", "");
		assertEquals("Fabio - 987654321-1 (AM)", pc1.exibirPessoa("987654321-1"));
	}
	
	@Test
	void testaExibePessoaInexistente() {
		assertThrows(NullPointerException.class,
				() -> pc1.exibirPessoa("101010100-2"));
	}
	
	@Test
	void testaCadastroDeputadoComDniVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarDeputado("", "01062019"));
	}
	
	@Test
	void testaCadastroDeputadoComDniNulo() {
		assertThrows(NullPointerException.class,
				() -> pc1.cadastrarDeputado(null, "01062019"));
	}
	
	@Test
	void testaCadastroDeputadoComDataDeInicioVazia() {
		pc1.cadastrarPessoa("Sabido", "987654321-1", "PA", "", "GG");
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarDeputado("987654321-1", ""));
	}
	
	@Test
	void testaCadastroDeputadoComDataDeInicioNula() {
		pc1.cadastrarPessoa("Sabido", "987654321-1", "PA", "", "GG");
		assertThrows(NullPointerException.class,
				() -> pc1.cadastrarDeputado("987654321-1", null));
	}
	
	@Test
	void testaCadastroDeputadoComPessoaInexistente() {
		assertThrows(NullPointerException.class,
				() -> pc1.cadastrarDeputado("987654321-1", "01062019"));
	}
	
	@Test
	void testaCadastroDeputadoComDataInvalida() {
		pc1.cadastrarPessoa("Sabido", "987654321-1", "PA", "", "GG");
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarDeputado("987654321-1", "0101"));
	}
	
	@Test
	void testaCadastroDeputadoComDataFutura() {
		pc1.cadastrarPessoa("Sabido", "987654321-1", "PA", "", "GG");
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarDeputado("987654321-1", "30072019"));
	}
	
	@Test
	void testaCadastroDeputadoComPessoaSemPartido() {
		pc1.cadastrarPessoa("Sabido", "987654321-1", "PA", "");
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarDeputado("987654321-1", "01062019"));
	}
	
	@Test
	void testaCadastroDeputadoExistente() {
		pc1.cadastrarPessoa("Sabido", "987654321-1", "PA", "", "GG");
		pc1.cadastrarDeputado("987654321-1", "01062019");
		assertThrows(IllegalArgumentException.class,
				() -> pc1.cadastrarDeputado("987654321-1", "01062019"));
	}

}