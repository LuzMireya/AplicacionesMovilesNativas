package com.ramitas.cerraduras_api.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CerraduraService {

    // Generador de cadenas para la cerradura de Kleene
    public Set<String> calcularCerraduraKleene(int n) {
        Set<String> resultado = new HashSet<>();
        resultado.add(""); // Añadir la cadena vacía

        // Generar cadenas de longitud 1 a n
        for (int i = 1; i <= n; i++) {
            generarCadenas("", i, resultado);
        }
        return resultado;
    }

    // Generador de cadenas para la cerradura positiva
    public Set<String> calcularCerraduraPositiva(int n) {
        Set<String> resultado = new HashSet<>();

        // Generar cadenas de longitud 1 a n
        for (int i = 1; i <= n; i++) {
            generarCadenas("", i, resultado);
        }
        return resultado;
    }

    // Método auxiliar para generar cadenas
    private void generarCadenas(String prefix, int longitud, Set<String> resultado) {
        if (longitud == 0) {
            resultado.add(prefix);
            return;
        }

        // Aquí puedes definir el conjunto de caracteres que deseas utilizar
        String caracteres = "01"; // Por ejemplo, cadenas binarias

        for (char c : caracteres.toCharArray()) {
            generarCadenas(prefix + c, longitud - 1, resultado);
        }
    }
}
