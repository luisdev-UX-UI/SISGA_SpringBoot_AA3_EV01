package com.sena.sisga.controller;

import com.sena.sisga.dto.DocenteDTO;
import com.sena.sisga.model.Docente;
import com.sena.sisga.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteRepository docenteRepository;

    @GetMapping
    public String listar(Model model) {
        List<Docente> lista = docenteRepository.findAll();
        model.addAttribute("listaDocentes", lista);
        return "docentes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("docenteDTO", new DocenteDTO());
        return "docentes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DocenteDTO dto) {
        Docente doc = new Docente(dto.getIdUsuario(), dto.getCodigoDocente(), dto.getEspecialidad(), dto.getFechaContratacion());
        docenteRepository.save(doc);
        return "redirect:/docentes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Docente doc = docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado: " + id));
        DocenteDTO dto = new DocenteDTO();
        dto.setIdUsuario(doc.getIdUsuario());
        dto.setCodigoDocente(doc.getCodigoDocente());
        dto.setEspecialidad(doc.getEspecialidad());
        dto.setFechaContratacion(doc.getFechaContratacion());
        model.addAttribute("docenteDTO", dto);
        model.addAttribute("idDocente", id);
        return "docentes/formularioEdicion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute DocenteDTO dto) {
        Docente doc = docenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado: " + id));
        doc.setIdUsuario(dto.getIdUsuario());
        doc.setCodigoDocente(dto.getCodigoDocente());
        doc.setEspecialidad(dto.getEspecialidad());
        doc.setFechaContratacion(dto.getFechaContratacion());
        docenteRepository.save(doc);
        return "redirect:/docentes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        docenteRepository.deleteById(id);
        return "redirect:/docentes";
    }
}