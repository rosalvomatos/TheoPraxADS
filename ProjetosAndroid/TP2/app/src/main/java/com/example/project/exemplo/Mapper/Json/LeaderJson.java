package com.example.project.exemplo.Mapper.Json;

public class LeaderJson {
    String Chave;
    String Valor;

    public LeaderJson() {
    }

    public LeaderJson(String chave, String valor) {
        Chave = chave;
        Valor = valor;
    }

    public String getChave() {
        return Chave;
    }

    public void setChave(String chave) {
        Chave = chave;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
}
