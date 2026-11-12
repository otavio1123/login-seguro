package com.otavio.loginseguro.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.otavio.loginseguro.model.Chamado;
import com.otavio.loginseguro.model.StatusChamado;
import com.otavio.loginseguro.model.TipoChamado;

public interface ChamadoRepository extends MongoRepository<Chamado, String> {

    long countByStatus(StatusChamado status);

    long countByTipo(TipoChamado tipo);

    List<Chamado> findByStatusAndDataConclusaoBetween(
            StatusChamado status,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}