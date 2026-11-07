package com.sena.sisga.model;

import jakarta.persistence.*;

@Entity
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private int idAsignatura;

    @Column(name = "id_docente", nullable = false)
    private int idDocente;

    @Column(name = "codigo_asignatura", nullable = false, unique = true)
    private String codigoAsignatura;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "creditos")
    private int creditos;

    @Column(name = "descripcion")
    private String descripcion;

    public Asignatura() {}

    public Asignatura(int idDocente, String codigoAsignatura, String nombre, int creditos, String descripcion) {
        this.idDocente = idDocente;
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
        this.creditos = creditos;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdAsignatura() { return idAsignatura; }
    public void setIdAsignatura(int idAsignatura) { this.idAsignatura = idAsignatura; }
    public int getIdDocente() { return idDocente; }
    public void setIdDocente(int idDocente) { this.idDocente = idDocente; }
    public String getCodigoAsignatura() { return codigoAsignatura; }
    public void setCodigoAsignatura(String codigoAsignatura) { this.codigoAsignatura = codigoAsignatura; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}