package web.itmo.lab2.retriever;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonRetriever {
    public static JsonObject getJson(BufferedReader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        reader.close();

        String requestData = stringBuilder.toString();
        Gson gson = new Gson();

        return gson.fromJson(requestData, JsonObject.class);
    }
}