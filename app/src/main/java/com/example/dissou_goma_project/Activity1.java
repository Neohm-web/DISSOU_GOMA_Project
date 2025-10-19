package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> {
            Intent intent2 = new Intent(Activity1.this, MainActivity.class);
            startActivity(intent2);
        });
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(v -> {
            Intent intent1 = new Intent(Activity1.this, Activity1_1.class);
            startActivity(intent1);
        });
    }
}