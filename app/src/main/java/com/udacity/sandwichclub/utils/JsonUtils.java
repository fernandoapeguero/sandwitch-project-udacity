package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonMainObject;
        String mainName  = "";
        String placeOfOrigin= "" ;
        String description = "";
        String image = "";
        List<String> ingreString= new ArrayList<>();
        List<String> alsoknows = new ArrayList<>();
        try{
            jsonMainObject = new JSONObject(json);
            JSONObject nameObject = jsonMainObject.getJSONObject("name");
            mainName = nameObject.getString("mainName");
            placeOfOrigin = jsonMainObject.getString("placeOfOrigin");
            description = jsonMainObject.getString("description");
            image = jsonMainObject.getString("image");
            JSONArray knownsArray =  nameObject.getJSONArray("alsoKnownAs");

            for(int i =0; i < knownsArray.length() ; i++){
                alsoknows.add(knownsArray.get(i).toString());
            }


             JSONArray ingredientsArray = jsonMainObject.getJSONArray("ingredients");
              for(int i =0; i < ingredientsArray.length() ; i++){
                ingreString.add(ingredientsArray.get(i).toString());
              }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName , alsoknows , placeOfOrigin , description , image , ingreString);
    }





}
