package com.otavio.loginseguro.dto;

public class ChamadosPorSituacaoDTO {

    private String situacao;
    private long quantidade;

    public ChamadosPorSituacaoDTO(String situacao, long quantidade) {
        this.situacao = situacao;
        this.quantidade = quantidade;
    }

    public String getSituacao() {
        return situacao;
    }

    public long getQuantidade() {
        return quantidade;
    }
}