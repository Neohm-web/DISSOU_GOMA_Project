package com.example.dissou_goma_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        // Remplir le spinner
        String[] heros = {
                "Sundiata Keïta", "Kwame Nkrumah", "Kimpa Vita",
                "Simon Kimbangu", "Shaka Zulu", "Nelson Mandela", "Béhanzin", "Agojié"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, heros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHeros.setAdapter(adapter);
        //spinnerHeros.setEnabled(false); // Spinner désactivé au départ

        // Activer le spinner si le champ nom n'est pas vide
        editTextNom.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                spinnerHeros.setEnabled(s.length() > 0);
            }
            @Override public void afterTextChanged(Editable s) {}
        });
        // Changer avatar selon héros choisi
        spinnerHeros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                    case 6	:
                        avatarImage.setImageResource(R.drawable.behanzin);
                        break;
                    case 7:
                        avatarImage.setImageResource(R.drawable.agojie);
                        break;
                    default:
                        avatarImage.setImageResource(R.drawable.default_avatar);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(v -> {
            Intent intent2 = new Intent(Activity1.this, MainActivity.class);
            startActivity(intent2);
        });


        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(v -> {
            String nom = editTextNom.getText().toString();
            String age = editTextAge.getText().toString();

            int selectedSexeId = radioGroupSexe.getCheckedRadioButtonId();
            RadioButton selectedSexe = findViewById(selectedSexeId);
            String sexe = selectedSexe != null ? selectedSexe.getText().toString() : "";

            Toast.makeText(this, "Nom: " + nom + "\nÂge: " + age + "\nSexe: " + sexe, Toast.LENGTH_SHORT).show();

            Intent intent1 = new Intent(Activity1.this, Activity1_1.class);
            intent1.putExtra("nom", nom);
            intent1.putExtra("age", age);
            intent1.putExtra("sexe", sexe);
            startActivity(intent1);
        });
    }
}