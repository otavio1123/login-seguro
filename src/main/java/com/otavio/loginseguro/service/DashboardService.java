package com.otavio.loginseguro.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.otavio.loginseguro.dto.ChamadosPorPeriodoDTO;
import com.otavio.loginseguro.dto.ChamadosPorSituacaoDTO;
import com.otavio.loginseguro.dto.ChamadosPorTipoDTO;
import com.otavio.loginseguro.dto.DashboardIndicadoresDTO;
import com.otavio.loginseguro.model.Chamado;
import com.otavio.loginseguro.model.StatusChamado;
import com.otavio.loginseguro.model.TipoChamado;
import com.otavio.loginseguro.repository.ChamadoRepository;

@Service
public class DashboardService {

    private final ChamadoRepository chamadoRepository;

    public DashboardService(ChamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public DashboardIndicadoresDTO buscarIndicadores() {

        long total = chamadoRepository.count();
        long abertos = chamadoRepository.countByStatus(StatusChamado.ABERTO);
        long emAndamento = chamadoRepository.countByStatus(StatusChamado.EM_ANDAMENTO);
        long concluidos = chamadoRepository.countByStatus(StatusChamado.CONCLUIDO);
        long cancelados = chamadoRepository.countByStatus(StatusChamado.CANCELADO);

        return new DashboardIndicadoresDTO(
                total,
                abertos,
                emAndamento,
                concluidos,
                cancelados
        );
    }

    public List<ChamadosPorPeriodoDTO> buscarChamadosPorPeriodo(
            LocalDate dataInicio,
            LocalDate dataFim) {

        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(LocalTime.MAX);

        List<Chamado> chamados = chamadoRepository
                .findByStatusAndDataConclusaoBetween(
                        StatusChamado.CONCLUIDO,
                        inicio,
                        fim
                );

        Map<LocalDate, Long> quantidadePorDia = new TreeMap<>();

        for (Chamado chamado : chamados) {
            LocalDate data = chamado.getDataConclusao().toLocalDate();

            quantidadePorDia.put(
                    data,
                    quantidadePorDia.getOrDefault(data, 0L) + 1
            );
        }

        List<ChamadosPorPeriodoDTO> resultado = new ArrayList<>();

        for (Map.Entry<LocalDate, Long> item : quantidadePorDia.entrySet()) {
            resultado.add(
                    new ChamadosPorPeriodoDTO(
                            item.getKey(),
                            item.getValue()
                    )
            );
        }

        return resultado;
    }

    public List<ChamadosPorSituacaoDTO> buscarChamadosPorSituacao() {

        List<ChamadosPorSituacaoDTO> resultado = new ArrayList<>();

        resultado.add(new ChamadosPorSituacaoDTO(
                "ABERTO",
                chamadoRepository.countByStatus(StatusChamado.ABERTO)
        ));

        resultado.add(new ChamadosPorSituacaoDTO(
                "EM_ANDAMENTO",
                chamadoRepository.countByStatus(StatusChamado.EM_ANDAMENTO)
        ));

        resultado.add(new ChamadosPorSituacaoDTO(
                "CONCLUIDO",
                chamadoRepository.countByStatus(StatusChamado.CONCLUIDO)
        ));

        resultado.add(new ChamadosPorSituacaoDTO(
                "CANCELADO",
                chamadoRepository.countByStatus(StatusChamado.CANCELADO)
        ));

        return resultado;
    }

    public List<ChamadosPorTipoDTO> buscarChamadosPorTipo() {

        List<ChamadosPorTipoDTO> resultado = new ArrayList<>();

        for (TipoChamado tipo : TipoChamado.values()) {

            long quantidade = chamadoRepository.countByTipo(tipo);

            if (quantidade > 0) {
                resultado.add(
                        new ChamadosPorTipoDTO(
                                tipo.name(),
                                quantidade
                        )
                );
            }
        }

        resultado.sort(
                (a, b) -> Long.compare(
                        b.getQuantidade(),
                        a.getQuantidade()
                )
        );

        return resultado;
    }
}