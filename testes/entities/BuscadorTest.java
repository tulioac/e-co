package entities;

import controllers.ComissaoController;
import controllers.PartidoBaseController;
import controllers.PessoaController;
import controllers.ProjetoController;
import enums.EstrategiaBusca;
import interfaces.PropostaLegislativa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ComissaoService;
import services.PartidoBaseService;
import services.PessoaService;
import services.ProjetoService;
import util.Buscador;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuscadorTest {

	private Set<PropostaLegislativa> propostas;
	private Pessoa pessoa;
	private String[] interesses;
	private Buscador buscador;

	private ProjetoController pc;
	private PessoaService ps;
	private PessoaController p;
	private ComissaoService cs;
	private ComissaoController cc;
	private PartidoBaseService pas;
	private PartidoBaseController pbc;
	private ProjetoService projs;
	
	@BeforeEach
	void setUp() throws Exception {
		this.pessoa = new Pessoa("Jarbas", "234325254-6", "AC", "inclusao, transportes, carros", "LAT(Lula Acima de Todos)");
		this.propostas = new HashSet<>();
		this.buscador = new Buscador(this.propostas);
		this.interesses = this.pessoa.getInteresses().split(",");
	}

	@BeforeEach
	void configuraParaVotacao() {
		this.p = new PessoaController();
		this.ps = new PessoaService(p);
		this.cc = new ComissaoController(ps);
		this.cs = new ComissaoService(cc);
		this.pbc = new PartidoBaseController();
		this.pas = new PartidoBaseService(pbc);
		this.pc = new ProjetoController(ps, cs, pas);
		this.projs = new ProjetoService(pc);

		this.p.cadastrarPessoa("pedrinho", "111111000-1", "PB", "educacao,seguranca publica,saude", "PartidoGov");
		this.p.cadastrarPessoa("gilberto", "222222222-2", "PE", "educacao,seguranca publica,saude", "PartidoGov");
		this.p.cadastrarPessoa("alfredo", "333333333-3", "PI", "saude,seguranca publica,trabalho", "PartidoGov");
		this.p.cadastrarPessoa("jarbas", "444444444-4", "PI", "saude,seguranca publica,trabalho", "PartidoOpo");
		this.p.cadastrarPessoa("jorete", "555555555-5", "PI", "nutricao", "PartidoOpo");

		this.p.cadastrarDeputado("111111000-1", "24012013");
		this.p.cadastrarDeputado("222222222-2", "24012013");
		this.p.cadastrarDeputado("333333333-3", "24012013");
		this.p.cadastrarDeputado("444444444-4", "24012013");
		this.p.cadastrarDeputado("555555555-5", "24012013");

		this.pbc.cadastrarPartido("PartidoGov");

		this.cc.cadastrarComissao("CCJC", "111111000-1,222222222-2,333333333-3");

	}
	
	void inicializaPropostasUnicaMaisInteressesComuns() {
		//Mais interesses em comum
		PropostaLegislativa pl1 = new PL("PL 1/2006","111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", true);

		PropostaLegislativa plp1 = new PLP("PLP 1/2006", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "inclusao, transportes", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp2 = new PLP("PLP 2/2006", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "inclusao, transportes", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa pec1 = new PEC("PEC 1/2006","111111000-1", 2005, "Reduz a distancia entre paradas de transporte publico",
                "transportes", "https://example.net/distancia%22transporte", "36, 70");
		
		this.propostas.add(pl1);
		this.propostas.add(plp1);
		this.propostas.add(plp2);
		this.propostas.add(pec1);
		this.buscador.setPropostas(this.propostas);
	}
	
	void inicializaPropostasSemInteressesComuns() {
		//Nenhum interesse em comum com nenhuma
		PropostaLegislativa pl1 = new PL("PL 1/2006","111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "nao, tem, comuns", "https://example.net/multas%22acessibilidade", true);

		PropostaLegislativa plp1 = new PLP("PLP 1/2006", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "nada, kpop", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp2 = new PLP("PLP 2/2006", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "musica, boa", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa pec1 = new PEC("PEC 1/2006","111111000-1", 2005, "Reduz a distancia entre paradas de transporte publico",
				"parque do povo", "https://example.net/distancia%22transporte", "36, 70");
		
		this.propostas.add(pl1);
		this.propostas.add(plp1);
		this.propostas.add(plp2);
		this.propostas.add(pec1);
		this.buscador.setPropostas(this.propostas);
	}
	
	void inicializaPropostasVariasMaisInteressantes() {
		//MainteressesUsuariois interesses em comum
		PropostaLegislativa pl1 = new PL("PL 1/2006","111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", true);
		
		//Mais interesses em comum, prevalece essa por CONSTITUCIONAL
		PropostaLegislativa plp1 = new PLP("PLP 1/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp2 = new PLP("PLP 2/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		
		PropostaLegislativa plp3 = new PLP("PLP 3/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
                "inclusao, transportes", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa pec1 = new PEC("PEC 1/2006","111111000-1", 2006, "Reduz a distancia entre paradas de transporte publico",
                "transportes", "https://example.net/distancia%22transporte", "36, 70");
		
		this.propostas.add(pl1);
		this.propostas.add(plp1);
		this.propostas.add(plp2);
		this.propostas.add(plp3);
		this.propostas.add(pec1);
		this.buscador.setPropostas(this.propostas);
	}
	
	void inicializaPropostasVariasMaisInteressantesPLPMaisAntiga() {
		//Mais interesses em comum
		PropostaLegislativa pl1 = new PL("PL 1/2006","111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", true);
		
		//Mais interesses em comum, prevalece essa por ano
		PropostaLegislativa plp01 = new PLP("PLP 1/2005", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		//Mais interesses em comum, prevalece essa por CONSTITUCIONAL
		PropostaLegislativa plp1 = new PLP("PLP 1/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp2 = new PLP("PLP 2/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		
		PropostaLegislativa plp3 = new PLP("PLP 3/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa pec1 = new PEC("PEC 1/2006","111111000-1", 2006, "Reduz a distancia entre paradas de transporte publico",
				"transportes", "https://example.net/distancia%22transporte", "36, 70");
		
		this.propostas.add(pl1);
		this.propostas.add(plp01);
		this.propostas.add(plp1);
		this.propostas.add(plp2);
		this.propostas.add(plp3);
		this.propostas.add(pec1);
		this.buscador.setPropostas(this.propostas);
	}
	
	void inicializaPropostasVariasMaisInteressantesPLPCadastradaPrimeiro() {
		//Mais interesses em comum
		PropostaLegislativa pl1 = new PL("PL 1/2006","111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", true);
		
		//Mais interesses em comum, mesmo ano mais antigo, prevalece no momento de cadastro
		PropostaLegislativa plp001 = new PLP("PLP 1/2005", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		//Mais interesses em comum, prevalece essa por ano
		PropostaLegislativa plp01 = new PLP("PLP 2/2005", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp1 = new PLP("PLP 1/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp2 = new PLP("PLP 2/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		
		PropostaLegislativa plp3 = new PLP("PLP 3/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa pec1 = new PEC("PEC 1/2006","111111000-1", 2006, "Reduz a distancia entre paradas de transporte publico",
				"transportes", "https://example.net/distancia%22transporte", "36, 70");
		
		this.propostas.add(pl1);
		this.propostas.add(plp001);
		this.propostas.add(plp01);
		this.propostas.add(plp1);
		this.propostas.add(plp2);
		this.propostas.add(plp3);
		this.propostas.add(pec1);
		this.buscador.setPropostas(this.propostas);
	}
	
	void inicializaPropostasVariasMaisInteressantesPLMaisPertoDaConclusao() {
		//Mais interesses em comum, mais avançada nas comissões, prevalece por CONCLUSAO

		this.pc.cadastraPL("111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana", "inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", true);

		assertTrue(this.pc.votarComissao("PL 1/2006", "GOVERNISTA", "CGOV"));

		this.propostas = this.projs.getPropostas();

		//Mais interesses em comum, mesmo ano mais antigo, prevalece no momento de cadastro
		PropostaLegislativa plp001 = new PLP("PLP 1/2005", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		//Mais interesses em comum, prevalece essa por ano
		PropostaLegislativa plp01 = new PLP("PLP 2/2005", "111111000-1", 2005, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp1 = new PLP("PLP 1/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa plp2 = new PLP("PLP 2/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes, carros", "https://example.net/multas%22acessibilidade", "36, 70");
		
		
		PropostaLegislativa plp3 = new PLP("PLP 3/2006", "111111000-1", 2006, "Destina 30% das multas de trânsito arrecadadas à melhoria da acessibilidade urbana",
				"inclusao, transportes", "https://example.net/multas%22acessibilidade", "36, 70");
		
		PropostaLegislativa pec1 = new PEC("PEC 1/2006","111111000-1", 2006, "Reduz a distancia entre paradas de transporte publico",
				"transportes", "https://example.net/distancia%22transporte", "36, 70");
		
		this.propostas.add(plp001);
		this.propostas.add(plp01);
		this.propostas.add(plp1);
		this.propostas.add(plp2);
		this.propostas.add(plp3);
		this.propostas.add(pec1);
		this.buscador.setPropostas(this.propostas);
	}
	
	@Test
	void testaPropostaMaisRelacionadaMaisInteressesComuns() {
		inicializaPropostasUnicaMaisInteressesComuns();
		assertEquals("PL 1/2006", this.buscador.buscaMaisRelacionado(this.interesses));
	}
	
	@Test
	void testaSemInteressesComuns() {
		inicializaPropostasSemInteressesComuns();
		assertEquals("", this.buscador.buscaMaisRelacionado(this.interesses));
	}
	
	@Test
	void testaMesmosInteressesComunsDesempateConstitucional() {
		inicializaPropostasVariasMaisInteressantes();
		assertEquals("PLP 1/2006", this.buscador.buscaMaisRelacionado(interesses));
	}
	
	@Test
	void testaMesmosInteressesComunsDesempatePorAno() {
		inicializaPropostasVariasMaisInteressantesPLPMaisAntiga();
		assertEquals("PLP 1/2005", this.buscador.buscaMaisRelacionado(interesses));
	}
	
	@Test
	void testaMesmosInteressesComunsMesmoAnoDesempatePorCadastro() {
		inicializaPropostasVariasMaisInteressantesPLPCadastradaPrimeiro();
		assertEquals("PLP 1/2005", this.buscador.buscaMaisRelacionado(interesses));
	}
	
	@Test
	void testaMesmosInteressesComunsDesempateMaisPertoConclusao(){
		inicializaPropostasVariasMaisInteressantesPLMaisPertoDaConclusao();
		this.buscador.setEstrategiaAtual(EstrategiaBusca.CONCLUSAO);
		assertEquals("PL 1/2006", this.buscador.buscaMaisRelacionado(interesses));
	}
	
	@Test
	void testaMesmosInteressesComunsDesempateMaisPertoAprovacao() {
		inicializaPropostasVariasMaisInteressantesPLMaisPertoDaConclusao();
		this.buscador.setEstrategiaAtual(EstrategiaBusca.APROVACAO);
		assertEquals("PL 1/2006", this.buscador.buscaMaisRelacionado(interesses));
	}
}
