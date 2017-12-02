package com.example.project.exemplo.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.project.exemplo.Mapper.Json.DisciplineJson;
import com.example.project.exemplo.Mapper.Json.LeaderJson;
import com.example.project.exemplo.Mapper.Json.TeacherJson;
import com.example.project.exemplo.Mapper.Json.CourseJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CGuideWS {
    //private static String base_ws = "http://200.9.65.20:8080/WS_hibernate_pd/webresources/";
    private static String base_ws_pdf = "http://200.9.65.20:8080/WS_hibernate_pdSenai/webresources/";
    //private static String base_ws_senai = "http://senaiweb2.fieb.org.br/mec/api/";
//    private static String base_ws = "http://ws-tp.apphb.com/api/";
    private static String base_ws = "http://snp305-030/CimatecAPI/api/";
    public static String newline = System.getProperty("line.separator");

    private Context context;

    public CGuideWS(Context context) {
        this.context = context;
    }

    private static HttpURLConnection openConnection(String url,
                                                    String method,
                                                    boolean result) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setReadTimeout(15000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod(method);
            connection.setDoInput(true);
            connection.setDoOutput(result);
            if (result) {
                connection.addRequestProperty("Content-Type", "application/json");
            }
            connection.connect();
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    private static String streamToString(InputStream is) throws Exception {
        byte[] bytes = new byte[9999999];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read;
        while ((read = is.read(bytes)) > 0) {
            baos.write(bytes, 0, read);
        }
        return new String(baos.toByteArray());
    }

    public static Intent openFile(String url) {
        String filePath = base_ws_pdf + url;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(filePath));
        return intent;
    }

    //URL COURSE LAPA'S WEB API
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
            String fullPath = base_ws + partUrl;
            HttpURLConnection conn = openConnection(fullPath, "GET", false);
            List<CourseJson> list = new ArrayList<>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<CourseJson>>() {
                }.getType();
                list = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    //URL DISCIPLINE LAPA'S WEB API
    public static List<DisciplineJson> getDiscipline(int typeSearch, String refferId) {
        try {
            String partUrl = "Disciplina/";
            switch (typeSearch) {
                case 1:
                    partUrl += "GetByCurso?codCurso=";
                    break;
                case 2:
                    partUrl += "GetByProfessor?codProf=";
                    break;
            }
            partUrl += refferId;
            String fullPath = base_ws + partUrl;
            HttpURLConnection conn = openConnection(fullPath, "GET", false);
            List<DisciplineJson> list = new ArrayList<>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<DisciplineJson>>() {
                }.getType();
                list = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    //URL TEACHER LAPA'S WEB API
    public static List<TeacherJson> getTeacher(int typeSearch, String refferId) {
        try {
            String partUrl = "Professor/";
            switch (typeSearch) {
                case 1:
                    partUrl += "GetByCurso?codCurso=";
                    break;
                case 2:
                    partUrl += "GetByDisciplina?codDisc=";
                    break;
            }
            partUrl += refferId;
            String fullPath = base_ws + partUrl;
            HttpURLConnection conn = openConnection(fullPath, "GET", false);
            List<TeacherJson> list = new ArrayList<>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<TeacherJson>>() {
                }.getType();
                list = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<LeaderJson> getLeader() {
        try {
            //REMOVE AFTER TEST COMPLETE
//            base_ws = "http://tp-ws.somee.com/api/";
            String partUrl = "ConfigDirigente/";

            String fullPath = base_ws + partUrl;
            HttpURLConnection conn = openConnection(fullPath, "GET", false);
            List<LeaderJson> list = new ArrayList<>();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String s = streamToString(is);
                is.close();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<LeaderJson>>() {
                }.getType();
                list = gson.fromJson(s, collectionType);
            }
            conn.disconnect();
            List<LeaderJson> listTemp = CGuideWS.GroupLeaderList(list);
            return listTemp;
        } catch (Exception e) {
            return null;
        }
    }

    private static List<LeaderJson> GroupLeaderList(List<LeaderJson> leaderJsonList) {
        List<LeaderJson> leaderJsonResult = new ArrayList<>();
        for (LeaderJson leader : leaderJsonList) {
            List<LeaderJson> leaderJsonListTemp = CGuideWS.GetElementsByKey(leaderJsonList, leader.getChave());
            if (leaderJsonListTemp.size() > 0)
                leaderJsonResult.add(new LeaderJson(leader.getValor(), ConcatenateLeaderValueList(leaderJsonListTemp)));
        }
        return leaderJsonResult;
    }

    private static List<LeaderJson> GetElementsByKey(List<LeaderJson> leaderJsonList, String key) {
        List<LeaderJson> leaderJsonResult = new ArrayList<>();
        for (LeaderJson leader : leaderJsonList) {
            if (leader.getChave().contains(key) && !leader.getChave().equals(key))
                leaderJsonResult.add(leader);
        }
        return leaderJsonResult;
    }

    private static String ConcatenateLeaderValueList(List<LeaderJson> leaderJsonList) {
        String result = "";
        for (LeaderJson leader : leaderJsonList) {
            result += (leader.getValor() + newline);
        }
        return result;
    }
}