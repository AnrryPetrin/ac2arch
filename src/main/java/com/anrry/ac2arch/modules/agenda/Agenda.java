package com.anrry.ac2arch.modules.agenda;

import java.time.LocalDate;
import java.time.LocalTime;

import com.anrry.ac2arch.modules.curso.Curso;
import com.anrry.ac2arch.modules.professor.Professor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate dataInicio;
  private LocalDate dataFim;
  private LocalTime horarioInicio;
  private LocalTime horarioFim;
  private String cidade;
  private String estado;
  private String cep;

  @ManyToOne
  @JoinColumn(name = "curso_id", nullable = false)
  private Curso curso;

  @ManyToOne
  @JoinColumn(name = "professor_id", nullable = false)
  private Professor professor;
}
