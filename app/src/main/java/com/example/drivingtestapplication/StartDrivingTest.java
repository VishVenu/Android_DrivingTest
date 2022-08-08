package com.example.drivingtestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartDrivingTest extends AppCompatActivity {

    ImageView ivShowImage;
    HashMap<String, Integer> map = new HashMap<>();
    ArrayList<String> roadSymbolList = new ArrayList<>();
    int index;
    Button btn1, btn2, btn3, btn4;
    TextView tvCount;
    int points;
    String userChoice;
    int questionNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_driving_test);

        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvCount = findViewById(R.id.tvPoints);
        index = 0;
        questionNumber = 1;
        roadSymbolList.add("Bicycles may use this road");
        roadSymbolList.add("No Bicycles allowed");
        roadSymbolList.add("Don't drive through intersection");
        roadSymbolList.add("No Entry");
        roadSymbolList.add("No Left Turn");
        roadSymbolList.add("No Parking");
        roadSymbolList.add("No pedestrians allowed");
        roadSymbolList.add("No standing");
        roadSymbolList.add("No U-turn");
        roadSymbolList.add("Snowmobiles may use this road");

        map.put(roadSymbolList.get(0), R.drawable.bicycle);
        map.put(roadSymbolList.get(1), R.drawable.no_bicycle);
        map.put(roadSymbolList.get(2), R.drawable.no_drive_thru);
        map.put(roadSymbolList.get(3), R.drawable.no_entry);
        map.put(roadSymbolList.get(4), R.drawable.no_left_turn);
        map.put(roadSymbolList.get(5), R.drawable.no_parking);
        map.put(roadSymbolList.get(6), R.drawable.no_pedestrians_allowed);
        map.put(roadSymbolList.get(7), R.drawable.no_standing);
        map.put(roadSymbolList.get(8), R.drawable.no_u_turn);
        map.put(roadSymbolList.get(9), R.drawable.snow_mobiles);

        Collections.shuffle(roadSymbolList);

        points = 0;

        startDrivingTest();
    }

    private void startDrivingTest() {
        tvCount.setText(questionNumber + " / 5");
        generateQuestions(index);
    }

    private void generateQuestions(int index) {
        ArrayList<String> roadSymbolListTemp = (ArrayList<String>) roadSymbolList.clone();
        String correctAnswer = roadSymbolList.get(index);
        roadSymbolListTemp.remove(correctAnswer);

        Collections.shuffle(roadSymbolListTemp);

        ArrayList<String> newList = new ArrayList<>();
        newList.add(roadSymbolListTemp.get(0));
        newList.add(roadSymbolListTemp.get(1));
        newList.add(roadSymbolListTemp.get(2));
        newList.add(correctAnswer);

        Collections.shuffle(newList);

        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));

        ivShowImage.setImageResource(map.get(roadSymbolList.get(index)));
    }

    public void nextQuestion(View view) {
        if(userChoice==null) {
            return;
        }

        btn1.setSelected(false);
        btn2.setSelected(false);
        btn3.setSelected(false);
        btn4.setSelected(false);

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);

        ++questionNumber;

        String correctAnswer = roadSymbolList.get(index);
        if (userChoice.equals(correctAnswer)) {
            points++;
        }

        index++;

        if (index > (roadSymbolList.size() / 2) - 1) {

            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);

            Intent intent = new Intent(StartDrivingTest.this, ResultScreen.class);
            intent.putExtra("points", points);
            startActivity(intent);

            finish();
        } else {
            startDrivingTest();
        }
    }

    public void answerSelected(View view) {
        btn1.setSelected(false);
        btn2.setSelected(false);
        btn3.setSelected(false);
        btn4.setSelected(false);

        view.setSelected(true);

        userChoice = ((Button) view).getText().toString().trim();
    }
}
