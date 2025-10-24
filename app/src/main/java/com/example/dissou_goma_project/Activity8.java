package com.example.dissou_goma_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity8 extends AppCompatActivity {

    private Spinner spinnerTotem, spinnerDanger;
    private RadioGroup radioGroupElephants;
    private CheckBox cbLion, cbElephant, cbRhino, cbLeopard, cbBuffle, cbGirafe, cbZebre, cbGuepard;
    private ToggleButton toggleChameau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_8);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // === Initialisation des vues ===
        spinnerTotem = findViewById(R.id.spinnerTotem);
        spinnerDanger = findViewById(R.id.spinnerDanger);
        radioGroupElephants = findViewById(R.id.radioGroupElephants);

        cbLion = findViewById(R.id.cbLion);
        cbElephant = findViewById(R.id.cbElephant);
        cbRhino = findViewById(R.id.cbRhino);
        cbLeopard = findViewById(R.id.cbLeopard);
        cbBuffle = findViewById(R.id.cbBuffle);
        cbGirafe = findViewById(R.id.cbGirafe);
        cbZebre = findViewById(R.id.cbZebre);
        cbGuepard = findViewById(R.id.cbGuepard);

        toggleChameau = findViewById(R.id.toggleChameau);

        // === Spinners ===
        String[] totems = {"Python (sagesse)", "Lion (courage)", "Tortue (longévité)", "Éléphant (force)", "Aigle (liberté)"};
        ArrayAdapter<String> adapterTotem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, totems);
        adapterTotem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTotem.setAdapter(adapterTotem);

        String[] animauxDangereux = {"Lion", "Hippopotame", "Crocodile", "Moustique", "Buffle"};
        ArrayAdapter<String> adapterDanger = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, animauxDangereux);
        adapterDanger.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDanger.setAdapter(adapterDanger);

        // === Toasts pour les Spinners ===
        spinnerTotem.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();
                Toast.makeText(Activity8.this, "Totem choisi : " + choix, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        spinnerDanger.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();
                Toast.makeText(Activity8.this, "Animal dangereux choisi : " + choix, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        // === RadioGroup Éléphants ===
        radioGroupElephants.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) {
                Toast.makeText(this, "Nombre d’éléphants choisi : " + rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        // === CheckBoxes Big Five + autres ===
        setCheckboxToast(cbLion);
        setCheckboxToast(cbElephant);
        setCheckboxToast(cbRhino);
        setCheckboxToast(cbLeopard);
        setCheckboxToast(cbBuffle);
        setCheckboxToast(cbGirafe);
        setCheckboxToast(cbZebre);
        setCheckboxToast(cbGuepard);

        // === ToggleButton Chameau ===
        toggleChameau.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                toggleChameau.setBackgroundColor(Color.parseColor("#4CAF50")); // vert
                Toast.makeText(this, "Choix : Chameau", Toast.LENGTH_SHORT).show();
            } else {
                toggleChameau.setBackgroundColor(Color.parseColor("#D3D3D3")); // gris clair
                Toast.makeText(this, "Choix : Autre", Toast.LENGTH_SHORT).show();
            }
        });

        // === Bouton "Suivant" ===
        Button buttonSuivant = findViewById(R.id.button22);
        buttonSuivant.setOnClickListener(v -> {
            // Lecture des réponses
            String totemChoisi = spinnerTotem.getSelectedItem().toString();
            String animalDangereux = spinnerDanger.getSelectedItem().toString();

            String nbElephants = "";
            int selectedId = radioGroupElephants.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton rb = findViewById(selectedId);
                nbElephants = rb.getText().toString();
            }

            StringBuilder bigFive = new StringBuilder();
            if (cbLion.isChecked()) bigFive.append("Lion, ");
            if (cbElephant.isChecked()) bigFive.append("Éléphant, ");
            if (cbRhino.isChecked()) bigFive.append("Rhinocéros, ");
            if (cbLeopard.isChecked()) bigFive.append("Léopard, ");
            if (cbBuffle.isChecked()) bigFive.append("Buffle, ");
            if (cbGirafe.isChecked()) bigFive.append("Girafe, ");
            if (cbZebre.isChecked()) bigFive.append("Zèbre, ");
            if (cbGuepard.isChecked()) bigFive.append("Guépard, ");
            if (bigFive.length() > 0) bigFive.setLength(bigFive.length() - 2);

            String animalSansEau = toggleChameau.isChecked() ? "Chameau" : "Autre";

            // Résumé
            String resume = "Totem : " + totemChoisi +
                    "\nÉléphants : " + nbElephants +
                    "\nDangereux : " + animalDangereux +
                    "\nBig Five : " + bigFive +
                    "\nSans eau : " + animalSansEau;

            Toast.makeText(this, resume, Toast.LENGTH_LONG).show();
            startActivity(new Intent(Activity8.this, Activity9.class));
        });

        // === Bouton "Précédent" ===
        Button buttonPrecedent = findViewById(R.id.button21);
        buttonPrecedent.setOnClickListener(v -> {
            Toast.makeText(this, "Retour à l’activité précédente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity8.this, Activity7.class));
        });
    }

    /** Ajoute un Toast automatique pour chaque CheckBox cochée/décochée **/
    private void setCheckboxToast(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, checkBox.getText() + " sélectionné(e)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, checkBox.getText() + " désélectionné(e)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
