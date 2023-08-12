package com.faithfulolaleru.Miscellaneous;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallAPI3 {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(CallAPI.class.getName());

    /*
    *   HACKERRANK: REST API - Number of Drawn Matches
    *
    *   https://www.youtube.com/watch?v=0br3nIJUS14
    *
    * */



    public static int getNumDraws(int year) throws IOException {
        final String endpoint = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year;
        final int MaxScore = 10;
        int totalNumDraws = 0;

        for (int score = 0; score <= MaxScore; score++) {
            totalNumDraws = getTotalNumDraws(String.format(endpoint + "&team1goals=%d&team2goals=%d", score, score));
        }

        return totalNumDraws;
    }

    private static int getTotalNumDraws(String request) throws IOException {
        URL url = new URL(request);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.addRequestProperty("Content-Type", "application/json");

        int status = conn.getResponseCode();

        InputStream in = (status < 200 || status > 299) ? conn.getErrorStream() : conn.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String responseLine;
        StringBuffer responseContent = new StringBuffer();

        while ((responseLine = reader.readLine()) != null) {
            responseContent.append(responseLine);
        }
        reader.close();
        conn.disconnect();

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        String script = "var obj = JSON.parse('" + responseContent.toString() + "'); ";
        script += "var total = obj.total; ";

        try {
            engine.eval(script);
        } catch(ScriptException ex) {
            ex.printStackTrace();
        }

        if(engine.get("total") == null) {
            throw new RuntimeException("Cannot retrieve data from the server");
        }

        return (int) engine.get("total");
    }

}
