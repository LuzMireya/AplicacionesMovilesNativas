package com.ramitas.cerraduras_api.controller;

import com.ramitas.cerraduras_api.model.Cerradura;
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

    @PutMapping("/estrella/{n}")
    public ResponseEntity<Set<String>> getCerraduraEstrella(@PathVariable int n) {
        Set<String> resultado = cerraduraService.calcularCerraduraKleene(n);
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/positiva/{n}")
    public ResponseEntity<Set<String>> updateCerraduraPositiva(@PathVariable int n) {
        Set<String> resultado = cerraduraService.calcularCerraduraPositiva(n);
        return ResponseEntity.ok(resultado);
    }
}
