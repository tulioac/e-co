package services;

import controllers.ProjetoController;
import interfaces.PropostaLegislativa;

import java.util.HashSet;
import java.util.Map;

public class ProjetoService {
    private ProjetoController projetoController;

    public ProjetoService(ProjetoController projetoController) {
        this.projetoController = projetoController;
    }

    public HashSet<PropostaLegislativa> getPropostas() {
        return new HashSet<>(this.projetoController.getPropostas());
    }

    public PartidoBaseService getPartidoService() {
        return this.projetoController.getPartidoService();
    }

    public ComissaoService getComissaoService() {
        return this.projetoController.getComissaoService();
    }

    public PessoaService getPessoaService() {
        return this.projetoController.getPessoaService();
    }

    public void setPropostas(Map<String, PropostaLegislativa> mapaPropostas) {
        this.projetoController.setPropostas(mapaPropostas);
    }
}
