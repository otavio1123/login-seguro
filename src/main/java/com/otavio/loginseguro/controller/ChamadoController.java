package com.otavio.loginseguro.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.otavio.loginseguro.model.Chamado;
import com.otavio.loginseguro.model.StatusChamado;
import com.otavio.loginseguro.model.TipoChamado;
import com.otavio.loginseguro.service.ChamadoService;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @GetMapping
    public List<Chamado> listarTodos() {
        return chamadoService.listarTodos();
    }

    @GetMapping("/filtro")
    public List<Chamado> filtrarChamados(
            @RequestParam(required = false) StatusChamado status,
            @RequestParam(required = false) TipoChamado tipo,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataInicio,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dataFim) {

        return chamadoService.filtrarChamados(
                status,
                tipo,
                dataInicio,
                dataFim
        );
    }

    @GetMapping("/{id}")
    public Chamado buscarPorId(@PathVariable String id) {
        return chamadoService.buscarPorId(id);
    }

    @PostMapping
    public Chamado criar(@RequestBody Chamado chamado) {
        return chamadoService.salvar(chamado);
    }

    @PutMapping("/{id}/status")
    public Chamado atualizarStatus(
            @PathVariable String id,
            @RequestParam StatusChamado status) {

        return chamadoService.atualizarStatus(id, status);
    }

    @PutMapping("/{id}/consideracao")
    public Chamado atualizarConsideracao(
            @PathVariable String id,
            @RequestBody String consideracaoTecnica) {

        return chamadoService.atualizarConsideracaoTecnica(
                id,
                consideracaoTecnica
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarErro(IllegalArgumentException erro) {
        return ResponseEntity.badRequest().body(erro.getMessage());
    }
}