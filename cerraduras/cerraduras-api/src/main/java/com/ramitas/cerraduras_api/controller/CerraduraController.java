package com.ramitas.cerraduras_api.controller;

import com.ramitas.cerraduras_api.service.CerraduraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/cerradura")
public class CerraduraController {

    private final CerraduraService cerraduraService;

    public CerraduraController(CerraduraService cerraduraService) {
        this.cerraduraService = cerraduraService;
    }

    @GetMapping("/estrella/{n}") // Cambiado a GET y se pasa 'n' como variable de ruta
    public ResponseEntity<Set<String>> getCerraduraEstrella(@PathVariable Integer n) {
        Set<String> resultado = cerraduraService.calcularCerraduraKleene(n);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/positiva/{n}") // Cambiado a GET y se pasa 'n' como variable de ruta
    public ResponseEntity<Set<String>> getCerraduraPositiva(@PathVariable Integer n) {
        Set<String> resultado = cerraduraService.calcularCerraduraPositiva(n);
        return ResponseEntity.ok(resultado);
    }
}


