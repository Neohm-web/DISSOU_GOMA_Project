package com.example.dissou_goma_project;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button buttonSuivant;


    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_8);


        sharedPreferences = getSharedPreferences("Activity8Prefs", Context.MODE_PRIVATE);


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
        buttonSuivant = findViewById(R.id.button22);


        // === Spinners ===
        String[] totems = {"Python (sagesse)", "Lion (courage)", "Tortue (longévité)", "Éléphant (force)", "Aigle (liberté)"};
        ArrayAdapter<String> adapterTotem = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, totems);
        adapterTotem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTotem.setAdapter(adapterTotem);


        String[] animauxDangereux = {"Lion", "Hippopotame", "Crocodile", "Moustique", "Buffle"};
        ArrayAdapter<String> adapterDanger = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, animauxDangereux);
        adapterDanger.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDanger.setAdapter(adapterDanger);


        // === Listeners ===
        spinnerTotem.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                checkAllFieldsFilled();
            }


            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });


        spinnerDanger.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                checkAllFieldsFilled();
            }


            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });


        radioGroupElephants.setOnCheckedChangeListener((group, checkedId) -> checkAllFieldsFilled());


        setCheckboxToast(cbLion);
        setCheckboxToast(cbElephant);
        setCheckboxToast(cbRhino);
        setCheckboxToast(cbLeopard);
        setCheckboxToast(cbBuffle);
        setCheckboxToast(cbGirafe);
        setCheckboxToast(cbZebre);
        setCheckboxToast(cbGuepard);


        toggleChameau.setOnCheckedChangeListener((buttonView, isChecked) -> checkAllFieldsFilled());


        // === Bouton "Suivant" ===
        buttonSuivant.setOnClickListener(v -> {
            if (!areAllFieldsFilled()) {
                Toast.makeText(this, "Veuillez remplir tous les champs avant de continuer", Toast.LENGTH_SHORT).show();
                return;
            }


            saveResponses();
            startActivity(new Intent(Activity8.this, Activity10.class));
        });


        // === Bouton "Précédent" ===
        Button buttonPrecedent = findViewById(R.id.button21);
        buttonPrecedent.setOnClickListener(v -> {
            Toast.makeText(this, "Retour à l’activité précédente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity8.this, Activity7.class));
        });


        checkAllFieldsFilled(); // initial check
    }


    /** Ajoute un Toast automatique pour chaque CheckBox cochée/décochée **/
    private void setCheckboxToast(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> checkAllFieldsFilled());
    }


    /** Vérifie si tous les champs sont remplis **/
    private boolean areAllFieldsFilled() {
        boolean spinnerTotemFilled = spinnerTotem.getSelectedItemPosition() != -1;
        boolean spinnerDangerFilled = spinnerDanger.getSelectedItemPosition() != -1;
        boolean radioElephantsChecked = radioGroupElephants.getCheckedRadioButtonId() != -1;
        boolean bigFiveChecked = cbLion.isChecked() || cbElephant.isChecked() || cbRhino.isChecked() ||
                cbLeopard.isChecked() || cbBuffle.isChecked() || cbGirafe.isChecked() ||
                cbZebre.isChecked() || cbGuepard.isChecked();
        boolean toggleChecked = toggleChameau.isChecked() || !toggleChameau.isChecked(); // toujours vrai


        return spinnerTotemFilled && spinnerDangerFilled && radioElephantsChecked && bigFiveChecked && toggleChecked;
    }


    /** Active ou désactive le bouton "Suivant" selon le remplissage des champs **/
    private void checkAllFieldsFilled() {
        buttonSuivant.setEnabled(areAllFieldsFilled());
    }


    /** Sauvegarde les réponses dans SharedPreferences **/
    private void saveResponses() {
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("totem", spinnerTotem.getSelectedItem().toString());


        int selectedId = radioGroupElephants.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton rb = findViewById(selectedId);
            editor.putString("nbElephants", rb.getText().toString());
        }


        editor.putString("animalDangereux", spinnerDanger.getSelectedItem().toString());


        editor.putBoolean("cbLion", cbLion.isChecked());
        editor.putBoolean("cbElephant", cbElephant.isChecked());
        editor.putBoolean("cbRhino", cbRhino.isChecked());
        editor.putBoolean("cbLeopard", cbLeopard.isChecked());
        editor.putBoolean("cbBuffle", cbBuffle.isChecked());
        editor.putBoolean("cbGirafe", cbGirafe.isChecked());
        editor.putBoolean("cbZebre", cbZebre.isChecked());
        editor.putBoolean("cbGuepard", cbGuepard.isChecked());


        editor.putBoolean("toggleChameau", toggleChameau.isChecked());


        editor.apply();
        Toast.makeText(this, "Réponses sauvegardées", Toast.LENGTH_SHORT).show();
    }
}





