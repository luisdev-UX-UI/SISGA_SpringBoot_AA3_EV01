package com.sena.sisga.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private int idMatricula;

    @Column(name = "id_estudiante", nullable = false)
    private int idEstudiante;

    @Column(name = "id_asignatura", nullable = false)
    private int idAsignatura;

    @Column(name = "periodo", nullable = false)
    private String periodo;

    @Column(name = "fecha_matricula", nullable = false)
    private LocalDate fechaMatricula;

    @Column(name = "estado", nullable = false)
    private String estado;

    public Matricula() {}

    public Matricula(int idEstudiante, int idAsignatura, String periodo, LocalDate fechaMatricula, String estado) {
        this.idEstudiante = idEstudiante;
        this.idAsignatura = idAsignatura;
        this.periodo = periodo;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdMatricula() { return idMatricula; }
    public void setIdMatricula(int idMatricula) { this.idMatricula = idMatricula; }
    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }
    public int getIdAsignatura() { return idAsignatura; }
    public void setIdAsignatura(int idAsignatura) { this.idAsignatura = idAsignatura; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public LocalDate getFechaMatricula() { return fechaMatricula; }
    public void setFechaMatricula(LocalDate fechaMatricula) { this.fechaMatricula = fechaMatricula; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}