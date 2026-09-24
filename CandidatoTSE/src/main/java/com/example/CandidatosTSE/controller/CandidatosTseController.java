package com.example.CandidatosTSE.controller;


import com.example.CandidatosTSE.service.CandidatosTseService;
import com.example.CandidatosTSE.model.Candidato;
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


        String cargoSelecionado = (cargo != null) ? cargo : "";
        String partidoSelecionado = (partido != null) ? partido : "";
        String textoSelecionado = (texto != null) ? texto : "";


        List<Candidato> listaFiltrada = candidatosTseService.filtrar(cargoSelecionado, partidoSelecionado, textoSelecionado);
        int totalEncontrado = listaFiltrada.size();


        model.addAttribute("candidatos", listaFiltrada);
        model.addAttribute("totalEncontrado", totalEncontrado);


        model.addAttribute("cargos", candidatosTseService.listarCargos());
        model.addAttribute("partidos", candidatosTseService.listarPartidos());


        model.addAttribute("cargoSelecionado", cargoSelecionado);
        model.addAttribute("partidoSelecionado", partidoSelecionado);
        model.addAttribute("textoSelecionado", textoSelecionado);


        return "index";
    }
}
