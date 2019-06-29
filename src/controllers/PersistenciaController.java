package controllers;

import entities.Comissao;
import entities.Partido;
import entities.Pessoa;
import entities.Projeto;
import interfaces.PropostaLegislativa;
import services.ProjetoService;

import java.io.*;
import java.util.*;


public class PersistenciaController {
    private ProjetoService projetoService;

    public PersistenciaController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

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

    public void salvarSistema() {
        this.salvarPessoas();
        this.salvarComissoes();
        this.salvarPartidos();
        this.salvarPropostas();
    }

    public void carregarSistema() {
        this.carregarPessoas(this.recuperarPessoas());
        this.carregarComissoes(this.recuperarComissoes());
        this.carregarPartidos(this.recuperarPartidos());
        this.carregarPropostas(this.recuperarPropostas());
    }

    //
    // SALVAR COMISSÕES
    //
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

    //
    // RECUPERAR COMISSÕES
    //
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

    //
    // CARREGAR COMISSÕES
    //
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
