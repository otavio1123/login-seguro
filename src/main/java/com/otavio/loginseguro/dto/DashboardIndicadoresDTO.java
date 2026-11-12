package com.otavio.loginseguro.dto;

public class DashboardIndicadoresDTO {

    private long total;
    private long abertos;
    private long emAndamento;
    private long concluidos;
    private long cancelados;

    public DashboardIndicadoresDTO(
            long total,
            long abertos,
            long emAndamento,
            long concluidos,
            long cancelados) {

        this.total = total;
        this.abertos = abertos;
        this.emAndamento = emAndamento;
        this.concluidos = concluidos;
        this.cancelados = cancelados;
    }

    public long getTotal() {
        return total;
    }

    public long getAbertos() {
        return abertos;
    }

    public long getEmAndamento() {
        return emAndamento;
    }

    public long getConcluidos() {
        return concluidos;
    }

    public long getCancelados() {
        return cancelados;
    }
}