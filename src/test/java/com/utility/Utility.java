package com.utility;

import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

public class Utility {

    public static Date convertStringToDate(String dateStr, String parsePattern) {
        Date date = null;

        if(StringUtils.isEmpty(dateStr))
            return date;

        try {
            date = DateUtils.parseDate(dateStr, parsePattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Object[] getData(String testName, String dataFilePath) throws IOException {

        //Read json file data using Gson library
        BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
        JsonElement jsonElement = new JsonParser().parse(br);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        //Get test data for the specific test case
        JsonArray jsonArray = jsonObject.getAsJsonArray(testName);
        Object[] data = jsonArrayToObjectArray(jsonArray);
        return data;
    }

    public static Object[] jsonArrayToObjectArray(JsonArray jsonArray) {

        Object[] data = null;
        Gson gson = new Gson();

        if (jsonArray.size() > 0) {
            data = new Object[jsonArray.size()];
            for (int i = 0; i<jsonArray.size(); i++) {
                HashMap<String, String> hashmap = new HashMap<>();
                data[i] = gson.fromJson((JsonElement) jsonArray.get(i), hashmap.getClass());
            }
        }
        return data;
    }

}
