package com.example.project.exemplo.Mapper.Json;

import java.io.Serializable;

public class CourseJson implements Serializable {
    String Nome;
    String Codigo;
    String CH;
    String Coordenador;
    int NotaMEC;
    String Mensalidade;
    int Tipo;
    String Turno;
    String Modalidade;


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

    public String getCH() {
        return CH;
    }

    public void setCH(String CH) {
        this.CH = CH;
    }

    public String getCoordenador() {
        return Coordenador;
    }

    public void setCoordenador(String coordenador) {
        Coordenador = coordenador;
    }

    public int getNotaMEC() {
        return NotaMEC;
    }

    public void setNotaMEC(int notaMEC) {
        NotaMEC = notaMEC;
    }

    public String getMensalidade() {
        return Mensalidade;
    }

    public void setMensalidade(String mensalidade) {
        Mensalidade = mensalidade;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public String getTurno() {
        return Turno;
    }

    public void setTurno(String turno) {
        Turno = turno;
    }

    public String getModalidade() {
        return Modalidade;
    }

    public void setModalidade(String modalidade) {
        Modalidade = modalidade;
    }
}
