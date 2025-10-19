package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(v -> {
            Intent intent5 = new Intent(Activity2.this, Activity3.class);
            startActivity(intent5);
        });
        Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(v -> {
            Intent intent6 = new Intent(Activity2.this, Activity1_1.class);
            startActivity(intent6);
        });
    }
}