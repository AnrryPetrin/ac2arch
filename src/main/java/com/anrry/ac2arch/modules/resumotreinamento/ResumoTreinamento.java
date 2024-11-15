package com.anrry.ac2arch.modules.resumotreinamento;

import java.time.LocalDate;

import com.anrry.ac2arch.modules.agenda.Agenda;
import com.anrry.ac2arch.modules.professor.Professor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResumoTreinamento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate data;

  @Column(length = 2000)
  private String ocorrencias;

  @ManyToOne
  @JoinColumn(name = "agenda_id", nullable = false)
  private Agenda agenda;

  @ManyToOne
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;
}
