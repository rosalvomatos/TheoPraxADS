package com.example.project.exemplo.Mapper.Json;

import java.io.Serializable;

public class TeacherJson implements Serializable {
    String Nome;
    String ModContrato;
    String Codigo;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getModContrato() {
        return ModContrato;
    }

    public void setModContrato(String modContrato) {
        ModContrato = modContrato;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }
}
