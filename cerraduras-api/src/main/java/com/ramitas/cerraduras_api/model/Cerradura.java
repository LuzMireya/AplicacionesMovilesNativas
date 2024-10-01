package com.ramitas.cerraduras_api.model;

public class Cerradura {
    private String tipo;
    private String resultado;

    public Cerradura(String tipo, String resultado) {
        this.tipo = tipo;
        this.resultado = resultado;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
