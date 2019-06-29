package services;

import controllers.ProjetoController;
import interfaces.PropostaLegislativa;

import java.util.HashSet;
import java.util.Set;

public class ProjetoService {
    private ProjetoController projetoController;

    public ProjetoService(ProjetoController projetoController) {
        this.projetoController = projetoController;
    }

    public Set<PropostaLegislativa> getPropostas() {
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
}
