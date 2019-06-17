package controllers;

import java.io.*;


public class PersistenciaController {

    public void limparSistema() {
//        File arquivoEco = new File("eco.txt");
//        if (arquivoEco.exists()) arquivoEco.delete();
//        arquivoEco = new File("eco.txt");
    }

    public void salvarSistema(Serializable... controllers) {
        ObjectOutputStream objGravador = null;
        try {
            objGravador = new ObjectOutputStream(new FileOutputStream("eco.txt"));

            for (Serializable controller : controllers) objGravador.writeObject(controller);
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
            objLeitor = new ObjectInputStream(new FileInputStream("eco.txt"));

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
