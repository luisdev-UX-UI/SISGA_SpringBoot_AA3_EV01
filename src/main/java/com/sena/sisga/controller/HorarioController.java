package com.sena.sisga.controller;

import com.sena.sisga.dto.HorarioDTO;
import com.sena.sisga.model.Horario;
import com.sena.sisga.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioRepository horarioRepository;

    @GetMapping
    public String listar(Model model) {
        List<Horario> lista = horarioRepository.findAll();
        model.addAttribute("listaHorarios", lista);
        return "horarios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("horarioDTO", new HorarioDTO());
        return "horarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute HorarioDTO dto, Model model) {
        // Validar que la hora de fin sea mayor que la hora de inicio
        if (dto.getHoraFin().isBefore(dto.getHoraInicio()) || dto.getHoraFin().equals(dto.getHoraInicio())) {
            model.addAttribute("error", "La hora de fin debe ser mayor que la hora de inicio.");
            model.addAttribute("horarioDTO", dto);
            return "horarios/formulario"; // Volver al formulario con el error
        }

        Horario hor = new Horario(dto.getIdAsignatura(), dto.getDiaSemana(), dto.getHoraInicio(), dto.getHoraFin(), dto.getAula());
        horarioRepository.save(hor);
        return "redirect:/horarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Horario hor = horarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Horario no encontrado: " + id));
        HorarioDTO dto = new HorarioDTO();
        dto.setIdAsignatura(hor.getIdAsignatura());
        dto.setDiaSemana(hor.getDiaSemana());
        dto.setHoraInicio(hor.getHoraInicio());
        dto.setHoraFin(hor.getHoraFin());
        dto.setAula(hor.getAula());
        model.addAttribute("horarioDTO", dto);
        model.addAttribute("idHorario", id);
        return "horarios/formularioEdicion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable int id, @ModelAttribute HorarioDTO dto) {
        Horario hor = horarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Horario no encontrado: " + id));
        hor.setIdAsignatura(dto.getIdAsignatura());
        hor.setDiaSemana(dto.getDiaSemana());
        hor.setHoraInicio(dto.getHoraInicio());
        hor.setHoraFin(dto.getHoraFin());
        hor.setAula(dto.getAula());
        horarioRepository.save(hor);
        return "redirect:/horarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        horarioRepository.deleteById(id);
        return "redirect:/horarios";
    }
}