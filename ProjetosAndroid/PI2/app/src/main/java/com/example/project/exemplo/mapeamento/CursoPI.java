package com.example.project.exemplo.mapeamento;

import java.io.Serializable;



public class CursoPI implements Serializable {
    int cursoAvaliacaoMec;
    String cursoCategoria;
    String cursoCodigo;
    String cursoCoordenador;
    String cursoDescricao;
    String cursoDuracao;
    int cursoId;
    String cursoModalidade;
    String cursoNome;
    String cursoStatus;
    String cursoTurno;


    public int getCursoAvaliacaoMec() {
        return cursoAvaliacaoMec;
    }

    public void setCursoAvaliacaoMec(int cursoAvaliacaoMec) {
        this.cursoAvaliacaoMec = cursoAvaliacaoMec;
    }

    public String getCursoCategoria() {
        return cursoCategoria;
    }

    public void setCursoCategoria(String cursoCategoria) {
        this.cursoCategoria = cursoCategoria;
    }

    public String getCursoCodigo() {
        return cursoCodigo;
    }

    public void setCursoCodigo(String cursoCodigo) {
        this.cursoCodigo = cursoCodigo;
    }

    public String getCursoCoordenador() {
        return cursoCoordenador;
    }

    public void setCursoCoordenador(String cursoCoordenador) {
        this.cursoCoordenador = cursoCoordenador;
    }

    public String getCursoDescricao() {
        return cursoDescricao;
    }

    public void setCursoDescricao(String cursoDescricao) {
        this.cursoDescricao = cursoDescricao;
    }

    public String getCursoDuracao() {
        return cursoDuracao;
    }

    public void setCursoDuracao(String cursoDuracao) {
        this.cursoDuracao = cursoDuracao;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoModalidade() {
        return cursoModalidade;
    }

    public void setCursoModalidade(String cursoModalidade) {
        this.cursoModalidade = cursoModalidade;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public String getCursoStatus() {
        return cursoStatus;
    }

    public void setCursoStatus(String cursoStatus) {
        this.cursoStatus = cursoStatus;
    }

    public String getCursoTurno() {
        return cursoTurno;
    }

    public void setCursoTurno(String cursoTurno) {
        this.cursoTurno = cursoTurno;
    }
}
