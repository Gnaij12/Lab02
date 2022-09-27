package com.example.lab02;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LifecycleData {
    int onCreate = 0;
    int onStart = 0;
    int onResume = 0;
    int onPause = 0;
    int onStop = 0;
    int onRestart = 0;
    int onDestroy = 0;
    String duration;
    public String toString() {
        return duration + "\n" +
                "Made me! \t\t\t\t" + onCreate + "\n" +
                "Started again \t" + onStart + "\n" +
                "Came back \t\t\t" + onResume + "\n" +
                "Other came \t\t\t" + onPause + "\n" +
                "Went away \t\t\t" + onStop + "\n" +
                "Restarted \t\t\t\t" + onRestart + "\n" +
                "Deleted \t\t\t\t\t\t" + onDestroy;
    }
    //convert instance to string
    String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this,LifecycleData.class);
    }
    //from JSON String to class using GSON
    static LifecycleData parseJSON(String fromSharedPreferences) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(fromSharedPreferences,LifecycleData.class);
    }

    public void updateEvent(String currentEnclosingMethod) {
        switch (currentEnclosingMethod) {
            case "onCreate":
                onCreate++;
                break;
            case "onStart":
                onStart++;
                break;
            case "onResume":
                onResume++;
                break;
            case "onPause":
                onPause++;
                break;
            case "onStop":
                onStop++;
                break;
            case "onRestart":
                onRestart++;
                break;
            case "onDestroy":
                onDestroy++;
                break;
            default:break;
        }
    }
}
