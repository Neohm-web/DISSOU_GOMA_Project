package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity5 extends AppCompatActivity {

    private EditText editTextNumber, editTextNumber2;
    private RadioButton rbCleopatre, rbNefertiti, rbHatchepsout, rbTiyi;
    private CheckBox cbChat, cbCrocodile, cbIbis, cbScarabee, cbFaucon, cbSerpent;
    private Spinner spinner6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_5);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // === Liens avec les éléments XML ===
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber2 = findViewById(R.id.editTextNumber2);

        rbCleopatre = findViewById(R.id.radioButton8);
        rbNefertiti = findViewById(R.id.radioButton10);
        rbHatchepsout = findViewById(R.id.radioButton11);
        rbTiyi = findViewById(R.id.radioButton9);

        cbChat = findViewById(R.id.checkBox8);
        cbCrocodile = findViewById(R.id.checkBox11);
        cbIbis = findViewById(R.id.checkBox13);
        cbScarabee = findViewById(R.id.checkBox15);
        cbFaucon = findViewById(R.id.checkBox16);
        cbSerpent = findViewById(R.id.checkBox17);

        spinner6 = findViewById(R.id.spinner6);

        // === Données pour le spinner ===
        String[] têtes = {"Homme", "Chat", "Bélier", "Pharaon", "Dieu", "Aucune idée"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, têtes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter);

        // === Bouton "Suivant" ===
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(v -> {

            // Récupération des réponses
            String agePyramides = editTextNumber.getText().toString().trim();
            String nbPyramides = editTextNumber2.getText().toString().trim();

            String dernierePharaonne = "";
            if (rbCleopatre.isChecked()) dernierePharaonne = "Cléopâtre";
            else if (rbNefertiti.isChecked()) dernierePharaonne = "Nefertiti";
            else if (rbHatchepsout.isChecked()) dernierePharaonne = "Hatchepsout";
            else if (rbTiyi.isChecked()) dernierePharaonne = "Tiyi";

            StringBuilder animauxSacres = new StringBuilder();
            if (cbChat.isChecked()) animauxSacres.append("Chat, ");
            if (cbCrocodile.isChecked()) animauxSacres.append("Crocodile, ");
            if (cbIbis.isChecked()) animauxSacres.append("Ibis, ");
            if (cbScarabee.isChecked()) animauxSacres.append("Scarabée, ");
            if (cbFaucon.isChecked()) animauxSacres.append("Faucon, ");
            if (cbSerpent.isChecked()) animauxSacres.append("Serpent, ");
            if (animauxSacres.length() > 0)
                animauxSacres.setLength(animauxSacres.length() - 2); // retirer dernière virgule

            String teteSphinx = spinner6.getSelectedItem().toString();

            // Petit résumé (facultatif)
            String resume = "Âge des pyramides: " + agePyramides +
                    "\nDernière pharaonne: " + dernierePharaonne +
                    "\nNombre de pyramides: " + nbPyramides +
                    "\nAnimaux sacrés: " + animauxSacres +
                    "\nTête du Sphinx: " + teteSphinx;

            Toast.makeText(this, resume, Toast.LENGTH_LONG).show();

            // Navigation vers l’activité suivante
            Intent intent11 = new Intent(Activity5.this, Activity6.class);
            startActivity(intent11);
        });

        // === Bouton "Précédent" ===
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(v -> {
            Intent intent12 = new Intent(Activity5.this, Activity4.class);
            startActivity(intent12);
        });
    }
}
