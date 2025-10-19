package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(v -> {
            Intent intent9 = new Intent(Activity4.this, Activity5.class);
            startActivity(intent9);
        });
        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(v -> {
            Intent intent10 = new Intent(Activity4.this, Activity3.class);
            startActivity(intent10);
        });
    }
}