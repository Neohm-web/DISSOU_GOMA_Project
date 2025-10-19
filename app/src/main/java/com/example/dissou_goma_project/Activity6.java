package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button20 = findViewById(R.id.button20);
        button20.setOnClickListener(v -> {
            Intent intent13 = new Intent(Activity6.this, Activity7.class);
            startActivity(intent13);
        });
        Button button14 = findViewById(R.id.button14);
        button14.setOnClickListener(v -> {
            Intent intent14 = new Intent(Activity6.this, Activity5.class);
            startActivity(intent14);
        });
    }
}