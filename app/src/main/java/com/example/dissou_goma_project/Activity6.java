package com.example.dissou_goma_project;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.HashSet;
import java.util.Set;


public class Activity6 extends AppCompatActivity {


    private RadioGroup radioGroupMandela;
    private Spinner spinnerPays;
    private SeekBar seekBarIndep;
    private TextView textViewSeekValue;
    private CheckBox checkGhana, checkZoulou, checkEcrivain, checkMusicien, checkAuteur;
    private AutoCompleteTextView autoCompleteFemme;
    private Button buttonPrecedent, buttonSuivant;


    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "Activity6Prefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_6);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // 🔹 Nettoyage si tu veux recommencer à zéro
        sharedPreferences.edit().clear().apply();
        // Ajustement padding pour barres système

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Initialisation vues
        radioGroupMandela = findViewById(R.id.radioGroupMandela);
        spinnerPays = findViewById(R.id.spinnerPays);
        seekBarIndep = findViewById(R.id.seekBarIndep);
        textViewSeekValue = findViewById(R.id.textViewSeekValue);
        checkGhana = findViewById(R.id.checkBoxGhana);
        checkZoulou = findViewById(R.id.checkBoxZoulou);
        checkEcrivain = findViewById(R.id.checkBoxEcrivain);
        checkMusicien = findViewById(R.id.checkBoxMusicien);
        checkAuteur = findViewById(R.id.checkBoxAuteur);
        autoCompleteFemme = findViewById(R.id.autoCompleteFemme);
        buttonPrecedent = findViewById(R.id.button14);
        buttonSuivant = findViewById(R.id.button20);


        // Spinner
        String[] paysOptions = {"Éthiopie", "Libéria", "Maroc", "Kenya", "Les deux premiers"};
        ArrayAdapter<String> paysAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paysOptions);
        paysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPays.setAdapter(paysAdapter);


        // SeekBar
        String[] decades = {"1940", "1950", "1960", "1970", "1980"};
        seekBarIndep.setMax(decades.length - 1);
        seekBarIndep.setProgress(2);
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


        // AutoCompleteTextView
        String[] femmesNobel = {"Wangari Maathai", "Leymah Gbowee", "Ellen Johnson Sirleaf"};
        ArrayAdapter<String> femmeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, femmesNobel);
        autoCompleteFemme.setAdapter(femmeAdapter);


        // Bouton Suivant avec validation
        buttonSuivant.setOnClickListener(v -> {
            if (isFormComplete()) {
                saveToSharedPreferences();
                Intent intent = new Intent(Activity6.this, Activity7.class);
                startActivity(intent);
            } else {
                Toast.makeText(Activity6.this, "Veuillez remplir tous les champs avant de continuer.", Toast.LENGTH_SHORT).show();
            }
        });


        // Bouton Précédent
        buttonPrecedent.setOnClickListener(v -> {
            saveToSharedPreferences();
            startActivity(new Intent(Activity6.this, Activity5.class));
        });


        // Charger les valeurs précédemment sauvegardées si existantes
        loadFromSharedPreferences();
    }


    private boolean isFormComplete() {
        // Vérifier RadioGroup
        if (radioGroupMandela.getCheckedRadioButtonId() == -1) return false;
        // Vérifier Spinner
        if (spinnerPays.getSelectedItem() == null || spinnerPays.getSelectedItem().toString().isEmpty()) return false;        // Vérifier CheckBoxes (au moins 1)
        if (!checkGhana.isChecked() && !checkZoulou.isChecked() && !checkEcrivain.isChecked() && !checkMusicien.isChecked() && !checkAuteur.isChecked()) return false;
        // Vérifier AutoCompleteTextView non vide
        return !autoCompleteFemme.getText().toString().trim().isEmpty();
    }


    private void saveToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();


        // RadioGroup
        int selectedMandelaId = radioGroupMandela.getCheckedRadioButtonId();
        RadioButton selectedRadio = findViewById(selectedMandelaId);
        editor.putString("mandela", selectedRadio.getText().toString());


        // Spinner
        editor.putString("pays", spinnerPays.getSelectedItem().toString());


        // SeekBar
        editor.putInt("decennie", seekBarIndep.getProgress());


        // CheckBoxes
        Set<String> checkedItems = new HashSet<>();
        if (checkGhana.isChecked()) checkedItems.add("Ghana");
        if (checkZoulou.isChecked()) checkedItems.add("Zoulou");
        if (checkEcrivain.isChecked()) checkedItems.add("Ecrivain");
        if (checkMusicien.isChecked()) checkedItems.add("Musicien");
        if (checkAuteur.isChecked()) checkedItems.add("Auteur");
        editor.putStringSet("checkBoxes", checkedItems);


        // AutoCompleteTextView
        editor.putString("femmeNobel", autoCompleteFemme.getText().toString());


        editor.apply();
    }


    private void loadFromSharedPreferences() {
        // RadioGroup
        String mandela = sharedPreferences.getString("mandela", "");
        for (int i = 0; i < radioGroupMandela.getChildCount(); i++) {
            RadioButton rb = (RadioButton) radioGroupMandela.getChildAt(i);
            if (rb.getText().toString().equals(mandela)) {
                rb.setChecked(true);
                break;
            }
        }


        // Spinner
        String pays = sharedPreferences.getString("pays", "");
        ArrayAdapter adapter = (ArrayAdapter) spinnerPays.getAdapter();
        int position = adapter.getPosition(pays);
        if (position >= 0) spinnerPays.setSelection(position);


        // SeekBar
        int decennie = sharedPreferences.getInt("decennie", 2);
        seekBarIndep.setProgress(decennie);


        // CheckBoxes
        Set<String> checkBoxes = sharedPreferences.getStringSet("checkBoxes", new HashSet<>());
        checkGhana.setChecked(checkBoxes.contains("Ghana"));
        checkZoulou.setChecked(checkBoxes.contains("Zoulou"));
        checkEcrivain.setChecked(checkBoxes.contains("Ecrivain"));
        checkMusicien.setChecked(checkBoxes.contains("Musicien"));
        checkAuteur.setChecked(checkBoxes.contains("Auteur"));


        // AutoCompleteTextView
        autoCompleteFemme.setText(sharedPreferences.getString("femmeNobel", ""));
    }
}





