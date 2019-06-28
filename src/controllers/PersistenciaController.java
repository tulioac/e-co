package controllers;

import services.ProjetoService;

import java.io.*;


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
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("dados/eco.txt"));

            objGravador.writeObject(new Object());
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
}
