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
        List<String> ingredientsList = new ArrayList<>();
        List<String> knownNameList = new ArrayList<>();
        try{
            jsonMainObject = new JSONObject(json);
            JSONObject nameObject = jsonMainObject.getJSONObject("name");
            mainName = nameObject.getString("mainName");
            placeOfOrigin = jsonMainObject.getString("placeOfOrigin");
            description = jsonMainObject.getString("description");
            image = jsonMainObject.getString("image");
            JSONArray knownsArray =  nameObject.getJSONArray("alsoKnownAs");

         if(knownsArray.length() > 0){
             for(int i =0; i < knownsArray.length() ; i++){
                 knownNameList.add(knownsArray.get(i).toString());
             }

         } else {
             knownNameList.add("Not available");
         }

             JSONArray ingredientsArray = jsonMainObject.getJSONArray("ingredients");
              for(int i =0; i < ingredientsArray.length() ; i++){
                ingredientsList.add(ingredientsArray.get(i).toString());
              }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(mainName , knownNameList , placeOfOrigin , description , image , ingredientsList);
    }





}
