package com.sena.sisga.controller;

import com.sena.sisga.dto.MatriculaDTO;
import com.sena.sisga.model.Matricula;
import com.sena.sisga.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    public String listar(Model model) {
        List<Matricula> lista = matriculaRepository.findAll();
        model.addAttribute("listaMatriculas", lista);
        return "matriculas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("matriculaDTO", new MatriculaDTO());
        return "matriculas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute MatriculaDTO dto) {
        Matricula mat = new Matricula(dto.getIdEstudiante(), dto.getIdAsignatura(), dto.getPeriodo(), dto.getFechaMatricula(), dto.getEstado());
        matriculaRepository.save(mat);
        return "redirect:/matriculas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Matricula mat = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada: " + id));
        MatriculaDTO dto = new MatriculaDTO();
        dto.setIdEstudiante(mat.getIdEstudiante());
        dto.setIdAsignatura(mat.getIdAsignatura());
        dto.setPeriodo(mat.getPeriodo());
        dto.setFechaMatricula(mat.getFechaMatricula());
        dto.setEstado(mat.getEstado());
        model.addAttribute("matriculaDTO", dto);
        model.addAttribute("idMatricula", id);
        return "matriculas/formularioEdicion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute MatriculaDTO dto) {
        Matricula mat = matriculaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada: " + id));
        mat.setIdEstudiante(dto.getIdEstudiante());
        mat.setIdAsignatura(dto.getIdAsignatura());
        mat.setPeriodo(dto.getPeriodo());
        mat.setFechaMatricula(dto.getFechaMatricula());
        mat.setEstado(dto.getEstado());
        matriculaRepository.save(mat);
        return "redirect:/matriculas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        matriculaRepository.deleteById(id);
        return "redirect:/matriculas";
    }
}