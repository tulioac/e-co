package entities;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PessoaTest {

	private Pessoa p1;
	private Date dataInicialValida;
	
	@BeforeEach
	void setUp() throws ParseException {
		this.p1 = new Pessoa("Jarbas", "234325254-6", "AC", "Gatos, peixes, cachorros e gaivotas", "LAT(Lula Acima de Todos)");
		DateFormat formato = new SimpleDateFormat("ddMMyyyy");
		this.dataInicialValida = formato.parse("25021989");
	}								   

	@Test
	void testaConstrutor() {
		Pessoa p2 = new Pessoa("Alberto", "574396356-6", "MA", "Caça de animais domésticos", "BAT(Bozo Acima de Todos)");
		Pessoa p3 = new Pessoa("Marininha", "653245443-6", "PA", "Amazônia, abraçar árvores", "AAT(Amazônia Acima de Todos)");
	}
	
	@Test
	void testaConstrutorInvalido() {
		assertThrows(NullPointerException.class, () -> new Pessoa(null, "653245443-6", "PA", "Pescaria", "GLUB"));
		assertThrows(NullPointerException.class, () -> new Pessoa("Peixonado", null, "PA", "Pescaria", "GLUB"));
		assertThrows(NullPointerException.class, () -> new Pessoa("Peixonado", "653245443-6", null, "Pescaria", "GLUB"));
		assertThrows(NullPointerException.class, () -> new Pessoa("Peixonado", "653245443-6", "PA", null, "GLUB"));
		assertThrows(NullPointerException.class, () -> new Pessoa("Peixonado", "653245443-6", "PA", "Pescaria", null));

		assertThrows(IllegalArgumentException.class, () -> new Pessoa("", "653245443-6", "PA", "Pescaria", "GLUB"));
		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Peixonado", "", "PA", "Pescaria", "GLUB"));
		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Peixonado", "653245443-6", "", "Pescaria", "GLUB"));
	}
	
	@Test
	void testaConstrutorDniInvalido() {
		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Jalbertinho", "343242", "PA", "Pescaria", "GLUB"));
		assertThrows(IllegalArgumentException.class, () -> new Pessoa("Jalbertinho", "65E245AA3-6", "PA", "Pescaria", "GLUB"));
	}
	
	@Test
	void testaSetCargoPolitico() {
		this.p1.setCargoPolitico("Deputado", dataInicialValida);
	}
	
	@Test
	void testaSetCargoPoliticoInvalido() {
		assertThrows(NullPointerException.class, () -> this.p1.setCargoPolitico(null, dataInicialValida));
		assertThrows(NullPointerException.class, () -> this.p1.setCargoPolitico("Deputado", null));		
		assertThrows(IllegalArgumentException.class, () -> this.p1.setCargoPolitico("", dataInicialValida));
		assertThrows(IllegalArgumentException.class, () -> this.p1.setCargoPolitico("Secretário de Estado", dataInicialValida));
	}
	
	@Test
	void testaToString() {
		assertEquals("Jarbas - 234325254-6 (AC) - LAT(Lula Acima de Todos) - Interesses: Gatos, peixes, cachorros e gaivotas", this.p1.toString());
		this.p1.setCargoPolitico("Deputado", dataInicialValida);
		assertEquals("POL: Jarbas - 234325254-6 (AC) - LAT(Lula Acima de Todos) - Interesses: Gatos, peixes, cachorros e gaivotas - 25/02/1989 - 0 Leis", this.p1.toString());
	}
}