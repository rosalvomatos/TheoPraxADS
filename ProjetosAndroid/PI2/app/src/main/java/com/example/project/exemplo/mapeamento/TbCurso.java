package com.example.project.exemplo.mapeamento;

import java.io.Serializable;

/**
 * Created by lucas on 10/10/2016.
 */

public class TbCurso implements Serializable {
    private int curso_id;
    private String curso_codigo;
    private String curso_nome;
    private String curso_turno;
    private String curso_descriminacao;
    private String curso_modalidade;
    private int curso_avaliacao;
    private String curso_coordenador;
    private String curso_duracao;
    private float curso_mensalidade;
    private String curso_status;
    private String curso_categoria;

    public TbCurso(String curso_codigo, String curso_nome, String curso_modalidade, String curso_duracao) {
        this.curso_codigo = curso_codigo;
        this.curso_nome = curso_nome;
        this.curso_modalidade = curso_modalidade;
        this.curso_duracao = curso_duracao;
    }

    public int getCurso_id() {
        return curso_id;
    }

    public void setCurso_id(int curso_id) {
        this.curso_id = curso_id;
    }

    public String getCurso_nome() {
        return curso_nome;
    }

    public void setCurso_nome(String curso_nome) {
        this.curso_nome = curso_nome;
    }

    public String getCurso_turno() {
        return curso_turno;
    }

    public void setCurso_turno(String curso_turno) {
        this.curso_turno = curso_turno;
    }

    public String getCurso_descriminacao() {
        return curso_descriminacao;
    }

    public void setCurso_descriminacao(String curso_descriminacao) {
        this.curso_descriminacao = curso_descriminacao;
    }

    public String getCurso_modalidade() {
        return curso_modalidade;
    }

    public void setCurso_modalidade(String curso_modalidade) {
        this.curso_modalidade = curso_modalidade;
    }

    public int getCurso_avaliacao() {
        return curso_avaliacao;
    }

    public void setCurso_avaliacao(int curso_avaliacao) {
        this.curso_avaliacao = curso_avaliacao;
    }

    public String getCurso_coordenador() {
        return curso_coordenador;
    }

    public void setCurso_coordenador(String curso_coordenador) {
        this.curso_coordenador = curso_coordenador;
    }

    public String getCurso_duracao() {
        return curso_duracao;
    }

    public void setCurso_duracao(String curso_duracao) {
        this.curso_duracao = curso_duracao;
    }

    public float getCurso_mensalidade() {
        return curso_mensalidade;
    }

    public void setCurso_mensalidade(float curso_mensalidade) {
        this.curso_mensalidade = curso_mensalidade;
    }

    public String getCurso_status() {
        return curso_status;
    }

    public void setCurso_status(String curso_status) {
        this.curso_status = curso_status;
    }

    public String getCurso_categoria() {
        return curso_categoria;
    }

    public void setCurso_categoria(String curso_categoria) {
        this.curso_categoria = curso_categoria;
    }

    public String getCurso_codigo() {
        return curso_codigo;
    }

    public void setCurso_codigo(String curso_codigo) {
        this.curso_codigo = curso_codigo;
    }
}
