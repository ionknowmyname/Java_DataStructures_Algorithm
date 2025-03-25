//package com.faithfulolaleru.Miscellaneous;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.codehaus.jackson.JsonNode;
//import org.codehaus.jackson.map.ObjectMapper;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
public class CallAPI {
//
//    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(CallAPI.class.getName());
//
//    private static HttpURLConnection conn;
//
//    public static void main(String[] args) {
//
//        BufferedReader reader;
//        String line;
//        StringBuilder responseContent = new StringBuilder();
//        try{
//            // https://jsonmock.hackerrank.com/api/articles
//            URL url = new URL("https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=Gi");
//            conn = (HttpURLConnection) url.openConnection();
//
//            // Request setup
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
//            conn.setReadTimeout(5000);
//            conn.addRequestProperty("Content-Type", "application/json");
//
//            // Test if the response from the server is successful
//            int status = conn.getResponseCode();
//
//            if (status >= 300) {
//                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//                while ((line = reader.readLine()) != null) {
//                    responseContent.append(line);
//                }
//                reader.close();
//            }
//            else {
//                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                while ((line = reader.readLine()) != null) {
//                    responseContent.append(line);
//                }
//                reader.close();
//            }
//            log.info("response code: " + status);
//            System.out.println(responseContent);
//        }
//        catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            conn.disconnect();
//        }
//
//
//        // parseData(responseContent);
//        parseData2(responseContent);
//
//
//    }
//
//    // using gson
//    private static String parseData(StringBuilder responseContent) {
//
//        String title = "";
//
//        // Parse the JSON response
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObject = parser.parse(responseContent.toString()).getAsJsonObject();
//
//        // Extract the "data" field as an array
//        JsonArray dataArray = jsonObject.getAsJsonArray("data");
//
//        // Check if the array is not empty
//        if (dataArray.size() > 0) {
//            // Get the first object in the array
//            JsonObject firstObject = dataArray.get(0).getAsJsonObject();
//
//            // Extract the "title" field from the first object
//            title = firstObject.get("Title").getAsString();
//            System.out.println(title);
//        }
//
//        // Extract the "data" field and then the "title" field
//       //  JsonObject dataObject = jsonObject.getAsJsonObject("data");
//
//        return title;
//    }
//
//    // using jackson library
//    private static String parseData2(StringBuilder responseContent) {
//
//        // Parse JSON string to JsonNode
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = null;
//        try {
//            jsonNode = objectMapper.readTree(responseContent.toString());
//        } catch (Exception ex) {
//
//        }
//
//
//        // Access JSON properties
//        JsonNode data = jsonNode.get("data");
//
//
//        String title = data.get(0).get("Title").getTextValue();
//
//        return title;
//    }
//
//    private String returnURLwithQuery(String query) {
//        String baseUrl = "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=";
//        return baseUrl + query;
//    }
}
