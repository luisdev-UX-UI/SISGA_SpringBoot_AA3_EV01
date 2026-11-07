package com.sena.sisga.controller;

import com.sena.sisga.dto.EstudianteDTO;
import com.sena.sisga.model.Estudiante;
import com.sena.sisga.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping
    public String listar(Model model) {
        List<Estudiante> lista = estudianteRepository.findAll();
        model.addAttribute("listaEstudiantes", lista);
        return "estudiantes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudianteDTO", new EstudianteDTO());
        return "estudiantes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EstudianteDTO dto) {
        Estudiante est = new Estudiante(dto.getIdUsuario(), dto.getCodigoEstudiante(),dto.getFechaIngreso(), dto.getEstado());
        estudianteRepository.save(est);
        return "redirect:/estudiantes";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute EstudianteDTO dto) {
        Estudiante est = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado: " + id));

        est.setIdUsuario(dto.getIdUsuario());
        est.setCodigoEstudiante(dto.getCodigoEstudiante());
        est.setFechaIngreso(dto.getFechaIngreso());
        est.setEstado(dto.getEstado());

        estudianteRepository.save(est);
        return "redirect:/estudiantes";
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable int id, @RequestBody Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setIdUsuario(estudianteActualizado.getIdUsuario());
                    estudiante.setCodigoEstudiante(estudianteActualizado.getCodigoEstudiante());
                    estudiante.setFechaIngreso(estudianteActualizado.getFechaIngreso());
                    estudiante.setEstado(estudianteActualizado.getEstado());
                    return estudianteRepository.save(estudiante);
                })
                .orElseGet(() -> {
                    estudianteActualizado.setIdEstudiante(id);
                    return estudianteRepository.save(estudianteActualizado);
                });
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        estudianteRepository.deleteById(id);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Estudiante est = estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado: " + id));

        EstudianteDTO dto = new EstudianteDTO();
        dto.setIdUsuario(est.getIdUsuario());
        dto.setCodigoEstudiante(est.getCodigoEstudiante());
        dto.setFechaIngreso(est.getFechaIngreso());
        dto.setEstado(est.getEstado());

        model.addAttribute("estudianteDTO", dto);
        model.addAttribute("idEstudiante", id);
        return "estudiantes/formularioEdicion";
    }

}