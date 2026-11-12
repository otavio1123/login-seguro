package com.otavio.loginseguro.dto;

import java.time.LocalDate;

public class ChamadosPorPeriodoDTO {

    private LocalDate data;
    private long quantidade;

    public ChamadosPorPeriodoDTO(LocalDate data, long quantidade) {
        this.data = data;
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public long getQuantidade() {
        return quantidade;
    }
}