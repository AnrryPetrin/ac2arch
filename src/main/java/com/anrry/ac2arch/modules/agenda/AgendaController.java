package com.anrry.ac2arch.modules.agenda;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anrry.ac2arch.modules.curso.Curso;
import com.anrry.ac2arch.modules.curso.CursoService;
import com.anrry.ac2arch.modules.professor.Professor;
import com.anrry.ac2arch.modules.professor.ProfessorService;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private CursoService cursoService;

    /**
     * Criar uma nova Agenda.
     * 
     * @param agenda Objeto Agenda a ser criado.
     * @return Agenda criada com status 201 ou erro 400 em caso de conflito.
     */
    @PostMapping
    public ResponseEntity<?> createAgenda(@RequestBody Agenda agenda) {
        try {
            // Validação: Verificar se o Curso existe
            Long cursoId = agenda.getCurso().getId();
            Optional<Curso> cursoOpt = cursoService.getCursoById(cursoId);
            if (!cursoOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Curso com ID " + cursoId + " não encontrado.");
            }

            // Validação: Verificar se o Professor existe
            Long professorId = agenda.getProfessor().getId();
            Optional<Professor> professorOpt = professorService.getProfessorById(professorId);
            if (!professorOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Professor com ID " + professorId + " não encontrado.");
            }

            // Associar Curso e Professor existentes à Agenda
            agenda.setCurso(cursoOpt.get());
            agenda.setProfessor(professorOpt.get());

            Agenda savedAgenda = agendaService.saveAgenda(agenda);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAgenda);
        } catch (IllegalArgumentException e) {
            // Conflito de horário
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Erro interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar agenda: " + e.getMessage());
        }
    }

    /**
     * Obter uma Agenda pelo ID.
     * 
     * @param id ID da Agenda.
     * @return Agenda encontrada ou 404 se não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Agenda> getAgendaById(@PathVariable Long id) {
        Optional<Agenda> agenda = agendaService.getAgendaById(id);
        return agenda.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obter todas as Agendas.
     * 
     * @return Lista de Agendas.
     */
    @GetMapping
    public ResponseEntity<List<Agenda>> getAllAgendas() {
        List<Agenda> agendas = agendaService.getAllAgendas();
        return ResponseEntity.ok(agendas);
    }

    /**
     * Deletar uma Agenda pelo ID.
     * 
     * @param id ID da Agenda.
     * @return Status 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgenda(@PathVariable Long id) {
        agendaService.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obter Agendas associadas a um Curso específico.
     * 
     * @param cursoId ID do Curso.
     * @return Lista de Agendas associadas ao Curso.
     */
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Agenda>> getAgendasByCursoId(@PathVariable Long cursoId) {
        List<Agenda> agendas = agendaService.findByCursoId(cursoId);
        return ResponseEntity.ok(agendas);
    }

    /**
     * Obter Agendas associadas a um Professor específico.
     * 
     * @param professorId ID do Professor.
     * @return Lista de Agendas associadas ao Professor.
     */
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<Agenda>> getAgendasByProfessorId(@PathVariable Long professorId) {
        Optional<Professor> professorOpt = professorService.getProfessorById(professorId);
        if (!professorOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
        List<Agenda> agendas = agendaService.findByProfessorAndDateRange(
                professorOpt.get(),
                LocalDate.now().minusDays(1), // Por exemplo, buscar a partir de ontem
                LocalDate.now().plusYears(10) // Até daqui a 10 anos
        );
        return ResponseEntity.ok(agendas);
    }
}
