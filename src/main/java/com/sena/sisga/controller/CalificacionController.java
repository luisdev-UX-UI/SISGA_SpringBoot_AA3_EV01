package com.sena.sisga.controller;

import com.sena.sisga.dto.CalificacionDTO;
import com.sena.sisga.model.Calificacion;
import com.sena.sisga.repository.CalificacionRepository;
import com.sena.sisga.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @GetMapping
    public String listar(Model model) {
        List<Calificacion> lista = calificacionRepository.findAll();
        model.addAttribute("listaCalificaciones", lista);
        return "calificaciones/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("calificacionDTO", new CalificacionDTO());
        return "calificaciones/formulario";
    }

    @Autowired
    private MatriculaRepository matriculaRepository;
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute CalificacionDTO dto, Model model) {
        // Validar que la matrícula exista
        if (!matriculaRepository.existsById(dto.getIdMatricula())) {
            model.addAttribute("error", "La matrícula con ID " + dto.getIdMatricula() + " no existe.");
            model.addAttribute("calificacionDTO", dto);
            return "calificaciones/formulario";
        }

        Calificacion cal = new Calificacion(dto.getIdMatricula(), dto.getNotaParcial1(), dto.getNotaParcial2(), dto.getNotaFinal(), dto.getEstado());
        calificacionRepository.save(cal);
        return "redirect:/calificaciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Calificacion cal = calificacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Calificación no encontrada: " + id));
        CalificacionDTO dto = new CalificacionDTO();
        dto.setIdMatricula(cal.getIdMatricula());
        dto.setNotaParcial1(cal.getNotaParcial1());
        dto.setNotaParcial2(cal.getNotaParcial2());
        dto.setNotaFinal(cal.getNotaFinal());
        dto.setEstado(cal.getEstado());
        model.addAttribute("calificacionDTO", dto);
        model.addAttribute("idCalificacion", id);
        return "calificaciones/formularioEdicion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute CalificacionDTO dto) {
        Calificacion cal = calificacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Calificación no encontrada: " + id));
        cal.setIdMatricula(dto.getIdMatricula());
        cal.setNotaParcial1(dto.getNotaParcial1());
        cal.setNotaParcial2(dto.getNotaParcial2());
        cal.setNotaFinal(dto.getNotaFinal());
        cal.setEstado(dto.getEstado());
        calificacionRepository.save(cal);
        return "redirect:/calificaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        calificacionRepository.deleteById(id);
        return "redirect:/calificaciones";
    }
}