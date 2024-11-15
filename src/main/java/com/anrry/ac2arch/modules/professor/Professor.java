package com.anrry.ac2arch.modules.professor;

import java.util.Set;

import com.anrry.ac2arch.modules.curso.Curso;
import com.anrry.ac2arch.modules.especializacao.Especializacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String celular;

    @ManyToMany
    @JoinTable(name = "professor_curso", joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private Set<Curso> cursos;

    @ManyToMany
    @JoinTable(name = "professor_especializacao", joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "especializacao_id"))
    private Set<Especializacao> especializacoes;
}
