package controllers;

import entities.Comissao;
import entities.Partido;
import entities.Pessoa;
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

    private void salvarComissoes() {
        Set<Comissao> comissoes = new HashSet<>(this.projetoService.getComissaoService().getComissoes());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados" + File.separator + "comissoes.txt"));
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

    public Set recuperarComissoes() {
        ObjectInputStream objLeitor = null;
        try {
            objLeitor = new ObjectInputStream(new FileInputStream("dados/comissoes.txt"));

            return (Set) objLeitor.readObject();

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
        return null;
    }

    private void carregarComissoes(Set comissoes) {
        Map<String, Comissao> mapaComissoes = new HashMap<>();

        for (Iterator<Comissao> it = comissoes.iterator(); it.hasNext();)
            mapaComissoes.put(it.next().getTema(), it.next());

        this.projetoService.getComissaoService().setComissoes(mapaComissoes);
    }

    private void salvarPessoas() {
        HashSet<Pessoa> pessoas = new HashSet<>(this.projetoService.getPessoaService().getPessoas());
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

    private HashSet<Pessoa> recuperarPessoas() {
        ObjectInputStream objLeitor = null;
        try {
            objLeitor = new ObjectInputStream(new FileInputStream("dados/pessoas.txt"));

            return (HashSet<Pessoa>) objLeitor.readObject();
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
        return null;
    }

    private void carregarPessoas(HashSet<Pessoa> pessoas) {
        Map<String, Pessoa> mapaPessoas = new HashMap<>();

        for (Iterator<Pessoa> it = pessoas.iterator(); it.hasNext(); )
            mapaPessoas.put(it.next().getDni(), it.next());

        this.projetoService.getPessoaService().setPessoas(mapaPessoas);
    }

    private void salvarPartidos() {
        HashSet<Partido> partidos = new HashSet<>(this.projetoService.getPartidoService().getPartidos());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados" + File.separator + "partidos.txt"));
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

    public Set recuperarPartidos() {
        ObjectInputStream objLeitor = null;
        try {
            objLeitor = new ObjectInputStream(new FileInputStream("dados" + File.separator + "partidos.txt"));

            return (Set) objLeitor.readObject();

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
        return null;
    }

    private void carregarPartidos(Set partidos) {
        Map<String, Partido> mapaPartidos = new HashMap<>();

        for (Iterator<Partido> it = partidos.iterator(); it.hasNext();)
            mapaPartidos.put(it.next().getNome(), it.next());

        this.projetoService.getPartidoService().setPartidos(mapaPartidos);
    }

    private void salvarPropostas() {
        Set<PropostaLegislativa> propostas = new HashSet<>(this.projetoService.getPropostas());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados" + File.separator + "propostas.txt"));
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

    public Set recuperarPropostas() {
        ObjectInputStream objLeitor = null;
        try {
            objLeitor = new ObjectInputStream(new FileInputStream("dados" + File.separator + "propostas.txt"));

            return (Set) objLeitor.readObject();

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
        return null;
    }

    private void carregarPropostas(Set propostas) {
        Map<String, PropostaLegislativa> mapaPropostas = new HashMap<>();

        for (Iterator<PropostaLegislativa> it = propostas.iterator(); it.hasNext();)
            mapaPropostas.put(it.next().getCodigo(), it.next());

        this.projetoService.setPessoas(mapaPropostas);
    }

}
