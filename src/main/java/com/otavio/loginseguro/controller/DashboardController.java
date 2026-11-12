package com.otavio.loginseguro.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otavio.loginseguro.dto.ChamadosPorPeriodoDTO;
import com.otavio.loginseguro.dto.ChamadosPorSituacaoDTO;
import com.otavio.loginseguro.dto.ChamadosPorTipoDTO;
import com.otavio.loginseguro.dto.DashboardIndicadoresDTO;
import com.otavio.loginseguro.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/indicadores")
    public DashboardIndicadoresDTO buscarIndicadores() {
        return dashboardService.buscarIndicadores();
    }

    @GetMapping("/chamados-periodo")
    public List<ChamadosPorPeriodoDTO> buscarChamadosPorPeriodo(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicio,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFim) {

        return dashboardService.buscarChamadosPorPeriodo(
                dataInicio,
                dataFim
        );
    }

    @GetMapping("/situacoes")
    public List<ChamadosPorSituacaoDTO> buscarChamadosPorSituacao() {
        return dashboardService.buscarChamadosPorSituacao();
    }

    @GetMapping("/tipos")
    public List<ChamadosPorTipoDTO> buscarChamadosPorTipo() {
        return dashboardService.buscarChamadosPorTipo();
    }
}