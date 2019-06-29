package services;

import controllers.ProjetoController;
import interfaces.PropostaLegislativa;

import java.util.HashSet;
import java.util.Map;

/**
 * Essa classe usa o padrão Service contendo métodos que carregam
 *
 *
 * @author Jonathan Tavares da Silva
 * @author Mirella Quintans Lyra
 * @author Tulio Araujo Cunha
 * @author Guilherme de Melo Carneiro
 */
public class ProjetoService {
    /**
     * Armazena uma instância de ProjetoController
     */
    private ProjetoController projetoController;

    /**
     * Constrói um Service de Projeto com uma instância de
     * ProjetoController.
     *
     * @param projetoController instância de ProjetoController
     */
    public ProjetoService(ProjetoController projetoController) {
        this.projetoController = projetoController;
    }

    /**
     * Esse método serve para retornar o conjunto de propostas do
     * sistema.
     *
     * @return Set de propostas
     */
    public HashSet<PropostaLegislativa> getPropostas() {
        return new HashSet<>(this.projetoController.getPropostas());
    }

    /**
     * Esse método serve para retornar uma instância de PartidoService.
     *
     * @return PartidoService
     */
    public PartidoBaseService getPartidoService() {
        return this.projetoController.getPartidoService();
    }

    /**
     * Esse método serve para retornar uma instância de ComissaoService.
     *
     * @return ComissaoService
     */
    public ComissaoService getComissaoService() {
        return this.projetoController.getComissaoService();
    }

    /**
     * Esse método serve para retornar uma instância de PessoaService.
     *
     * @return PessoaService
     */
    public PessoaService getPessoaService() {
        return this.projetoController.getPessoaService();
    }

    /**
     * Esse método serve para carregar do arquivo de propostas
     * o mapa de propostas legislativas.
     *
     * @param mapaPropostas mapa de propostas
     */
    public void setPropostas(Map<String, PropostaLegislativa> mapaPropostas) {
        this.projetoController.setPropostas(mapaPropostas);
    }
}
