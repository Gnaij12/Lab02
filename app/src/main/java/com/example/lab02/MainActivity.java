package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button incrementButton;
    TextView greetingDisplay,displayText;
    EditText inputText;
    int count = 0;
    int multiplier = 1;
    String[] planets;
    RadioButton leftRadBut, rightRadBut;
    int[] back_images;
    ImageView background;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    LifecycleData lifeTime;
    TextView lifeTimeTV;
    AppCompatButton reset;
    String TAG = "com.example.lab02.sharedpreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incrementButton = findViewById(R.id.increment_button);
        greetingDisplay = findViewById(R.id.greeting_textview);
        planets = getResources().getStringArray(R.array.planets_array);
        leftRadBut = findViewById(R.id.radio_left);
        rightRadBut = findViewById(R.id.radio_right);
        displayText = findViewById(R.id.displayInputText);
        inputText = findViewById(R.id.inputText);
        back_images = new int[]{R.drawable.kirby1, R.drawable.kirby2,
                R.drawable.kirby3, R.drawable.kirby4};
        background = findViewById(R.id.background);
        background.setScaleType(ImageView.ScaleType.FIT_XY);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("incrementing: " + (count+=multiplier));
//                Log.i("incrementing",""+count);
//                greetingDisplay.setText(getString(R.string.likes_count,count));
                if (rightRadBut.isChecked())
                    count = ++count%back_images.length;
                background.setImageResource(back_images[count]);
            }
        });
        //load SharedPreferences
        sharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        //Instantiate editor
        editor = sharedPreferences.edit();
        //lifecycledata from SharedPreferences as String
        String lifecycleDataString = sharedPreferences.getString("lifetime","");
        //Instantiate a new LifecycleData if empty string
        //else convert JSON into LifecycleData oject
        if (lifecycleDataString.equals("")) {
            lifeTime = new LifecycleData();
            lifeTime.duration = "Lifetime";
        }else {
            lifeTime = LifecycleData.parseJSON(lifecycleDataString);
        }
        lifeTimeTV = findViewById(R.id.lifetime);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().apply();
                lifeTime = new LifecycleData();
                lifeTime.duration = "Lifetime";
                displayData();
            }
        });
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);

    }
    private void displayData() {
        lifeTimeTV.setText(lifeTime.toString());
    }

    //convert lifetime to String and store in SharedPreferences
    public void storeData(){
        editor.putString("lifetime",lifeTime.toJSON()).apply();
    }
    public void updateCount(String currentEnclosingMethod) {
        //pass name to LifecycleData to update count
        lifeTime.updateEvent(currentEnclosingMethod);
        displayData();
        storeData();
    }

    public void decrement(View view) {
//        System.out.println("decrementing: " + (count-=multiplier));
//        greetingDisplay.setText(getString(R.string.likes_count,count));
        if (leftRadBut.isChecked())
            if (--count <0) count=back_images.length-1;
        background.setImageResource(back_images[count]);
    }

    public void putText(View view) {
        displayText.setText(inputText.getText());
    }
    @Override
    protected void onStart() {
        super.onStart();
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.onstartsong);
        mediaPlayer.start();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onPause() {
        super.onPause();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onStop() {
        super.onStop();
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.onstopsong);
        mediaPlayer.start();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }
}