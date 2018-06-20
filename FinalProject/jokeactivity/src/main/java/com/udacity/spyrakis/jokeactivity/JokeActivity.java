package com.udacity.spyrakis.jokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "EXTRA_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeView = findViewById(R.id.joke_view);
        if (getIntent().hasExtra(EXTRA_JOKE)) {
            jokeView.setText(getIntent().getStringExtra(EXTRA_JOKE));
        }
    }
}
