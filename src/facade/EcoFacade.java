package facade;


import controllers.*;
import easyaccept.EasyAccept;
import services.ComissaoService;
import services.PartidoBaseService;
import services.PessoaService;

/**
 * Essa classe usa o padrão Facade contendo métodos de acesso ao E-Camara
 * Organizada, provendo uma interface mais simples de acesso ao subsistema.
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class EcoFacade {
    /**
     * Armazena uma instância da classe controladora de pessoa.
     */
    private PessoaController pessoaController;
    private PartidoBaseController partidoController;
    private ComissaoController comissaoController;
    private ProjetoController projetoController;
    private PersistenciaController persistenciaController;

    /**
     * Constrói a classe EcoFacade e inicializa uma instância da classe
     * PessoaController para operar sobre pessoa.
     */
    public EcoFacade() {
        this.pessoaController = new PessoaController();
        this.partidoController = new PartidoBaseController();
        this.comissaoController = new ComissaoController(new PessoaService(pessoaController));
        this.projetoController = new ProjetoController(new PessoaService(pessoaController), new ComissaoService(comissaoController), new PartidoBaseService(partidoController));
        this.persistenciaController = new PersistenciaController();
    }

    /**
     * Método de testes do EasyAccept.
     *
     * @param args argumentos para execução do EasyAccept.
     */
    public static void main(String[] args) {
        args = new String[]{"facade.EcoFacade",
//                "acceptance_tests/use_case_1.txt",
//                "acceptance_tests/use_case_2.txt",
//                "acceptance_tests/use_case_3.txt",
//                "acceptance_tests/use_case_4.txt",
//                "acceptance_tests/use_case_5.txt",
//                "acceptance_tests/use_case_6.txt",
                "acceptance_tests/use_case_7.txt",
                "acceptance_tests/use_case_8.txt",
                "acceptance_tests/use_case_9.txt"

        };

        EasyAccept.main(args);
    }


    /**
     * Esse método serve para limpar o arquivo txt no qual é guardado os dados serializados do sistema
     */
    public void limparSistema() {
        this.persistenciaController.limparSistema();
    }

    /**
     * Esse método serve para guardar os dados serializados do sistema em um arquivo txt
     */
    public void salvarSistema() {
        this.persistenciaController.salvarSistema(this.comissaoController, this.partidoController,
                this.pessoaController, this.projetoController);
    }

    /**
     * Esse método carrega os dados serializados do sistema que estão gravados no arquivo txt
     */
    public void carregarSistema() {
        this.persistenciaController.carregarSistema();
    }

    /**
     * Cadastra uma pessoa com nome, documento nacional de identificação, estado e
     * interesses.
     *
     * @param nome       nome da pessoa.
     * @param dni        documento de identificação da pessoa.
     * @param estado     estado da pessoa.
     * @param interesses interesses da pessoa.
     */
    public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
        this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses);
    }

    /**
     * Cadastra uma pessoa com nome, documento nacional de identificação, estado,
     * interesses e partido.
     *
     * @param nome       nome da pessoa.
     * @param dni        documento de identificação da pessoa .
     * @param estado     estado da pessoa.
     * @param interesses interesses da pessoa.
     * @param partido    partido da pessoa.
     */
    public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
        this.pessoaController.cadastrarPessoa(nome, dni, estado, interesses, partido);
    }

    /**
     * Cadastra uma pessoa como deputado acessando-a pelo dni.
     *
     * @param dni          documento de identificação da pessoa.
     * @param dataDeInicio a data de início do cargo da pessoa.
     */
    public void cadastrarDeputado(String dni, String dataDeInicio) {
        this.pessoaController.cadastrarDeputado(dni, dataDeInicio);
    }

    /**
     * Exibe a descrição de uma pessoa em forma de String através do seu dni. Caso a
     * pessoa possua cargo político, deve haver uma indicação de seu caráter
     * político, além do partido ao qual pertence e a quantidade de leis aprovadas.
     * Os interesses somente são exibidos se esse atributo não for vazio para a
     * pessoa em exibição.
     *
     * @param dni documento de identificação da pessoa.
     * @return Nome - Dni - Estado - Interesses - Partido.
     */
    public String exibirPessoa(String dni) {
        return this.pessoaController.exibirPessoa(dni);
    }

    /**
     * Cadastra um partido a partir do seu nome.
     *
     * @param partido nome do partido a ser cadastrado.
     */
    public void cadastrarPartido(String partido) {
        this.partidoController.cadastrarPartido(partido);
    }

    /**
     * Exibe, em ordem alfabética A-Z, os partidos cadastrados na base.
     *
     * @return String contendo o nome dos partidos cadastrados em ordem alfabética.
     */
    public String exibirBase() {
        return this.partidoController.exibeBase();
    }

    /**
     * Cadastra uma comissão a partir de um tema e dos políticos que a integra.
     *
     * @param tema      tema que a comissão irá tratar.
     * @param politicos String contendo os DNIs(separados por vírgula) dos políticos
     *                  que participarão da comissão.
     */
    public void cadastrarComissao(String tema, String politicos) {
        this.comissaoController.cadastrarComissao(tema, politicos);
    }

    /**
     * Retorna uma String contendo o código da PL cadastrada. Cadastra uma Projeto de
     * Lei a partir da DNI do autor, do ano de criação dela, de sua ementa, de seus
     * interesses, de sua URL e se ela é ou não conclusiva. Não aceita valores nulos
     * ou vazios passados como parâmetro. A String retornada é na seguinte formatação:
     * <p>
     * "PL X/YYYY", sendo X o número sequencial da PL considerando o ano em que ela foi
     * cadastrada e YYYY o ano em que ela foi cadastrada.
     *
     * @param dni        String contendo o dni do autor
     * @param ano        ano de criação da PL
     * @param ementa     ementa da PL
     * @param interesses interesses abordados na PL
     * @param url        url da ementa da PL
     * @param conclusivo booleano sobre se a PL é ou não conclusiva
     * @return String contendo o código da PL cadastrada
     */
    public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
        return this.projetoController.cadastraPL(dni, ano, ementa, interesses, url, conclusivo);
    }

    /**
     * Retorna uma String contendo o código da PLP cadastrada. Cadastra uma Projeto de
     * Lei Parlamentar a partir da DNI do autor, do ano de criação dela, de sua ementa, de seus
     * interesses, de sua URL e os artigos que ela interfere. Não aceita valores nulos
     * ou vazios passados como parâmetro. A String retornada é na seguinte formatação:
     * <p>
     * "PLP X/YYYY", sendo X o número sequencial da PLP considerando o ano em que ela foi
     * cadastrada e YYYY o ano em que ela foi cadastrada.
     *
     * @param dni        String contendo o dni do autor
     * @param ano        ano de criação da PLP
     * @param ementa     ementa da PLP
     * @param interesses interesses abordados na PLP
     * @param url        url da ementa da PLP
     * @param artigos    String contendo os artigos que a PLP interfere, separados por ","
     * @return String contendo o código da PLP cadastrada
     */
    public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        return this.projetoController.cadastraPLP(dni, ano, ementa, interesses, url, artigos);
    }

    /**
     * Retorna uma String contendo o código da PEC cadastrada. Cadastra uma Projeto de
     * Lei Parlamentar a partir da DNI do autor, do ano de criação dela, de sua ementa, de seus
     * interesses, de sua URL e os artigos que ela interfere. Não aceita valores nulos
     * ou vazios passados como parâmetro. A String retornada é na seguinte formatação:
     * <p>
     * "PEC X/YYYY", sendo X o número sequencial da PEC considerando o ano em que ela foi
     * cadastrada e YYYY o ano em que ela foi cadastrada.
     *
     * @param dni        String contendo o dni do autor
     * @param ano        ano de criação da PEC
     * @param ementa     ementa da PEC
     * @param interesses interesses abordados na PEC
     * @param url        url da ementa da PEC
     * @param artigos    String contendo os artigos que a PEC interfere, separados por ","
     * @return String contendo o código da PEC cadastrada
     */
    public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigos) {
        return this.projetoController.cadastraPEC(dni, ano, ementa, interesses, url, artigos);
    }

    /**
     * Retorna uma String contendo informações sobre o projeto buscado através do seu código.
     * Cada tipo de projeto possui sua própria descrição, seguindo os seguintes padrões:
     * <p>
     * PL: "POL: nomePessoa - dni (estado) - partido - Interesses: educacao,seguranca publica,saude - dd/MM/yyyy - a Leis"
     *
     * @param codigo String contendo o código do Projeto que se quer exibir
     * @return String contendo as informações do projeto que se quer exibir
     */
    public String exibirProjeto(String codigo) {
        return this.projetoController.exibirProjeto(codigo);
    }

    /**
     * Retorna booleano sobre a aprovação, ou não, de uma votação em uma comissão.
     * Cada tipo de Proposta Legislativa utiliza seu próprio tipo de maioria, podendo
     * ser Simples, Absoluta e Qualificada.
     *
     * @param codigo           String contendo o código da proposta legislativa a ser votada
     * @param statusGovernista String contendo o posicionamento político do projeto
     * @param proximoLocal     String contendo o próximo local em que será votada a proposta, caso aprovada no local atual
     * @return true para uma votação aprovada pela comissão, false caso contrario
     */
    public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
        return this.projetoController.votarComissao(codigo, statusGovernista, proximoLocal);
    }

    /**
     * Retorna booleano sobre a aprovação de uma proposta legislativa no plenário com base nos
     * deputados presentes na sessão.
     *
     * @param codigo           código da proposta legislativa a ser votada
     * @param statusGovernista posicionamento político da proposta legislativa a ser votada
     * @param presentes        String contendo as dnis dos deputados presentes na sessão que terá a votação de tal proposta no plenário
     * @return true para uma proposta aprovada pelo plenário, false caso contrário
     */
    public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
        return this.projetoController.votarPlenario(codigo, statusGovernista, presentes);
    }

    /**
     * Retorna String contendo o status da tramitação de uma proposta legislativa.
     * A String de saída segue o seguinte formato:
     * <p>
     * "tipoProjeto: SITUACAO-I (Local-I), SITUACAO-II (Local-II), SITUACAO-III (Local-III)"
     *
     * @param codigo String contendo o código do pra proposta que se quer mostrar a tramitação
     * @return String contendo informações sobre a tramitação de uma proposta
     */
    public String exibirTramitacao(String codigo) {
        return this.projetoController.exibirTramitacao(codigo);
    }
    
    /**
     * Retorna o código da proposta mais relacionada a pessoa do dni, com base
     * no número de interesses em comum com a proposta. Caso não haja interesses
     * em comum, uma String vazia é retornada. Caso haja mais de uma proposta com
     * o mesmo número de interesses em comum, vale a estratégia de desempate definida
     * pelo usuário no sistema. Os últimos critérios de desempate são: ano de cadastro
     * da proposta e código da proposta, por último, se o ano também for igual.Lança
     * IllegalArgumentException e NullPointerException para entradas inválidas ou nulas,
     * respectivamente.
     * 
     * @param dni dni da pessoa a qual se quer buscar a proposta mais relacionada
     * @return código da proposta mais relacionada, String vazia se não houver nenhuma
     */
    public String pegarPropostaRelacionada(String dni) {
    	return this.projetoController.getPropostaRelacionada(dni);
    }
    
    /**
     * Redefine a estratégia de desempate da busca por uma proposta mais relacionada, com
     * base no dni da pessoa e na estratégia nova a ser sobreposta a antiga. Essa estratégia
     * só pode ser dos tipos: "APROVACAO", "CONCLUSAO" e "CONSTITUCIONAL". Lança
     * IllegalArgumentException e NullPointerException para entradas inválidas ou nulas,
     * respectivamente.
     * 
     * @param dni dni da pessoa que se quer modificar a estratégia
     * @param estrategia nova estratégia a ser redefinida
     */
    public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia) {
    	this.projetoController.configurarEstrategiaPropostaRelacionada(dni, estrategia);
    }
}
