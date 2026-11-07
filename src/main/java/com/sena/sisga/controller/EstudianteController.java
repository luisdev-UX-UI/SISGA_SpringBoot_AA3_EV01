package com.sena.sisga.controller;

import com.sena.sisga.model.Estudiante;
import com.sena.sisga.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping
    public String listar(Model model) {
        List<Estudiante> lista = estudianteRepository.findAll();
        model.addAttribute("listaEstudiantes", lista);
        return "estudiantes/lista";
    }

    @PostMapping
    public Estudiante crear(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
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

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        estudianteRepository.deleteById(id);
    }
}