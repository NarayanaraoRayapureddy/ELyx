package com.zemosolabs.myplaces;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreference {


    private static final String PLACES = "Places";
    private static final String PREFS_NAME = "MY_PLACES";

    public SharedPreference(){
        super();
    }

    public void storePlaces(Context context, ArrayList<String> placesList){

        Log.i("SharedPreferences", "StorePlaces");
        SharedPreferences sharedPref;
        SharedPreferences.Editor editor;


        sharedPref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        Log.i("SharedPreferences", "StorePlaces1");
        editor = sharedPref.edit();
        Gson gson = new Gson();
        String jsonPlaces = gson.toJson(placesList);
        Log.i("SharedPreferences", "StorePlaces2");
        editor.putString(PLACES,jsonPlaces);
        Log.i("SharedPreferences", "StorePlaces3");
        editor.commit();
        Log.i("SharedPreferences", "StorePlaces4");
    }

    public ArrayList<String> loadPlaces(Context context) {

        Log.i("SharedPreferences", "loadPlaces");
        SharedPreferences sharedPrefs;
        ArrayList<String> placesList;
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Log.i("SharedPreferences", "loadPlaces1");
        if (sharedPrefs.contains(PLACES)) {
            String jsonPlaces = sharedPrefs.getString(PLACES, null);
            Gson gson = new Gson();
            Log.i("SharedPreferences", "loadPlaces2");
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            Log.i("SharedPreferences", "loadPlaces3");
            placesList = gson.fromJson(jsonPlaces, type);
            Log.i("SharedPreferences", "loadPlaces4");
        }
        else
            return null;
        Log.i("SharedPreferences", "loadPlaces5");
        return placesList;

    }

    public void addPlace(Context context, String place){

        Log.i("SharedPreferences", "addPlaces");
        ArrayList<String> placesList = loadPlaces(context);
        Log.i("SharedPreferences", "addPlaces1");
        if(placesList ==null){
            placesList = new ArrayList<String>();
        }
        Log.i("SharedPreferences", "addPlaces2");
        placesList.add(place);
        storePlaces(context,placesList);
        Log.i("SharedPreferences", "addPlaces3");
    }

    public void removePlace(Context context, String place){

        Log.i("SharedPreferences", "removePlaces");
        ArrayList<String> placesList = loadPlaces(context);
        Log.i("SharedPreferences", "removePlaces1");
        if(placesList !=null) {
            placesList.remove(place);
            Log.i("SharedPreferences", "removePlaces2");
            storePlaces(context, placesList);
            Log.i("SharedPreferences", "removePlaces3");
        }
    }

    public void removeAll(Context context,ArrayList<String> placesList){

        Log.i("SharedPreferences", "removeall");
        placesList = loadPlaces(context);
        Log.i("SharedPreferences", "removeall1");
        placesList.clear();
            storePlaces(context, placesList);
            Log.i("SharedPreferences", "removeall3");

    }
}
