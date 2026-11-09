package com.sena.sisga.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private int idCalificacion;

    @Column(name = "id_matricula", nullable = false)
    private int idMatricula;

    @Column(name = "nota_parcial_1", precision = 4, scale = 2)
    private BigDecimal notaParcial1;

    @Column(name = "nota_parcial_2", precision = 4, scale = 2)
    private BigDecimal notaParcial2;

    @Column(name = "nota_final", precision = 4, scale = 2)
    private BigDecimal notaFinal;

    @Column(name = "estado", nullable = false)
    private String estado;

    public Calificacion() {}

    public Calificacion(int idMatricula, BigDecimal notaParcial1, BigDecimal notaParcial2, BigDecimal notaFinal, String estado) {
        this.idMatricula = idMatricula;
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.notaFinal = notaFinal;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdCalificacion() { return idCalificacion; }
    public void setIdCalificacion(int idCalificacion) { this.idCalificacion = idCalificacion; }
    public int getIdMatricula() { return idMatricula; }
    public void setIdMatricula(int idMatricula) { this.idMatricula = idMatricula; }
    public BigDecimal getNotaParcial1() { return notaParcial1; }
    public void setNotaParcial1(BigDecimal notaParcial1) { this.notaParcial1 = notaParcial1; }
    public BigDecimal getNotaParcial2() { return notaParcial2; }
    public void setNotaParcial2(BigDecimal notaParcial2) { this.notaParcial2 = notaParcial2; }
    public BigDecimal getNotaFinal() { return notaFinal; }
    public void setNotaFinal(BigDecimal notaFinal) { this.notaFinal = notaFinal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}