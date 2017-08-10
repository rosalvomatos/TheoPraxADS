package com.example.project.exemplo.mapeamento;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by lucas on 10/10/2016.
 */

public class TbDisciplina  implements Serializable {
    private String disciplina_codigo;
    private String disciplina_nome;
    private BigDecimal disciplina_cargahoraria;
    private int subelemento_elemento_id;

    public TbDisciplina(String disciplina_codigo, String disciplina_nome, BigDecimal disciplina_cargahoraria) {
        this.disciplina_codigo = disciplina_codigo;
        this.disciplina_nome = disciplina_nome;
        this.disciplina_cargahoraria = disciplina_cargahoraria;
    }

    public String getDisciplina_nome() {
        return disciplina_nome;
    }

    public void setDisciplina_nome(String disciplina_nome) {
        this.disciplina_nome = disciplina_nome;
    }

    public BigDecimal getDisciplina_cargahoraria() {
        return disciplina_cargahoraria;
    }

    public void setDisciplina_cargahoraria(BigDecimal disciplina_cargahoraria) {
        this.disciplina_cargahoraria = disciplina_cargahoraria;
    }

    public int getSubelemento_elemento_id() {
        return subelemento_elemento_id;
    }

    public void setSubelemento_elemento_id(int subelemento_elemento_id) {
        this.subelemento_elemento_id = subelemento_elemento_id;
    }

    public String getDisciplina_codigo() {
        return disciplina_codigo;
    }

    public void setDisciplina_codigo(String disciplina_codigo) {
        this.disciplina_codigo = disciplina_codigo;
    }
}
