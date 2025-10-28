package com.example.dissou_goma_project;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Activity2 extends AppCompatActivity {


    private boolean animalSelectionne = false;
    private boolean seekBarTouche = false;


    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "safariPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);


        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button button12 = findViewById(R.id.button12); // Suivant
        Button button13 = findViewById(R.id.button13); // Précédent


        // --- Spinner ---
        Spinner animalSpinner = findViewById(R.id.animalSpinner);
        String[] animals = {"Sélectionnez un animal", "🦁 Le lion", "🐆 Le guépard", "🦌 La gazelle", "🐦 L'autruche"};


        ArrayAdapter<String> animalAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                animals
        );
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animalSpinner.setAdapter(animalAdapter);


        animalSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                if (position > 0) {
                    animalSelectionne = true;
                    String animalChoisi = animals[position];
                    Toast.makeText(Activity2.this, "🐾 Animal préféré : " + animalChoisi, Toast.LENGTH_SHORT).show();
                } else {
                    animalSelectionne = false;
                }
            }


            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                animalSelectionne = false;
            }
        });


        // --- SeekBar ---
        SeekBar desertSeekBar = findViewById(R.id.desertSeekBar);
        TextView seekBarValue = findViewById(R.id.seekBarValue);
        desertSeekBar.setMax(10);
        seekBarValue.setText("Déplacez le curseur pour estimer");


        desertSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    seekBarTouche = true;
                }


                if (progress == 10) {
                    seekBarValue.setText("Nombre estimé : 10+");
                } else {
                    seekBarValue.setText("Nombre estimé : " + (progress + 1));
                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Activity2.this, "🌵 Ajustez votre estimation...", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress == 10) {
                    Toast.makeText(Activity2.this, "🔥 Tu penses qu'il y en a plus de 10 !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity2.this, "✨ Estimation finale : " + (progress + 1), Toast.LENGTH_SHORT).show();
                }
            }
        });


        // --- Bouton Précédent ---
        button13.setOnClickListener(v -> {
            Toast.makeText(this, "⬅️ Retour à l'activité précédente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity2.this, Activity1_1.class));
        });


        // --- Bouton Suivant ---
        button12.setOnClickListener(v -> {
            if (!animalSelectionne) {
                Toast.makeText(this, "❗Veuillez choisir un animal avant de continuer.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!seekBarTouche) {
                Toast.makeText(this, "❗Veuillez ajuster la barre avant de continuer.", Toast.LENGTH_SHORT).show();
                return;
            }


            Toast.makeText(this, "✅ Réponses enregistrées ! Passage à la suite...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity2.this, Activity3.class));
        });
    }
}