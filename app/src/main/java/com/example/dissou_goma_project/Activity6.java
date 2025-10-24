package com.example.dissou_goma_project;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Activity6 extends AppCompatActivity {


    // Déclaration des composants UI
    private RadioGroup radioGroupMandela;
    private Spinner spinnerPays;
    private SeekBar seekBarIndep;
    private TextView textViewSeekValue;
    private CheckBox checkGhana, checkZoulou, checkEcrivain, checkMusicien, checkAuteur;
    private AutoCompleteTextView autoCompleteFemme;
    private Button buttonPrecedent, buttonSuivant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_6);


        // Ajustement du padding pour les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // --- Initialisation des vues ---
        radioGroupMandela = findViewById(R.id.radioGroupMandela);
        spinnerPays = findViewById(R.id.spinnerPays);
        seekBarIndep = findViewById(R.id.seekBarIndep);
        textViewSeekValue = findViewById(R.id.textViewSeekValue); // tu devras l’ajouter dans ton XML
        checkGhana = findViewById(R.id.checkBoxGhana);
        checkZoulou = findViewById(R.id.checkBoxZoulou);
        checkEcrivain = findViewById(R.id.checkBoxEcrivain);
        checkMusicien = findViewById(R.id.checkBoxMusicien);
        checkAuteur = findViewById(R.id.checkBoxAuteur);
        autoCompleteFemme = findViewById(R.id.autoCompleteFemme);
        buttonPrecedent = findViewById(R.id.button14);
        buttonSuivant = findViewById(R.id.button20);


        // --- Configuration du Spinner (question 30) ---
        String[] paysOptions = {"Éthiopie", "Libéria", "Maroc", "Kenya", "Les deux premiers"};
        ArrayAdapter<String> paysAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                paysOptions
        );
        paysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPays.setAdapter(paysAdapter);


        // --- Configuration du SeekBar (question 31) ---
        // Plage : 0 à 4 → décennies : 1940, 1950, 1960, 1970, 1980
        String[] decades = {"1940", "1950", "1960", "1970", "1980"};
        seekBarIndep.setMax(decades.length - 1);
        seekBarIndep.setProgress(2); // Valeur par défaut : 1960
        textViewSeekValue.setText("Décennie : " + decades[2]);


        seekBarIndep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewSeekValue.setText("Décennie : " + decades[progress]);
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        // --- AutoCompleteTextView (question 33) ---
        String[] femmesNobel = {"Wangari Maathai", "Leymah Gbowee", "Ellen Johnson Sirleaf"};
        ArrayAdapter<String> femmeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                femmesNobel
        );
        autoCompleteFemme.setAdapter(femmeAdapter);


        // --- Bouton Suivant ---
        buttonSuivant.setOnClickListener(v -> {
            Intent intent = new Intent(Activity6.this, Activity7.class);
            startActivity(intent);
        });


        // --- Bouton Précédent ---
        buttonPrecedent.setOnClickListener(v -> {
            Intent intent = new Intent(Activity6.this, Activity5.class);
            startActivity(intent);
        });
    }
}






