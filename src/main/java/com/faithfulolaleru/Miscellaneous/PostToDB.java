package com.faithfulolaleru.Miscellaneous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostToDB {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(PostToDB.class.getName());

    // private static HttpURLConnection conn;

    public static void main(String[] args) throws IOException {

        sendPOST();
        System.out.println("POST DONE");

        sendPost2();
        System.out.println("POST2 DONE");

        // BOTH WORK
    }

    private static void sendPOST() throws IOException {
        URL url = new URL("https://reqres.in/api/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");

        // For POST only - START
        String requestBodyString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(requestBodyString.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code --> " + responseCode);

        if (responseCode >= HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request did not work.");
        }
    }

    private static void sendPost2() throws IOException {
        URL url = new URL("https://reqres.in/api/users");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);  // so we can write to output i.e give response

        String requestBodyString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBodyString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
