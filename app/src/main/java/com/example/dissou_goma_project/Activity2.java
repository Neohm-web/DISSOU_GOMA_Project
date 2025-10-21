package com.example.dissou_goma_project;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


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


        // --- Bouton Suivant ---
        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(v -> {
            Intent intent5 = new Intent(Activity2.this, Activity3.class);
            startActivity(intent5);
        });


        // --- Bouton Précédent ---
        Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(v -> {
            Intent intent6 = new Intent(Activity2.this, Activity1_1.class);
            startActivity(intent6);
        });


        // --- Q10 : Spinner avec animaux ---
        Spinner animalSpinner = findViewById(R.id.animalSpinner); // Assure-toi que c'est bien ce spinner dans le XML
        String[] animals = {
                "🦁 Le lion",
                "🐆 Le guépard",
                "🦌 La gazelle",
                "🐦 L'autruche"
        };
        ArrayAdapter<String> animalAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                animals
        );
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animalSpinner.setAdapter(animalAdapter);


        // --- Q11 : SeekBar de 1 à 10+ ---
        SeekBar desertSeekBar = findViewById(R.id.desertSeekBar); // ta SeekBar
        TextView seekBarValue = findViewById(R.id.seekBarValue);  // ton TextView d'affichage

        desertSeekBar.setMax(10); // 0 à 10 = 11 positions (10 = 10+)

// --- Texte de valeur en direct pour SeekBar ---
        desertSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Si le curseur est au max, on affiche "10+"
                if (progress == 10) {
                    seekBarValue.setText("Nombre estimé : 10+");
                } else {
                    seekBarValue.setText("Nombre estimé : " + (progress + 1)); // pour démarrer à 1
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }
}






