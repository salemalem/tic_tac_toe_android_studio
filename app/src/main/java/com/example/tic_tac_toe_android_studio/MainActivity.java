package com.example.tic_tac_toe_android_studio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000f);
        counter.setImageResource(R.drawable.yellow);

        counter.animate().translationYBy(1000f).rotation(1000).setDuration(900);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
