package com.example.trickypreguntas;

public class Pregunta {
    String enunciado;
    String[] opciones = {"", "", ""};
    int nOpcCorrecta, dificultad;
    boolean respondida;

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }

    public int getnOpcCorrecta() {
        return nOpcCorrecta;
    }

    public void setnOpcCorrecta(int nOpcCorrecta) {
        this.nOpcCorrecta = nOpcCorrecta;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public boolean isRespondida() {
        return respondida;
    }

    public void setRespondida(boolean respondida) {
        this.respondida = respondida;
    }
}
