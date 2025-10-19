package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity1_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_11);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            Intent intent3 = new Intent(Activity1_1.this, Activity1.class);
            startActivity(intent3);
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> {
            Intent intent4 = new Intent(Activity1_1.this, Activity2.class);
            startActivity(intent4);
        });
    }
}