package com.example.dissou_goma_project;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Activity9 extends AppCompatActivity {


    private ImageButton imgVilleModerne, imgVilleHistorique, imgVilleCulturelle, imgVilleBalneaire;
    private RadioGroup radioGroupCapitale;
    private Spinner spinnerMarrakech;
    private AutoCompleteTextView autoVille;
    private CheckBox cbNollywood, cbAfrobeat, cbPlages, cbEconomie, cbGastronomie;


    private String ambianceChoisie = "";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_9);


        sharedPreferences = getSharedPreferences("Activity9Prefs", Context.MODE_PRIVATE);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // === Initialisation ===
        imgVilleModerne = findViewById(R.id.imgVilleModerne);
        imgVilleHistorique = findViewById(R.id.imgVilleHistorique);
        imgVilleCulturelle = findViewById(R.id.imgVilleCulturelle);
        imgVilleBalneaire = findViewById(R.id.imgVilleBalneaire);


        radioGroupCapitale = findViewById(R.id.radioGroupCapitale);
        spinnerMarrakech = findViewById(R.id.spinnerMarrakech);
        autoVille = findViewById(R.id.autoVille);


        cbNollywood = findViewById(R.id.cbNollywood);
        cbAfrobeat = findViewById(R.id.cbAfrobeat);
        cbPlages = findViewById(R.id.cbPlages);
        cbEconomie = findViewById(R.id.cbEconomie);
        cbGastronomie = findViewById(R.id.cbGastronomie);


        // === Choix ambiance (ImageButtons) ===
        imgVilleModerne.setOnClickListener(v -> saveAmbiance("Ville moderne (Johannesburg, Cotonou, Nairobi)"));
        imgVilleHistorique.setOnClickListener(v -> saveAmbiance("Ville historique (Ouidah, Brazzaville, Gorée)"));
        imgVilleCulturelle.setOnClickListener(v -> saveAmbiance("Ville culturelle (Dakar, Abomey, Saint-Louis)"));
        imgVilleBalneaire.setOnClickListener(v -> saveAmbiance("Ville balnéaire (Pointe-Noire, Grand-Popo, Zanzibar)"));


        // === Spinner Marrakech ===
        String[] pays = {"Maroc", "Algérie", "Tunisie", "Égypte"};
        ArrayAdapter<String> adapterPays = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pays);
        adapterPays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarrakech.setAdapter(adapterPays);


        spinnerMarrakech.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                saveSpinnerChoix(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });


        // === AutoCompleteTextView pour la ville ===
        String[] villes = {"Le Caire", "Lagos", "Nairobi", "Casablanca", "Dakar", "Addis-Abeba", "Accra", "Kigali"};
        ArrayAdapter<String> adapterVilles = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, villes);
        autoVille.setAdapter(adapterVilles);
        autoVille.setOnItemClickListener((parent, view, position, id) ->
                saveAutoComplete(parent.getItemAtPosition(position).toString())
        );


        // === RadioGroup Capitales ===
        radioGroupCapitale.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) saveCapitale(rb.getText().toString());
        });


        // === CheckBoxes Lagos connue pour ===
        setCheckboxListener(cbNollywood);
        setCheckboxListener(cbAfrobeat);
        setCheckboxListener(cbPlages);
        setCheckboxListener(cbEconomie);
        setCheckboxListener(cbGastronomie);


        Button buttonSuivant = findViewById(R.id.button23);
        buttonSuivant.setOnClickListener(v -> {
            if (verifierChamps()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ville", autoVille.getText().toString());
                editor.putString("capitale", ((RadioButton)findViewById(radioGroupCapitale.getCheckedRadioButtonId())).getText().toString());
                editor.putString("marrakech", spinnerMarrakech.getSelectedItem().toString());
                editor.putString("ambiance", ambianceChoisie);
                editor.apply();

                startActivity(new Intent(Activity9.this, Activity10.class));
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs obligatoires avant de continuer", Toast.LENGTH_SHORT).show();
            }
        });



        // === Bouton "Précédent" ===
        Button buttonPrecedent = findViewById(R.id.button16);
        buttonPrecedent.setOnClickListener(v -> {
            Toast.makeText(this, "Retour à l’activité précédente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity9.this, Activity8.class));
        });
    }


    private void saveAmbiance(String choix) {
        ambianceChoisie = choix;
        sharedPreferences.edit().putString("ambiance", ambianceChoisie).apply();
        Toast.makeText(this, "Ambiance : " + ambianceChoisie, Toast.LENGTH_SHORT).show();
    }


    private void saveCapitale(String choix) {
        sharedPreferences.edit().putString("capitale", choix).apply();
        Toast.makeText(this, "Capitale choisie : " + choix, Toast.LENGTH_SHORT).show();
    }


    private void saveSpinnerChoix(String choix) {
        sharedPreferences.edit().putString("marrakech", choix).apply();
        Toast.makeText(this, "Marrakech se trouve au : " + choix, Toast.LENGTH_SHORT).show();
    }


    private void saveAutoComplete(String choix) {
        sharedPreferences.edit().putString("ville", choix).apply();
        Toast.makeText(this, "Ville sélectionnée : " + choix, Toast.LENGTH_SHORT).show();
    }


    private void setCheckboxListener(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit().putBoolean(checkBox.getText().toString(), isChecked).apply();
            Toast.makeText(this, checkBox.getText() + (isChecked ? " sélectionné(e)" : " désélectionné(e)"), Toast.LENGTH_SHORT).show();
        });
    }


    private boolean verifierChamps() {
        // On relit directement les valeurs à l’écran
        boolean ambianceOk = !ambianceChoisie.isEmpty();

        int selectedRadioId = radioGroupCapitale.getCheckedRadioButtonId();
        boolean capitaleOk = selectedRadioId != -1;

        boolean marrakechOk = spinnerMarrakech.getSelectedItem() != null &&
                !spinnerMarrakech.getSelectedItem().toString().isEmpty();

        boolean villeOk = !autoVille.getText().toString().trim().isEmpty();

        return ambianceOk && capitaleOk && marrakechOk && villeOk;
    }

}
