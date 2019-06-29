package entities;

import enums.CargosPoliticos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeputadoTest {
    private Deputado d;
    private String dataAtual;

    @BeforeEach
    void setUp() {
        d = new Deputado(new Date());
        DateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
        this.dataAtual = formatado.format(new Date());
    }

    @Test
    void testaGetNomeCargo() {
        assertEquals(CargosPoliticos.DEPUTADO,
                this.d.getNomeCargo());
    }

    @Test
    void testaGetLeis() {
        assertEquals(0,
                this.d.getLeis());
    }

    @Test
    void getDataDeInicio() {
        assertEquals(this.dataAtual,
                this.d.getDataDeInicio());
    }

    @Test
    void testaToStringDeputado() {
        assertEquals(this.dataAtual + " - 0 Leis",
                this.d.toString());
    }
}