package com.sena.sisga.dto;

import java.math.BigDecimal;

public class CalificacionDTO {
    private int idMatricula;
    private BigDecimal notaParcial1;
    private BigDecimal notaParcial2;
    private BigDecimal notaFinal;
    private String estado;

    // Getters y Setters
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