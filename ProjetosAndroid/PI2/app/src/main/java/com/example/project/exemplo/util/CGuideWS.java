package com.example.project.exemplo.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.project.exemplo.Mapper.Json.DisciplineJson;
import com.example.project.exemplo.mapeamento.CursoPI;
import com.example.project.exemplo.mapeamento.CursoSenai;
import com.example.project.exemplo.Mapper.Json.CourseJson;
import com.example.project.exemplo.mapeamento.TbDisciplina;
import com.example.project.exemplo.mapeamento.TbDocente;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Project on 22/04/2016.
 */
public class CGuideWS {
    private static String base_ws = "http://200.9.65.20:8080/WS_hibernate_pd/webresources/";
    private static String base_ws_pdf = "http://200.9.65.20:8080/WS_hibernate_pdSenai/webresources/";
    private static String base_ws_senai = "http://senaiweb2.fieb.org.br/mec/api/";
    private static String base_ws_test = "http://ws-tp.apphb.com/api/";
    private Context context;
    static List<CursoSenai> cursosSenai;

    public CGuideWS(Context context) {
        this.context = context;

    }

    private static HttpURLConnection abrirConexao(String url, String metodo, boolean saida) {
        try {
            URL urlConexao = new URL(url);
            HttpURLConnection conexao = (HttpURLConnection) urlConexao.openConnection();
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.setRequestMethod(metodo);
            conexao.setDoInput(true);
            conexao.setDoOutput(saida);
            if (saida) {
                conexao.addRequestProperty("Content-Type", "application/json");
            }
            conexao.connect();
            return conexao;
        } catch (Exception e) {
            return null;
        }
    }

    private static String streamToString(InputStream is) throws Exception {
        byte[] bytes = new byte[9999999];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

    private static List<CursoPI> obterCursosWS() {
        try {
            String urlsessao = base_ws + "cursows";
            HttpURLConnection conn = abrirConexao(urlsessao, "GET", false);
            List<CursoPI> lista = new ArrayList<CursoPI>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<CursoPI>>() {
                }.getType();
                lista = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    private static List<CursoSenai> obterCursosSenai() {
        try {
            String urlsessao = base_ws_senai + "cursos";
            HttpURLConnection conn = abrirConexao(urlsessao, "GET", false);
            List<CursoSenai> lista = new ArrayList<CursoSenai>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<CursoSenai>>() {
                }.getType();
                lista = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public static Intent abrirArquivo(String url) {
        String caminnhoArq = base_ws_pdf + url;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(caminnhoArq));
        return intent;
    }

    public static List<CursoPI> obterCursos() {
        List<CursoPI> cursosPI = obterCursosWS();
        cursosSenai = obterCursosSenai();
        return cursosPI;

    }

    public static List<TbDisciplina> obterDisciplinasCursos(String codCurso) {
        List<CursoSenai> cursosSN = cursosSenai;
        List<TbDisciplina> disciplinas = new ArrayList<TbDisciplina>();
        for (CursoSenai c : cursosSN) {
            if (c.getCODCURSO().equalsIgnoreCase(codCurso)) {
                if (!verificarExistenciaDeDisciplinaDoCurso(disciplinas, c.getCODDISC())) {
                    disciplinas.add(new TbDisciplina(c.getCODDISC(), c.getDISCIPLINA(), c.getCH()));
                }
            }
        }
        return disciplinas;

    }


    public static List<TbDisciplina> obterDisciplinasDocente(String codDocente) {
        List<CursoSenai> cursosSN = cursosSenai;
        List<TbDisciplina> disciplinas = new ArrayList<TbDisciplina>();
        for (CursoSenai c : cursosSN) {
            if (c.getCODPROF().equalsIgnoreCase(codDocente)) {
                if (!verificarExistenciaDeDisciplinaDoCurso(disciplinas, c.getCODDISC())) {
                    disciplinas.add(new TbDisciplina(c.getCODDISC(), c.getDISCIPLINA(), c.getCH()));
                }
            }
        }
        return disciplinas;

    }

    public static List<TbDocente> obterDocentesDisciplina(String codDisciplina) {
        List<CursoSenai> cursosSN = cursosSenai;
        List<TbDocente> docentes = new ArrayList<TbDocente>();
        for (CursoSenai c : cursosSN) {
            if (c.getCODDISC().equalsIgnoreCase(codDisciplina)) {
                if (!verificarExistenciaDeDocenteDoCurso(docentes, c.getCODPROF())) {
                    docentes.add(new TbDocente(c.getCODPROF(), c.getPROFESSOR(), c.getREGIME_TRAB()));
                }
            }
        }
        return docentes;

    }

    public static List<TbDocente> obterDocentesCursos(String codCurso) {
        List<CursoSenai> cursosSN = cursosSenai;
        List<TbDocente> docentes = new ArrayList<TbDocente>();
        for (CursoSenai c : cursosSN) {
            if (c.getCODCURSO().equalsIgnoreCase(codCurso)) {
                if (!verificarExistenciaDeDocenteDoCurso(docentes, c.getCODPROF())) {
                    docentes.add(new TbDocente(c.getCODPROF(), c.getPROFESSOR(), c.getREGIME_TRAB()));
                }
            }
        }
        return docentes;

    }

    private static boolean verificarExistenciaDeDocenteDoCurso(List<TbDocente> lista, String codDoc) {
        boolean encontrou = false;

        for (TbDocente c : lista) {
            if (c.getDocente_codigo().equalsIgnoreCase(codDoc)) {
                encontrou = true;
                break;
            }
        }
        return encontrou;
    }

    private static boolean verificarExistenciaDeDisciplinaDoCurso(List<TbDisciplina> lista, String codDisc) {
        boolean encontrou = false;

        for (TbDisciplina c : lista) {
            if (c.getDisciplina_codigo().equalsIgnoreCase(codDisc)) {
                encontrou = true;
                break;
            }
        }
        return encontrou;
    }


    //TEST URL COURSE LAPA'S WEB API
    public static List<CourseJson> getCourse(int typeCourse) {
        try {
            String partUrl = "Curso/";
            switch (typeCourse) {
                case 1:
                    partUrl += "GetGrad/";
                    break;
                case 2:
                    partUrl += "GetPosGrad/";
                    break;
            }
            String fullPath = base_ws_test + partUrl;
            HttpURLConnection conn = abrirConexao(fullPath, "GET", false);
            List<CourseJson> lista = new ArrayList<>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<CourseJson>>() {
                }.getType();
                lista = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    //TEST URL DISCIPLINE BY COURSE LAPA'S WEB API
    public static List<DisciplineJson> getDiscipline(int typeSearch, int refferId) {
        try {
            String partUrl = "Disciplina/";
            switch (typeSearch) {
                case 1:
                    partUrl += "GetByCurso";
                    break;
                case 2:
                    partUrl += "GetByProfessor";
                    break;
            }
            partUrl +="?id=" + Integer.toString(refferId);
            String fullPath = base_ws_test + partUrl;
            HttpURLConnection conn = abrirConexao(fullPath, "GET", false);
            List<DisciplineJson> lista = new ArrayList<>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<DisciplineJson>>() {
                }.getType();
                lista = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}