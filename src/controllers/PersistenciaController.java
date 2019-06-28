package controllers;

import entities.Comissao;
import entities.Partido;
import entities.Pessoa;
import interfaces.PropostaLegislativa;
import services.ProjetoService;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class PersistenciaController {
    private ProjetoService projetoService;

    public PersistenciaController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    public void limparSistema() {
        File arquivoEco = new File("eco.txt");
        if (arquivoEco.exists()) arquivoEco.delete();
//        arquivoEco = new File("eco.txt");
    }

    public void salvarSistema() {

    }

    public void carregarSistema() {
        ObjectInputStream objLeitor = null;
        try {
            objLeitor = new ObjectInputStream(new FileInputStream("dados/eco.txt"));

            objLeitor.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objLeitor != null) {
                try {
                    objLeitor.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void salvarComissoes() {
        Set<Comissao> comissoes = new HashSet<>(this.projetoService.getComissaoService().getComissoes());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados/comissoes.txt"));
            objGravador.writeObject(comissoes);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (objGravador != null) {
                try {
                    objGravador.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void salvarPessoas() {
        Set<Pessoa> pessoas = new HashSet<>(this.projetoService.getPessoaService().getPessoas());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados/pessoas.txt"));
            objGravador.writeObject(pessoas);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (objGravador != null) {
                try {
                    objGravador.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void salvarPartidos() {
        Set<Partido> partidos = new HashSet<>(this.projetoService.getPartidoService().getPartidos());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados/partidos.txt"));
            objGravador.writeObject(partidos);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (objGravador != null) {
                try {
                    objGravador.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void salvarPropostas() {
        Set<PropostaLegislativa> propostas = new HashSet<>(this.projetoService.getPropostas());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados/propostas.txt"));
            objGravador.writeObject(propostas);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (objGravador != null) {
                try {
                    objGravador.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
