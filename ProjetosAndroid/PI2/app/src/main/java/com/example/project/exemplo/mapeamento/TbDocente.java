package com.example.project.exemplo.mapeamento;

import java.io.Serializable;

/**
 * Created by Casa on 05/11/2016.
 */

public class TbDocente  implements Serializable {
    private String docente_codigo;
    private String docente_nome;
    private String docente_regime;

    public TbDocente(String docente_codigo,String docente_nome,String docente_regime) {
        this.docente_codigo = docente_codigo;
        this.docente_nome = docente_nome;
        this.docente_regime = docente_regime;
    }

    public String getDocente_codigo() {
        return docente_codigo;
    }

    public void setDocente_codigo(String docente_codigo) {
        this.docente_codigo = docente_codigo;
    }

    public String getDocente_nome() {
        return docente_nome;
    }

    public void setDocente_nome(String docente_nome) {
        this.docente_nome = docente_nome;
    }

    public String getDocente_regime() {
        return docente_regime;
    }

    public void setDocente_regime(String docente_regime) {
        this.docente_regime = docente_regime;
    }
}
