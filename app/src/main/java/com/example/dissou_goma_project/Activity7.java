package com.example.dissou_goma_project;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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


public class Activity7 extends AppCompatActivity {


    private Spinner spinner7;
    private ImageButton imageButton2, imageButton9, imageButton7, imageButton8, imageButton10;
    private EditText editTextNumber3;
    private RadioGroup radioGroupDeserts;
    private RadioButton rbSahara, rbKalahari, rbNamibie, rbArabie;
    private RadioButton rbZambie, rbZimbabwe, rbAfriqueSud, rbBotswana, rbMozambique;


    private String paysageChoisi = ""; // pour stocker l’image sélectionnée


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_7);


        // Gestion des insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // === Initialisation des vues ===
        spinner7 = findViewById(R.id.spinner7);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton9 = findViewById(R.id.imageButton9);
        imageButton7 = findViewById(R.id.imageButton7);
        imageButton8 = findViewById(R.id.imageButton8);
        imageButton10 = findViewById(R.id.imageButton10);
        editTextNumber3 = findViewById(R.id.editTextNumber3);


        radioGroupDeserts = findViewById(R.id.radioGroup1);
        rbSahara = findViewById(R.id.radioButton12);
        rbKalahari = findViewById(R.id.radioButton13);
        rbNamibie = findViewById(R.id.radioButton14);
        rbArabie = findViewById(R.id.radioButton15);


        rbZambie = findViewById(R.id.checkBox14);
        rbZimbabwe = findViewById(R.id.checkBox18);
        rbAfriqueSud = findViewById(R.id.checkBox19);
        rbBotswana = findViewById(R.id.checkBox20);
        rbMozambique = findViewById(R.id.checkBox21);


        // === Spinner (merveilles africaines) ===
        String[] merveilles = {"Pyramides d'Égypte", "Timbuktu", "Table Mountain", "Chutes Victoria", "Lac Malawi", "Autre"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, merveilles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter);


        spinner7.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String merveilleChoisie = parent.getItemAtPosition(position).toString();
                Toast.makeText(Activity7.this, "Merveille sélectionnée : " + merveilleChoisie, Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });


        // === Sélection du paysage via ImageButtons ===
        imageButton2.setOnClickListener(v -> {
            paysageChoisi = "Plage";
            Toast.makeText(this, "Vous avez choisi : Plage", Toast.LENGTH_SHORT).show();
        });


        imageButton9.setOnClickListener(v -> {
            paysageChoisi = "Savane";
            Toast.makeText(this, "Vous avez choisi : Savane", Toast.LENGTH_SHORT).show();
        });


        imageButton7.setOnClickListener(v -> {
            paysageChoisi = "Forêt tropicale";
            Toast.makeText(this, "Vous avez choisi : Forêt tropicale", Toast.LENGTH_SHORT).show();
        });


        imageButton8.setOnClickListener(v -> {
            paysageChoisi = "Montagnes";
            Toast.makeText(this, "Vous avez choisi : Montagnes", Toast.LENGTH_SHORT).show();
        });


        imageButton10.setOnClickListener(v -> {
            paysageChoisi = "Désert";
            Toast.makeText(this, "Vous avez choisi : Désert", Toast.LENGTH_SHORT).show();
        });


        // === RadioGroup Déserts ===
        radioGroupDeserts.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton checked = findViewById(checkedId);
            if (checked != null) {
                Toast.makeText(this, "Désert choisi : " + checked.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        // === Pays des chutes Victoria (radio buttons isolés) ===
        rbZambie.setOnClickListener(v -> Toast.makeText(this, "Choisi : Zambie", Toast.LENGTH_SHORT).show());
        rbZimbabwe.setOnClickListener(v -> Toast.makeText(this, "Choisi : Zimbabwe", Toast.LENGTH_SHORT).show());
        rbAfriqueSud.setOnClickListener(v -> Toast.makeText(this, "Choisi : Afrique du Sud", Toast.LENGTH_SHORT).show());
        rbBotswana.setOnClickListener(v -> Toast.makeText(this, "Choisi : Botswana", Toast.LENGTH_SHORT).show());
        rbMozambique.setOnClickListener(v -> Toast.makeText(this, "Choisi : Mozambique", Toast.LENGTH_SHORT).show());


        // === Bouton "Suivant" avec validation et SharedPreferences ===
        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(v -> {
            String merveilleChoisie = spinner7.getSelectedItem().toString().trim();
            String hauteurKilimandjaro = editTextNumber3.getText().toString().trim();


            if (merveilleChoisie.isEmpty()) {
                Toast.makeText(this, "Veuillez choisir une merveille.", Toast.LENGTH_SHORT).show();
                return;
            }


            if (paysageChoisi.isEmpty()) {
                Toast.makeText(this, "Veuillez choisir un paysage.", Toast.LENGTH_SHORT).show();
                return;
            }


            if (hauteurKilimandjaro.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer la hauteur du Kilimandjaro.", Toast.LENGTH_SHORT).show();
                return;
            }


            String desertChoisi = "";
            if (rbSahara.isChecked()) desertChoisi = "Sahara";
            else if (rbKalahari.isChecked()) desertChoisi = "Kalahari";
            else if (rbNamibie.isChecked()) desertChoisi = "Namibie";
            else if (rbArabie.isChecked()) desertChoisi = "Désert d'Arabie";


            if (desertChoisi.isEmpty()) {
                Toast.makeText(this, "Veuillez choisir un désert.", Toast.LENGTH_SHORT).show();
                return;
            }


            StringBuilder paysChutes = new StringBuilder();
            if (rbZambie.isChecked()) paysChutes.append("Zambie, ");
            if (rbZimbabwe.isChecked()) paysChutes.append("Zimbabwe, ");
            if (rbAfriqueSud.isChecked()) paysChutes.append("Afrique du Sud, ");
            if (rbBotswana.isChecked()) paysChutes.append("Botswana, ");
            if (rbMozambique.isChecked()) paysChutes.append("Mozambique, ");
            if (paysChutes.length() == 0) {
                Toast.makeText(this, "Veuillez choisir au moins un pays pour les chutes Victoria.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                paysChutes.setLength(paysChutes.length() - 2);
            }


            // === SharedPreferences pour Activity10 ===
            SharedPreferences prefs = getSharedPreferences("Activity10Prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("merveille", merveilleChoisie);
            editor.putString("paysage", paysageChoisi);
            editor.putString("kilimandjaro", hauteurKilimandjaro);
            editor.putString("desert", desertChoisi);
            editor.putString("paysChutes", paysChutes.toString());
            editor.apply();


            // Passage à l'activité suivante
            Intent intent = new Intent(Activity7.this, Activity8.class);
            startActivity(intent);
        });


        // === Bouton "Précédent" ===
        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(v -> {
            Toast.makeText(this, "Retour à l’activité précédente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Activity7.this, Activity6.class);
            startActivity(intent);
        });
    }
}





