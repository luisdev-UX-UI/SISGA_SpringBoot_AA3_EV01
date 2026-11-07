package com.sena.sisga.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private int idDocente;

    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;

    @Column(name = "codigo_docente", nullable = false, unique = true)
    private String codigoDocente;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "fecha_contratacion", nullable = false)
    private LocalDate fechaContratacion;

    public Docente() {}

    public Docente(int idUsuario, String codigoDocente, String especialidad, LocalDate fechaContratacion) {
        this.idUsuario = idUsuario;
        this.codigoDocente = codigoDocente;
        this.especialidad = especialidad;
        this.fechaContratacion = fechaContratacion;
    }

    // Getters y Setters
    public int getIdDocente() { return idDocente; }
    public void setIdDocente(int idDocente) { this.idDocente = idDocente; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getCodigoDocente() { return codigoDocente; }
    public void setCodigoDocente(String codigoDocente) { this.codigoDocente = codigoDocente; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
}