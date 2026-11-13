package com.otavio.loginseguro.dto;

public class ChamadosPorTipoDTO {

    private String tipo;
    private long quantidade;

    public ChamadosPorTipoDTO(String tipo, long quantidade) {
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public long getQuantidade() {
        return quantidade;
    }
}