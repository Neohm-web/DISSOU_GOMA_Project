package com.example.dissou_goma_project;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
            public void onStartTrackingTouch(SeekBar seekBar) { }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
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
            }


            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });


        // --- Bouton Retour ---
        button3.setOnClickListener(v -> {
            Intent intent3 = new Intent(Activity1_1.this, Activity1.class);
            startActivity(intent3);
        });


        // --- Bouton Suivant : sauvegarde des réponses ---
        button2.setOnClickListener(v -> {
            if (selectionAfrique == -1 || valeurActuelle == -1 || selectionContinent == -1) {
                Toast.makeText(this, "Merci de répondre à toutes les questions avant de continuer", Toast.LENGTH_LONG).show();
                return;
            }


            // --- Sauvegarde dans SharedPreferences ---
            getSharedPreferences("ExplorateurData", MODE_PRIVATE)
                    .edit()
                    .putInt("q1_afrique", selectionAfrique)
                    .putInt("q2_reve_afrique", valeurActuelle)
                    .putInt("q3_continents", selectionContinent)
                    .apply();


            Toast.makeText(this, "✅ Réponses enregistrées !", Toast.LENGTH_SHORT).show();


            // --- Passer à l’activité suivante ---
            Intent intent4 = new Intent(Activity1_1.this, Activity2.class);
            startActivity(intent4);
        });
    }
}
