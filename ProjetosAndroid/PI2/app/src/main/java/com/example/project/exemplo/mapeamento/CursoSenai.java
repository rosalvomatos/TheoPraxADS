package com.example.project.exemplo.mapeamento;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by lucas on 05/11/2016.
 */

public class CursoSenai implements Serializable{
    int COD_UNIDADE ;
    String UNIDADE;
    String CODMODALIDADECURSO;
    String MODALIDADE;
    String CODCURSO;
    String CURSO;
    String CODGRADE;
    String STATUS_MATRIZ;
    int IDHABILITACAOFILIAL;
    int CODPERIODO;
    String CODDISC;
    String DISCIPLINA;
    BigDecimal CH;
    String CODPROF;
    String PROFESSOR;
    String REGIME_TRAB;

    public int getCOD_UNIDADE() {
        return COD_UNIDADE;
    }

    public void setCOD_UNIDADE(int COD_UNIDADE) {
        this.COD_UNIDADE = COD_UNIDADE;
    }

    public String getUNIDADE() {
        return UNIDADE;
    }

    public void setUNIDADE(String UNIDADE) {
        this.UNIDADE = UNIDADE;
    }

    public String getCODMODALIDADECURSO() {
        return CODMODALIDADECURSO;
    }

    public void setCODMODALIDADECURSO(String CODMODALIDADECURSO) {
        this.CODMODALIDADECURSO = CODMODALIDADECURSO;
    }

    public String getMODALIDADE() {
        return MODALIDADE;
    }

    public void setMODALIDADE(String MODALIDADE) {
        this.MODALIDADE = MODALIDADE;
    }

    public String getCODCURSO() {
        return CODCURSO;
    }

    public void setCODCURSO(String CODCURSO) {
        this.CODCURSO = CODCURSO;
    }

    public String getCURSO() {
        return CURSO;
    }

    public void setCURSO(String CURSO) {
        this.CURSO = CURSO;
    }

    public String getCODGRADE() {
        return CODGRADE;
    }

    public void setCODGRADE(String CODGRADE) {
        this.CODGRADE = CODGRADE;
    }

    public String getSTATUS_MATRIZ() {
        return STATUS_MATRIZ;
    }

    public void setSTATUS_MATRIZ(String STATUS_MATRIZ) {
        this.STATUS_MATRIZ = STATUS_MATRIZ;
    }

    public int getIDHABILITACAOFILIAL() {
        return IDHABILITACAOFILIAL;
    }

    public void setIDHABILITACAOFILIAL(int IDHABILITACAOFILIAL) {
        this.IDHABILITACAOFILIAL = IDHABILITACAOFILIAL;
    }

    public int getCODPERIODO() {
        return CODPERIODO;
    }

    public void setCODPERIODO(int CODPERIODO) {
        this.CODPERIODO = CODPERIODO;
    }

    public String getCODDISC() {
        return CODDISC;
    }

    public void setCODDISC(String CODDISC) {
        this.CODDISC = CODDISC;
    }

    public String getDISCIPLINA() {
        return DISCIPLINA;
    }

    public void setDISCIPLINA(String DISCIPLINA) {
        this.DISCIPLINA = DISCIPLINA;
    }

    public BigDecimal getCH() {
        return CH;
    }

    public void setCH(BigDecimal CH) {
        this.CH = CH;
    }

    public String getCODPROF() {
        return CODPROF;
    }

    public void setCODPROF(String CODPROF) {
        this.CODPROF = CODPROF;
    }

    public String getPROFESSOR() {
        return PROFESSOR;
    }

    public void setPROFESSOR(String PROFESSOR) {
        this.PROFESSOR = PROFESSOR;
    }

    public String getREGIME_TRAB() {
        return REGIME_TRAB;
    }

    public void setREGIME_TRAB(String REGIME_TRAB) {
        this.REGIME_TRAB = REGIME_TRAB;
    }


}
