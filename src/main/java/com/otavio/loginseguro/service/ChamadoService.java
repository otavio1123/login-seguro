package com.otavio.loginseguro.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.otavio.loginseguro.model.Chamado;
import com.otavio.loginseguro.model.StatusChamado;
import com.otavio.loginseguro.model.TipoChamado;
import com.otavio.loginseguro.repository.ChamadoRepository;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;

    public ChamadoService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public Chamado salvar(Chamado chamado) {
        return chamadoRepository.save(chamado);
    }

    public List<Chamado> listarTodos() {
        return chamadoRepository.findAll();
    }

    public Chamado buscarPorId(String id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado"));
    }

    public Chamado atualizarStatus(String id, StatusChamado status) {
        Chamado chamado = buscarPorId(id);

        chamado.setStatus(status);

        if (status == StatusChamado.CONCLUIDO) {
            chamado.setDataConclusao(LocalDateTime.now());
        } else {
            chamado.setDataConclusao(null);
        }

        return chamadoRepository.save(chamado);
    }

    public Chamado atualizarConsideracaoTecnica(String id, String consideracaoTecnica) {
        Chamado chamado = buscarPorId(id);

        chamado.setConsideracaoTecnica(consideracaoTecnica);

        return chamadoRepository.save(chamado);
    }

    public List<Chamado> filtrarChamados(
            StatusChamado status,
            TipoChamado tipo,
            LocalDate dataInicio,
            LocalDate dataFim) {

        List<Chamado> chamados = chamadoRepository.findAll();
        List<Chamado> resultado = new ArrayList<>();

        for (Chamado chamado : chamados) {

            if (status != null && chamado.getStatus() != status) {
                continue;
            }

            if (tipo != null && chamado.getTipo() != tipo) {
                continue;
            }

            if (dataInicio != null) {
                if (chamado.getDataAbertura() == null
                        || chamado.getDataAbertura().toLocalDate().isBefore(dataInicio)) {
                    continue;
                }
            }

            if (dataFim != null) {
                if (chamado.getDataAbertura() == null
                        || chamado.getDataAbertura().toLocalDate().isAfter(dataFim)) {
                    continue;
                }
            }

            resultado.add(chamado);
        }

        return resultado;
    }
}