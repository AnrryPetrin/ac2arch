package com.anrry.ac2arch.modules.resumotreinamento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumoTreinamentoServiceImpl implements ResumoTreinamentoService {

    @Autowired
    private ResumoTreinamentoRepository resumoTreinamentoRepository;

    @Override
    public ResumoTreinamento saveResumo(ResumoTreinamento resumo) {
        return resumoTreinamentoRepository.save(resumo);
    }

    @Override
    public Optional<ResumoTreinamento> getResumoById(Long id) {
        return resumoTreinamentoRepository.findById(id);
    }

    @Override
    public List<ResumoTreinamento> getAllResumos() {
        return resumoTreinamentoRepository.findAll();
    }

    @Override
    public void deleteResumo(Long id) {
        resumoTreinamentoRepository.deleteById(id);
    }

    @Override
    public List<ResumoTreinamento> getResumosByProfessorId(Long professorId) {
        return resumoTreinamentoRepository.findByProfessorId(professorId);
    }

    @Override
    public List<ResumoTreinamento> getResumosByAgendaId(Long agendaId) {
        return resumoTreinamentoRepository.findByAgendaId(agendaId);
    }
}

