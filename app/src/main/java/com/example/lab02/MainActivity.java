package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

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
    }

    public void decrement(View view) {
//        System.out.println("decrementing: " + (count-=multiplier));
//        greetingDisplay.setText(getString(R.string.likes_count,count));
        if (leftRadBut.isChecked())
            if (--count <0) count=back_images.length-1;
        background.setImageResource(back_images[count]);
    }
    public void random(View view) {
        count = (int) (Math.random()*20-10)*multiplier;
        System.out.println("randomizing: " + count);
        greetingDisplay.setText(getString(R.string.likes_count,count));
    }
    public void ten(View view) {
        multiplier = 10;
        System.out.println("Multiplier is now " + multiplier);
    }
    public void one(View view) {
        multiplier = 1;
        System.out.println("Multiplier is now " + multiplier);
    }


    public void putText(View view) {
        displayText.setText(inputText.getText());
    }
}