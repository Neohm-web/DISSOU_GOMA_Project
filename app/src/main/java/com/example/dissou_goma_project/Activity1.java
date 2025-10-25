package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity1 extends AppCompatActivity {

    private EditText editTextNom, editTextAge;
    private RadioGroup radioGroupSexe;
    private Spinner spinnerHeros;
    private ImageView avatarImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNom = findViewById(R.id.editTextNom);
        editTextAge = findViewById(R.id.editTextAge);
        radioGroupSexe = findViewById(R.id.radioGroupSexe);
        spinnerHeros = findViewById(R.id.spinnerHeros);
        avatarImage = findViewById(R.id.avatarImage);

        // --- Spinner : héros africains ---
        String[] heros = {
                "Sundiata Keïta", "Kwame Nkrumah", "Kimpa Vita",
                "Simon Kimbangu", "Shaka Zulu", "Nelson Mandela",
                "Béhanzin", "Agojié"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, heros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHeros.setAdapter(adapter);
        spinnerHeros.setEnabled(true); // désactivé tant que le nom n’est pas rempli

        // --- Activer le spinner si le nom est saisi ---
        editTextNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                spinnerHeros.setEnabled(s.length() > 0);
                if (s.length() > 0) {
                    Toast.makeText(Activity1.this, "Nom saisi : " + s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // --- Choix du héros ---
        spinnerHeros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String heroChoisi = heros[position];
                switch (position) {
                    case 0:
                        avatarImage.setImageResource(R.drawable.sundiata_keita);
                        break;
                    case 1:
                        avatarImage.setImageResource(R.drawable.kwame_nkrumah);
                        break;
                    case 2:
                        avatarImage.setImageResource(R.drawable.kimpa_vita);
                        break;
                    case 3:
                        avatarImage.setImageResource(R.drawable.simon_kimbangu);
                        break;
                    case 4:
                        avatarImage.setImageResource(R.drawable.shaka_zulu);
                        break;
                    case 5:
                        avatarImage.setImageResource(R.drawable.nelson_mandela);
                        break;
                    case 6:
                        avatarImage.setImageResource(R.drawable.behanzin);
                        break;
                    case 7:
                        avatarImage.setImageResource(R.drawable.agojie);
                        break;
                    default:
                        avatarImage.setImageResource(R.drawable.default_avatar);
                }

                // ✅ Afficher le héros choisi
                Toast.makeText(Activity1.this, "Héros choisi : " + heroChoisi, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // --- Choix du sexe ---
        radioGroupSexe.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) {
                Toast.makeText(Activity1.this, "Sexe sélectionné : " + rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        // --- Bouton retour (button7) ---
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> {
            Toast.makeText(Activity1.this, "Retour à l’accueil", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(Activity1.this, MainActivity.class);
            startActivity(intent2);
        });

        // --- Bouton continuer (button8) ---
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(v -> {
            String nom = editTextNom.getText().toString();
            String age = editTextAge.getText().toString();

            int selectedSexeId = radioGroupSexe.getCheckedRadioButtonId();
            RadioButton selectedSexe = findViewById(selectedSexeId);
            String sexe = selectedSexe != null ? selectedSexe.getText().toString() : "";

            if (nom.isEmpty() || age.isEmpty() || sexe.isEmpty()) {
                Toast.makeText(this, "Merci de remplir tous les champs avant de continuer", Toast.LENGTH_LONG).show();
                return;
            }

            // ✅ Sauvegarde dans SharedPreferences
            getSharedPreferences("QuizData", MODE_PRIVATE)
                    .edit()
                    .putString("user_name", nom)
                    .putString("user_age", age)
                    .putString("user_sexe", sexe)
                    .putInt("score_total", 0) // score initialisé à 0
                    .apply();

            // ✅ Confirmation
            Toast.makeText(this,
                    "✅ Profil enregistré !\nNom : " + nom +
                            "\nÂge : " + age +
                            "\nSexe : " + sexe,
                    Toast.LENGTH_LONG).show();
            Log.d("DEBUG", "Nom: " + nom + ", Âge: " + age + ", Sexe: " + sexe);

            // Passer à l’activité suivante
            Intent intent1 = new Intent(Activity1.this, Activity1_1.class);
            startActivity(intent1);
        });
    }
}