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
        roadSymbolList.add("Bootstrap");
        roadSymbolList.add("C");
        roadSymbolList.add("Codeigniter");
        roadSymbolList.add("Cplusplus");
        roadSymbolList.add("Csharp");
        roadSymbolList.add("Css3");
        roadSymbolList.add("Github");
        roadSymbolList.add("Html5");
        roadSymbolList.add("Java");
        roadSymbolList.add("jQuery");
        roadSymbolList.add("MySQL");

        map.put(roadSymbolList.get(0), R.drawable.bootstrap);
        map.put(roadSymbolList.get(1), R.drawable.c);
        map.put(roadSymbolList.get(2), R.drawable.codeigniter);
        map.put(roadSymbolList.get(3), R.drawable.cplusplus);
        map.put(roadSymbolList.get(4), R.drawable.csharp);
        map.put(roadSymbolList.get(5), R.drawable.css3);
        map.put(roadSymbolList.get(6), R.drawable.github);
        map.put(roadSymbolList.get(7), R.drawable.html5);
        map.put(roadSymbolList.get(8), R.drawable.java);
        map.put(roadSymbolList.get(9), R.drawable.jquery);
        map.put(roadSymbolList.get(10), R.drawable.mysql);

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
