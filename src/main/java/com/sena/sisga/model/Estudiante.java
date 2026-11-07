package com.sena.sisga.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private int idEstudiante;

    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Column(name = "codigo_estudiante", nullable = false, unique = true)
    private String codigoEstudiante;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;

    @Column(name = "estado", nullable = false)
    private String estado;

    public Estudiante() {}

    public Estudiante(int idUsuario, String codigoEstudiante, LocalDate fechaIngreso, String estado) {
        this.idUsuario = idUsuario;
        this.codigoEstudiante = codigoEstudiante;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(int idEstudiante) { this.idEstudiante = idEstudiante; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getCodigoEstudiante() { return codigoEstudiante; }
    public void setCodigoEstudiante(String codigoEstudiante) { this.codigoEstudiante = codigoEstudiante; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}