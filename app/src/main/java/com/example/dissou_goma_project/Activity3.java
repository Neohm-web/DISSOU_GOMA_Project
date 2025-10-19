package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(v -> {
            Intent intent7 = new Intent(Activity3.this, Activity4.class);
            startActivity(intent7);
        });
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> {
            Intent intent8 = new Intent(Activity3.this, Activity2.class);
            startActivity(intent8);
        });
    }
}