package controllers;

import entities.Comissao;
import entities.Partido;
import entities.Pessoa;
import interfaces.PropostaLegislativa;
import services.ProjetoService;

import java.io.*;
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
        this.salvarPessoas();
        this.salvarComissoes();
        this.salvarPartidos();
        this.salvarPropostas();
    }

    public void carregarSistema() {
        this.carregarPessoas();
        this.carregarComissoes();
        this.carregarPartidos();
        this.carregarPropostas();
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
            objLeitor = new ObjectInputStream(new FileInputStream("dados" + File.separator + "comissoes.txt"));

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



    private void salvarPessoas() {
        Set<Pessoa> pessoas = new HashSet<>(this.projetoService.getPessoaService().getPessoas());
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados" + File.separator + "pessoas.txt"));
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

    public Set recuperarPessoas() {
        ObjectInputStream objLeitor = null;
        try {
            objLeitor = new ObjectInputStream(new FileInputStream("dados" + File.separator + "pessoas.txt"));

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

    private void carregarPessoas() {

    }

    private void salvarPartidos() {
        Set<Partido> partidos = new HashSet<>(this.projetoService.getPartidoService().getPartidos());
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


}
