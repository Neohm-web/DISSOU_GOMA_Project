package com.example.dissou_goma_project;

import android.content.Intent;
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

    private int selectionAnimal = -1; // valeur du spinner
    private int valeurSeekBar = -1;  // valeur de la SeekBar

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

        // --- Boutons ---
        Button button12 = findViewById(R.id.button12); // suivant
        Button button13 = findViewById(R.id.button13); // précédent

        // --- Q10 : Spinner avec animaux ---
        Spinner animalSpinner = findViewById(R.id.animalSpinner);
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

        // Afficher un Toast à chaque choix
        animalSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                selectionAnimal = position;
                String animalChoisi = animals[position];
                Toast.makeText(Activity2.this, "🐾 Animal préféré : " + animalChoisi, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        // --- Q11 : SeekBar de 1 à 10+ ---
        SeekBar desertSeekBar = findViewById(R.id.desertSeekBar);
        TextView seekBarValue = findViewById(R.id.seekBarValue);

        desertSeekBar.setMax(10);

        desertSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valeurSeekBar = progress;
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
                if (valeurSeekBar == 10) {
                    Toast.makeText(Activity2.this, "🔥 Tu penses qu’il y en a plus de 10 !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Activity2.this, "✨ Estimation finale : " + (valeurSeekBar + 1), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // --- Bouton Précédent ---
        button13.setOnClickListener(v -> {
            Toast.makeText(this, "⬅️ Retour à l’activité précédente", Toast.LENGTH_SHORT).show();
            Intent intent6 = new Intent(Activity2.this, Activity1_1.class);
            startActivity(intent6);
        });

        // --- Bouton Suivant avec condition ---
        button12.setOnClickListener(v -> {
            // Vérifie si un animal est choisi
            if (selectionAnimal == -1) {
                Toast.makeText(this, "❗Veuillez choisir un animal avant de continuer.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Vérifie si la SeekBar a été utilisée
            if (valeurSeekBar == -1) {
                Toast.makeText(this, "❗Veuillez ajuster la barre avant de continuer.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Si tout est bon :
            Toast.makeText(this, "✅ Réponses enregistrées ! Passage à la suite...", Toast.LENGTH_SHORT).show();
            Intent intent5 = new Intent(Activity2.this, Activity3.class);
            startActivity(intent5);
        });
    }
}
