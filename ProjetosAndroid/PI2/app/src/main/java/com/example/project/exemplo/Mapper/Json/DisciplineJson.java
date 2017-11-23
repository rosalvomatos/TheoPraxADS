package com.example.project.exemplo.Mapper.Json;

import java.io.Serializable;

public class DisciplineJson implements Serializable {
    int CH;
    String Nome;
    String Codigo;

    public int getCH() {
        return CH;
    }

    public void setCH(int CH) {
        this.CH = CH;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }
}
