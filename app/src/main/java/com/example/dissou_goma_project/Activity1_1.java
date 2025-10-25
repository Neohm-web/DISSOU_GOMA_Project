package com.example.dissou_goma_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

public class Activity1_1 extends AppCompatActivity {

    private int valeurActuelle = -1; // valeur SeekBar
    private int selectionAfrique = -1; // index du spinner Afrique
    private int selectionContinent = -1; // index du spinner continents

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

        // --- BOUTONS DE NAVIGATION ---
        Button button3 = findViewById(R.id.button3); // retour
        Button button2 = findViewById(R.id.button2); // suivant

        // --- SPINNER 1 : Avant ce quiz, l’Afrique pour vous c’était plutôt… ? ---
        Spinner spinner = findViewById(R.id.animalSpinner);
        String[] optionsAfrique = {
                "🦁 Les safaris et animaux",
                "🏺 L'Égypte et les pyramides",
                "🥁 La musique et la danse",
                "🍛 Les saveurs exotiques",
                "❓ Un grand mystère !"
        };
        ArrayAdapter<String> adapterAfrique = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsAfrique);
        adapterAfrique.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterAfrique);

        spinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                selectionAfrique = position;
                String choix = optionsAfrique[position];
                Toast.makeText(Activity1_1.this, "🌍 Vous avez choisi : " + choix, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        // --- SEEKBAR : Avez-vous déjà rêvé de visiter l’Afrique ? ---
        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView seekBarValue = findViewById(R.id.seekBarValue);
        seekBar.setMax(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valeurActuelle = progress;
                seekBarValue.setText("Note : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Activity1_1.this, "✨ Ajustez votre note de rêve...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Activity1_1.this, "💭 Niveau de rêve : " + valeurActuelle + "/10", Toast.LENGTH_SHORT).show();
            }
        });

        // --- SPINNER 2 : Combien de continents avez-vous déjà visités ? ---
        Spinner spinner2 = findViewById(R.id.spinner2);
        String[] continents = {"Aucun", "1", "2", "3", "4", "Tous les 5 !"};
        ArrayAdapter<String> adapterContinents = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, continents);
        adapterContinents.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterContinents);

        spinner2.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                selectionContinent = position;
                String choix = continents[position];
                Toast.makeText(Activity1_1.this, "✈️ Continents visités : " + choix, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });

        // --- Bouton Retour ---
        button3.setOnClickListener(v -> {
            Toast.makeText(this, "⬅️ Retour à l’accueil du quiz", Toast.LENGTH_SHORT).show();
            Intent intent3 = new Intent(Activity1_1.this, Activity1.class);
            startActivity(intent3);
        });

        // --- Bouton Suivant avec scoring et gestion de première saisie ---
        button2.setOnClickListener(v -> {
            if (selectionAfrique == -1) {
                Toast.makeText(this, "❗Veuillez choisir une réponse pour la première question.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (valeurActuelle == -1) {
                Toast.makeText(this, "❗Veuillez ajuster la note de la barre avant de continuer.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectionContinent == -1) {
                Toast.makeText(this, "❗Veuillez indiquer combien de continents vous avez visités.", Toast.LENGTH_SHORT).show();
                return;
            }

            // --- Calcul du score de cette activité ---
            int scoreAct1_1 = 0;
            if (selectionAfrique == 0 || selectionAfrique == 1) scoreAct1_1 += 5;
            if (valeurActuelle >= 7) scoreAct1_1 += 5;
            if (selectionContinent >= 2) scoreAct1_1 += 5;

            // --- Gestion du score total ---
            SharedPreferences prefs = getSharedPreferences("QuizData", MODE_PRIVATE);
            int ancienScoreAct1_1 = prefs.getInt("score_act1_1", -1); // -1 = jamais rempli
            int total = prefs.getInt("score_total", 0);

            if (ancienScoreAct1_1 == -1) {
                // première saisie
                total += scoreAct1_1;
            } else {
                // modification : soustraire l'ancien score puis ajouter le nouveau
                total = total - ancienScoreAct1_1 + scoreAct1_1;
            }

            // --- Sauvegarde des scores ---
            prefs.edit()
                    .putInt("score_total", total)
                    .putInt("score_act1_1", scoreAct1_1)
                    .apply();

            // --- Debug ---
            String nom = prefs.getString("user_name", "Inconnu");
            String age = prefs.getString("user_age", "?");
            String sexe = prefs.getString("user_sexe", "?");

            Log.d("DEBUG", "Nom=" + nom + " | Âge=" + age + " | Sexe=" + sexe +
                    " | Score Act1_1=" + scoreAct1_1 + " | Total=" + total);

            // --- Feedback utilisateur ---
            Toast.makeText(this,
                    "✅ Réponses enregistrées !\nScore activité : " + scoreAct1_1 + "\nScore total : " + total,
                    Toast.LENGTH_LONG).show();

            // --- Passer à l’activité suivante ---
            Intent intent4 = new Intent(Activity1_1.this, Activity2.class);
            startActivity(intent4);
        });
    }
}



