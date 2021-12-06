package com.wellbeeing.wellbeeing.util;

import com.wellbeeing.wellbeeing.domain.sport.Exercise;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class DataFromApi {

    private static final String DEEP_API_KEY = "8e0083f3-c620-1b01-cfee-69485fa69b35:fx";
    private static final String DEEP_URL = "api-free.deepl.com/v2/translate?auth_key=";

    public static void main(String[] args) {
        try {
            getExercisesFromJsonWger();
        } catch (JSONException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void getExerciseDataRapid() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://exercisedb.p.rapidapi.com/exercises")
                .get()
                .addHeader("x-rapidapi-host", "exercisedb.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "953c6fdab8mshae68c788c3dc49cp1f6b55jsna8b878c43cdf")
                .build();

        Response response = client.newCall(request).execute();
        String responseString = "{ 'results':" + response.body().string() + "}";
        String fileName = "exerciseDB.json";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(responseString);

        writer.close();
//        try {
//            JSONObject responseObject = new JSONObject(responseString);
//            JSONArray bodyArray = (JSONArray) responseObject.get("results");
//            for (int i=0; i<bodyArray.length() && i<3; i++) { //i<30 remove
//                JSONObject exercise = bodyArray.getJSONObject(i);
//                String name = exercise.getString("name");
//                System.out.println("English: " + name);
//                System.out.print(" - Polski: " + translate(name));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }

    public static Map<String, Object> getExercisesFromJsonWger() throws JSONException, IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("wger.json"));

        // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;

        // A JSON array. JSONObject supports java.util.List interface.
        org.json.simple.JSONArray exercisesJson = (org.json.simple.JSONArray) jsonObject.get("results");
        Set<String> categories = new HashSet<>();
        // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
        // Iterators differ from enumerations in two ways:
        // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
        // 2. Method names have been improved.
        List<Map<String, Object>> resultMap = new ArrayList<>();
        List<Exercise> exercises = new ArrayList<>();
        for (org.json.simple.JSONObject object : (Iterable<org.json.simple.JSONObject>) exercisesJson) {
            String name = object.get("name").toString();
            String description = object.get("description").toString();
            Exercise ex = Exercise.builder().name(name).instruction(description).build();
            String l = ((org.json.simple.JSONObject) object.get("category")).get("name").toString();
            resultMap.add(new HashMap<String, Object>() {{
                put("Exercise", ex);
                put("Label", l);
            }});
            exercises.add(ex);
            categories.add(l);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("exercises", exercises);
        result.put("labels", categories);
        result.put("exerciseLabels", resultMap);
        return result;
    }


    public static void getExerciseDataWger() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://wger.de/api/v2/exerciseinfo/?language=2&limit=230")
                .get()
                .build();

        Response response = client.newCall(request).execute();
        String responseString = response.body().string();
        String fileName = "wger.json";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(responseString);

        writer.close();
//        try {
//            JSONObject responseObject = new JSONObject(responseString);
//            JSONArray bodyArray = (JSONArray) responseObject.get("results");
//            for (int i=0; i<bodyArray.length() && i<30; i++) { //i<30 remove
//                JSONObject exercise = bodyArray.getJSONObject(i);
//                String name = exercise.getString("name");
//                String description = exercise.getString("description");
//                String category = exercise.getJSONObject("category").getString("name");
//                JSONArray muscles = (JSONArray) exercise.get("muscles");
//                List<String> musclesList = new ArrayList<>();
//                for (int j=0; j<muscles.length(); j++) {
//                    musclesList.add(muscles.getJSONObject(j).getString("name"));
//                }
////                System.out.print("English: " + name + " - Polski: " + translate(name));
//                System.out.print("English name: " + name);
//                System.out.println("\n   - description: " + description + "\n   - category: " + category + "\n   - muscles:" + musclesList);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


    }

    public static String translate(String text) throws IOException {
        String result = "";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("text", text)
                .add("source_lang", "EN")
                .add("target_lang", "PL")
                .build();
        Request request = new Request.Builder()
                .url("https://" + DEEP_URL + DEEP_API_KEY)
                .post(body)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseString = response.body().string();
        try {
            JSONObject responseObject = new JSONObject(responseString);
            JSONArray bodyArray = (JSONArray) responseObject.get("translations");
            JSONObject translation = bodyArray.getJSONObject(0);
            result = translation.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
