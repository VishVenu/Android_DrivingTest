package com.example.drivingtestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultScreen extends AppCompatActivity {

    TextView tvPoints, tvPersonalBest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);

        int score = getIntent().getExtras().getInt("score");
        tvPoints = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);
        tvPoints.setText("" + score);
        String remarks = getTestRemarks(score);
        tvPersonalBest.setText(remarks);
    }

    private String getTestRemarks(int score){
        switch (score){
            case 0:
            case 1:
            case 2:
                return "Please try again!";
            case 3:
                return "Good job!";
            case 4:
                return "Excellent work!";
            case 5:
                return "You are a genius!";
        }
        return "Please try again!";
    }

    public void restart(View view) {
        Intent intent = new Intent(ResultScreen.this, StartDrivingTest.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
