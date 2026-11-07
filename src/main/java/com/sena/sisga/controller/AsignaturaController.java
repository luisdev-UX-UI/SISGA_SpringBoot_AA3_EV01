package com.sena.sisga.controller;

import com.sena.sisga.dto.AsignaturaDTO;
import com.sena.sisga.model.Asignatura;
import com.sena.sisga.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @GetMapping
    public String listar(Model model) {
        List<Asignatura> lista = asignaturaRepository.findAll();
        model.addAttribute("listaAsignaturas", lista);
        return "asignaturas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asignaturaDTO", new AsignaturaDTO());
        return "asignaturas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute AsignaturaDTO dto) {
        Asignatura asig = new Asignatura(dto.getIdDocente(), dto.getCodigoAsignatura(), dto.getNombre(), dto.getCreditos(), dto.getDescripcion());
        asignaturaRepository.save(asig);
        return "redirect:/asignaturas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Asignatura asig = asignaturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asignatura no encontrada: " + id));
        AsignaturaDTO dto = new AsignaturaDTO();
        dto.setIdDocente(asig.getIdDocente());
        dto.setCodigoAsignatura(asig.getCodigoAsignatura());
        dto.setNombre(asig.getNombre());
        dto.setCreditos(asig.getCreditos());
        dto.setDescripcion(asig.getDescripcion());
        model.addAttribute("asignaturaDTO", dto);
        model.addAttribute("idAsignatura", id);
        return "asignaturas/formularioEdicion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute AsignaturaDTO dto) {
        Asignatura asig = asignaturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Asignatura no encontrada: " + id));
        asig.setIdDocente(dto.getIdDocente());
        asig.setCodigoAsignatura(dto.getCodigoAsignatura());
        asig.setNombre(dto.getNombre());
        asig.setCreditos(dto.getCreditos());
        asig.setDescripcion(dto.getDescripcion());
        asignaturaRepository.save(asig);
        return "redirect:/asignaturas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        asignaturaRepository.deleteById(id);
        return "redirect:/asignaturas";
    }
}