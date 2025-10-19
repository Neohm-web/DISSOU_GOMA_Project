package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_9);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button23 = findViewById(R.id.button23);
        button23.setOnClickListener(v -> {
            Intent intent19 = new Intent(Activity9.this, Activity10.class);
            startActivity(intent19);
        });
        Button button16 = findViewById(R.id.button16);
        button16.setOnClickListener(v -> {
            Intent intent20 = new Intent(Activity9.this, Activity8.class);
            startActivity(intent20);
        });
    }
}