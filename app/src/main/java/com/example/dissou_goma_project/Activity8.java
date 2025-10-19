package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_8);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button22 = findViewById(R.id.button22);
        button22.setOnClickListener(v -> {
            Intent intent17 = new Intent(Activity8.this, Activity9.class);
            startActivity(intent17);
        });
        Button button21 = findViewById(R.id.button21);
        button21.setOnClickListener(v -> {
            Intent intent18 = new Intent(Activity8.this, Activity7.class);
            startActivity(intent18);
        });
    }
}