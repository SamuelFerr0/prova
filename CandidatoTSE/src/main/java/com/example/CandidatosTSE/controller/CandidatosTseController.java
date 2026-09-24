package com.example.CandidatosTSE.controller;

import com.example.CandidatosTSE.model.Candidato;
import com.example.CandidatosTSE.service.CandidatosTseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService candidatosTseService;

    public CandidatosTseController(CandidatosTseService candidatosTseService) {
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String partido,
            @RequestParam(required = false) String texto,
            Model model) {

        String cargoSel = (cargo != null) ? cargo : "";
        String partidoSel = (partido != null) ? partido : "";
        String textoSel = (texto != null) ? texto : "";

        List<Candidato> listaFiltrada = candidatosTseService.filtrar(cargoSel, partidoSel, textoSel);

        model.addAttribute("candidatos", listaFiltrada);
        model.addAttribute("totalEncontrado", listaFiltrada.size());

        model.addAttribute("cargos", candidatosTseService.listarCargos());
        model.addAttribute("partidos", candidatosTseService.listarPartidos());

        model.addAttribute("cargoSelecionado", cargoSel);
        model.addAttribute("partidoSelecionado", partidoSel);
        model.addAttribute("textoSelecionado", textoSel);

        return "index";
    }
}
