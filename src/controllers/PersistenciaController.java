package controllers;

import entities.Comissao;
import entities.Partido;
import entities.Pessoa;
import interfaces.PropostaLegislativa;
import services.ProjetoService;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Essa classe usa o padrão Controller contendo métodos que facilitam a
 * persistência e leitura de dados
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class PersistenciaController {
    /**
     * Armazena uma instância de ProjetoService
     */
    private ProjetoService projetoService;

    /**
     * Constrói um controlador usado para persistir e ler
     * dados. Faz uso de ProjetoService.
     *
     * @param projetoService instância de ProjetoService
     */
    public PersistenciaController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    /**
     * Esse método sobrescreve os arquivos de texto comissoes,
     * pessoas, partidos e propostas do sistema com uma string
     * vazia.
     *
     * @throws IOException erro de escrita em arquivos
     */
    public void limparSistema() throws IOException {
        FileWriter arquivoComissoes = new FileWriter("dados" + File.separator + "comissoes.txt");
        FileWriter arquivoPessoas = new FileWriter("dados" + File.separator + "pessoas.txt");
        FileWriter arquivoPartidos = new FileWriter("dados" + File.separator + "partidos.txt");
        FileWriter arquivoPropostas = new FileWriter("dados" + File.separator + "propostas.txt");

        arquivoComissoes.write("");
        arquivoComissoes.flush();
        arquivoComissoes.close();

        arquivoPessoas.write("");
        arquivoPessoas.flush();
        arquivoPessoas.close();

        arquivoPartidos.write("");
        arquivoPartidos.flush();
        arquivoPartidos.close();

        arquivoPropostas.write("");
        arquivoPropostas.flush();
        arquivoPropostas.close();
    }

    /**
     * Esse método serve para salvar as estruturas
     * que guardam pessoas, comissoes, partidos e
     * propostas em seus respectivos arquivos.
     */
    public void salvarSistema() {
        this.salvarPessoas();
        this.salvarComissoes();
        this.salvarPartidos();
        this.salvarPropostas();
    }

    /**
     * Esse método serve para carregar as estruturas
     * salvas nos arquivos de pessoas, comissoes, partidos
     * e propostas nas estruturas de dados dos seus respectivos
     * controllers.
     */
    public void carregarSistema() {
        this.carregarPessoas(this.recuperarPessoas());
        this.carregarComissoes(this.recuperarComissoes());
        this.carregarPartidos(this.recuperarPartidos());
        this.carregarPropostas(this.recuperarPropostas());
    }

    /**
     * Esse método serve para salvar o Set de comissões no arquivo.
     */
    private void salvarComissoes() {
        HashSet<Comissao> comissoes = new HashSet<>(this.projetoService.getComissaoService().getComissoes());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(
                    new FileOutputStream(new File("dados" + File.separator + "comissoes.txt")));
            objGravador.writeObject(comissoes);
            objGravador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Esse método serve para salvar o Set de pessoas no arquivo.
     */
    private void salvarPessoas() {
        HashSet<Pessoa> pessoas = new HashSet<>(this.projetoService.getPessoaService().getPessoas());
        try {
            ObjectOutputStream objGravador = new ObjectOutputStream(
                    new FileOutputStream(new File("dados" + File.separator + "pessoas.txt")));
            objGravador.writeObject(pessoas);
            objGravador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Esse método serve para salvar o Set de partidos no arquivo.
     */
    private void salvarPartidos() {
        HashSet<Partido> partidos = new HashSet<>(this.projetoService.getPartidoService().getPartidos());
        try {
            ObjectOutputStream objGravador = new ObjectOutputStream(
                    new FileOutputStream(new File("dados" + File.separator + "partidos.txt")));
            objGravador.writeObject(partidos);
            objGravador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Esse método serve para salvar o Set de propostas no arquivo.
     */
    private void salvarPropostas() {
        HashSet<PropostaLegislativa> propostas = new HashSet<>(this.projetoService.getPropostas());
        try {
            ObjectOutputStream objGravador = new ObjectOutputStream(
                    new FileOutputStream(new File("dados" + File.separator + "propostas.txt")));
            objGravador.writeObject(propostas);
            objGravador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Esse método serve para recuperar do arquivo o Set de comissões
     * e retorná-lo.
     *
     * @return Set de comissões
     */
    private HashSet<Comissao> recuperarComissoes() {
        HashSet<Comissao> comissoes = new HashSet<>();
        try {
            File arqComissoes = new File("dados" + File.separator + "comissoes.txt");
            if (arqComissoes.exists()) {
                ObjectInputStream objLeitor = new ObjectInputStream(new FileInputStream(arqComissoes));
                comissoes = (HashSet<Comissao>) objLeitor.readObject();
                objLeitor.close();
            }
        } catch (EOFException eof) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return comissoes;
    }

    /**
     * Esse método serve para recuperar do arquivo o Set de pessoas
     * e retorná-lo.
     *
     * @return Set de pessoas
     */
    private HashSet<Pessoa> recuperarPessoas() {
        HashSet<Pessoa> pessoas = new HashSet<>();
        try {
            File arqPessoas = new File("dados" + File.separator + "pessoas.txt");
            if (arqPessoas.exists()) {
                ObjectInputStream objLeitor = new ObjectInputStream(new FileInputStream(arqPessoas));
                pessoas = (HashSet<Pessoa>) objLeitor.readObject();
                objLeitor.close();
            }
        } catch (EOFException eof) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    /**
     * Esse método serve para recuperar do arquivo o Set de partidos
     * e retorná-lo.
     *
     * @return Set de Partidos
     */
    private HashSet<Partido> recuperarPartidos() {
        HashSet<Partido> partidos = new HashSet<>();
        try {
            File arqPartidos = new File("dados" + File.separator + "partidos.txt");
            if (arqPartidos.exists()) {
                ObjectInputStream objLeitor = new ObjectInputStream(new FileInputStream(arqPartidos));
                partidos = (HashSet<Partido>) objLeitor.readObject();
                objLeitor.close();
            }
        } catch (EOFException eof) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return partidos;
    }

    /**
     * Esse método serve para recuperar do arquivo o Set de propostas
     * e retorná-lo.
     *
     * @return Set de propostas
     */
    private HashSet<PropostaLegislativa> recuperarPropostas() {
        HashSet<PropostaLegislativa> propostas = new HashSet<>();
        try {
            File arqPropostas = new File("dados" + File.separator + "propostas.txt");
            if (arqPropostas.exists()) {
                ObjectInputStream objLeitor = new ObjectInputStream(new FileInputStream(arqPropostas));
                propostas = (HashSet<PropostaLegislativa>) objLeitor.readObject();
                objLeitor.close();
            }
        } catch (EOFException eof) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return propostas;
    }


    /**
     * Esse método serve para carregar o mapa de comissões com
     * os dados do arquivo.
     *
     * @param comissoes Set de comissões
     */
    private void carregarComissoes(HashSet<Comissao> comissoes) {
        if (comissoes != null) {
            Map<String, Comissao> mapaComissoes = new HashMap<>();

            Iterator<Comissao> it = comissoes.iterator();
            while (it.hasNext()) {
                Comissao comissao = it.next();
                mapaComissoes.put(comissao.getTema(), comissao);
            }
            this.projetoService.getComissaoService().setComissoes(mapaComissoes);
        }
    }

    /**
     * Esse método serve para carregar o mapa de pessoas com
     * os dados do arquivo.
     *
     * @param pessoas Set de pessoas
     */
    private void carregarPessoas(HashSet<Pessoa> pessoas) {
        if (pessoas != null) {
            Map<String, Pessoa> mapaPessoas = new HashMap<>();

            Iterator<Pessoa> it = pessoas.iterator();
            while (it.hasNext()) {
                Pessoa pessoa = it.next();
                mapaPessoas.put(pessoa.getDni(), pessoa);
            }

            this.projetoService.getPessoaService().setPessoas(mapaPessoas);
        }
    }

    /**
     * Esse método serve para carregar o mapa de partidos com
     * os dados do arquivo.
     *
     * @param partidos Set de partidos
     */
    private void carregarPartidos(HashSet<Partido> partidos) {
        if (partidos != null) {
            Map<String, Partido> mapaPartidos = new HashMap<>();

            Iterator<Partido> it = partidos.iterator();
            while (it.hasNext()) {
                Partido partido = it.next();
                mapaPartidos.put(partido.getNome(), partido);
            }

            this.projetoService.getPartidoService().setPartidos(mapaPartidos);
        }
    }

    /**
     * Esse método serve para carregar o mapa de propostas com
     * os dados do arquivo.
     *
     * @param propostas Set de propostas
     */
    private void carregarPropostas(HashSet<PropostaLegislativa> propostas) {
        if (propostas != null) {
            Map<String, PropostaLegislativa> mapaPropostas = new HashMap<>();

            Iterator<PropostaLegislativa> it = propostas.iterator();
            while (it.hasNext()) {
                PropostaLegislativa proposta = it.next();
                mapaPropostas.put(proposta.getCodigo(), proposta);
            }

            this.projetoService.setPropostas(mapaPropostas);
        }
    }

}
