package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(v -> {
            Intent intent15 = new Intent(Activity7.this, Activity8.class);
            startActivity(intent15);
        });
        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(v -> {
            Intent intent16 = new Intent(Activity7.this, Activity6.class);
            startActivity(intent16);
        });
    }
}