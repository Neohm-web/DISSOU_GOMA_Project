package com.example.dissou_goma_project;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_9);

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
        imgVilleModerne.setOnClickListener(v -> {
            ambianceChoisie = "Ville moderne (Johannesburg, Cotonou, Nairobi)";
            Toast.makeText(this, "Ambiance : " + ambianceChoisie, Toast.LENGTH_SHORT).show();
        });

        imgVilleHistorique.setOnClickListener(v -> {
            ambianceChoisie = "Ville historique (Ouidah, Brazzaville, Gorée)";
            Toast.makeText(this, "Ambiance : " + ambianceChoisie, Toast.LENGTH_SHORT).show();
        });

        imgVilleCulturelle.setOnClickListener(v -> {
            ambianceChoisie = "Ville culturelle (Dakar, Abomey, Saint-Louis)";
            Toast.makeText(this, "Ambiance : " + ambianceChoisie, Toast.LENGTH_SHORT).show();
        });

        imgVilleBalneaire.setOnClickListener(v -> {
            ambianceChoisie = "Ville balnéaire (Pointe-Noire, Grand-Popo, Zanzibar)";
            Toast.makeText(this, "Ambiance : " + ambianceChoisie, Toast.LENGTH_SHORT).show();
        });

        // === Spinner Marrakech ===
        String[] pays = {"Maroc", "Algérie", "Tunisie", "Égypte"};
        ArrayAdapter<String> adapterPays = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pays);
        adapterPays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarrakech.setAdapter(adapterPays);

        spinnerMarrakech.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();
                Toast.makeText(Activity9.this, "Marrakech se trouve au : " + choix, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        // === AutoCompleteTextView pour la ville ===
        String[] villes = {"Le Caire", "Lagos", "Nairobi", "Casablanca", "Dakar", "Addis-Abeba", "Accra", "Kigali"};
        ArrayAdapter<String> adapterVilles = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, villes);
        autoVille.setAdapter(adapterVilles);

        autoVille.setOnItemClickListener((parent, view, position, id) -> {
            String ville = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, "Ville sélectionnée : " + ville, Toast.LENGTH_SHORT).show();
        });

        // === RadioGroup Capitales ===
        radioGroupCapitale.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) {
                Toast.makeText(this, "Capitale choisie : " + rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        // === CheckBoxes Lagos connue pour ===
        setCheckboxToast(cbNollywood);
        setCheckboxToast(cbAfrobeat);
        setCheckboxToast(cbPlages);
        setCheckboxToast(cbEconomie);
        setCheckboxToast(cbGastronomie);

        // === Bouton "Suivant" ===
        Button buttonSuivant = findViewById(R.id.button23);
        buttonSuivant.setOnClickListener(v -> {
            String capitale = "";
            int selectedId = radioGroupCapitale.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton rb = findViewById(selectedId);
                capitale = rb.getText().toString();
            }

            String paysMarrakech = spinnerMarrakech.getSelectedItem().toString();
            String villeChoisie = autoVille.getText().toString();

            StringBuilder lagosConnuePour = new StringBuilder();
            if (cbNollywood.isChecked()) lagosConnuePour.append("Nollywood, ");
            if (cbAfrobeat.isChecked()) lagosConnuePour.append("Afrobeat, ");
            if (cbPlages.isChecked()) lagosConnuePour.append("Plages, ");
            if (cbEconomie.isChecked()) lagosConnuePour.append("Économie, ");
            if (cbGastronomie.isChecked()) lagosConnuePour.append("Gastronomie, ");
            if (lagosConnuePour.length() > 0) lagosConnuePour.setLength(lagosConnuePour.length() - 2);

            String resume = "Ambiance : " + ambianceChoisie +
                    "\nCapitale : " + capitale +
                    "\nMarrakech : " + paysMarrakech +
                    "\nVille à découvrir : " + villeChoisie +
                    "\nLagos connue pour : " + lagosConnuePour;

            Toast.makeText(this, resume, Toast.LENGTH_LONG).show();
            startActivity(new Intent(Activity9.this, Activity10.class));
        });

        // === Bouton "Précédent" ===
        Button buttonPrecedent = findViewById(R.id.button16);
        buttonPrecedent.setOnClickListener(v -> {
            Toast.makeText(this, "Retour à l’activité précédente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity9.this, Activity8.class));
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
