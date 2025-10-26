package com.example.dissou_goma_project;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;


import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


public class Activity3 extends AppCompatActivity {


    private ListView listViewMusics;
    private Spinner spinner3;
    private boolean auMoinsUneLangue = false;
    private boolean auMoinsUnStyleMusical = false;
    private boolean auMoinsUnChip = false;
    private boolean danseChoisie = false;


    // SharedPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);


        sharedPreferences = getSharedPreferences("MesPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // --- Boutons navigation ---
        Button buttonSuivant = findViewById(R.id.button6);
        Button buttonPrecedent = findViewById(R.id.button5);


        // Retour
        buttonPrecedent.setOnClickListener(v -> {
            Toast.makeText(this, "⬅️ Retour à l’activité précédente", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity3.this, Activity2.class));
        });


        // --- TOGGLE BUTTONS : langues ---
        int[] toggleIds = {R.id.toggleButton, R.id.toggleButton2, R.id.toggleButton3,
                R.id.toggleButton4, R.id.toggleButton5, R.id.toggleButton6};
        String[] toggleTexts = {"Ewe", "Lingala", "Anglais", "Français", "Arabe", "Swahili"};


        for (int i = 0; i < toggleIds.length; i++) {
            ToggleButton toggle = findViewById(toggleIds[i]);
            toggle.setTextOn(toggleTexts[i]);
            toggle.setTextOff(toggleTexts[i]);
            toggle.setChecked(sharedPreferences.getBoolean(toggleTexts[i], false));
            toggle.setBackgroundColor(toggle.isChecked() ?
                    getResources().getColor(android.R.color.holo_green_light) :
                    getResources().getColor(android.R.color.darker_gray));


            toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
                String text = buttonView.getText().toString();
                if (isChecked) {
                    buttonView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    auMoinsUneLangue = true;
                } else {
                    buttonView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                    // Vérifie s’il reste une langue cochée
                    boolean encoreUne = false;
                    for (int id : toggleIds) {
                        ToggleButton t = findViewById(id);
                        if (t.isChecked()) {
                            encoreUne = true;
                            break;
                        }
                    }
                    auMoinsUneLangue = encoreUne;
                }
                editor.putBoolean(text, isChecked);
                editor.apply();
            });
        }


        // --- LISTVIEW : styles de musique ---
        listViewMusics = findViewById(R.id.listViewMusics);
        String[] musics = {"Blues", "Jazz", "Reggae", "Salsa", "Hip-hop", "Rock"};
        ArrayAdapter<String> adapterMusics = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, musics);
        listViewMusics.setAdapter(adapterMusics);
        listViewMusics.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        setListViewHeightBasedOnChildren(listViewMusics);


        // Restaurer les choix depuis SharedPreferences
        for (int i = 0; i < musics.length; i++) {
            if (sharedPreferences.getBoolean("music_" + musics[i], false)) {
                listViewMusics.setItemChecked(i, true);
                auMoinsUnStyleMusical = true;
            }
        }


        listViewMusics.setOnItemClickListener((parent, view, position, id) -> {
            boolean checked = listViewMusics.isItemChecked(position);
            editor.putBoolean("music_" + musics[position], checked);
            editor.apply();


            // Vérifie s’il y a au moins un élément sélectionné
            auMoinsUnStyleMusical = false;
            for (int i = 0; i < musics.length; i++) {
                if (listViewMusics.isItemChecked(i)) {
                    auMoinsUnStyleMusical = true;
                    break;
                }
            }
        });


        // --- SPINNER : danses africaines ---
        spinner3 = findViewById(R.id.spinner3);
        String[] dances = {"Coupé-décalé", "Ndombolo", "Azonto", "Gwara Gwara", "Kizomba", "Aucune mais je suis curieux !"};
        ArrayAdapter<String> adapterDances = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dances);
        adapterDances.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterDances);


        // Restaurer danse choisie
        String danseSaved = sharedPreferences.getString("danse_choisie", "");
        if (!danseSaved.isEmpty()) {
            for (int i = 0; i < dances.length; i++) {
                if (dances[i].equals(danseSaved)) {
                    spinner3.setSelection(i);
                    danseChoisie = true;
                    break;
                }
            }
        }


        spinner3.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                danseChoisie = true;
                editor.putString("danse_choisie", dances[position]);
                editor.apply();
            }


            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                danseChoisie = false;
            }
        });


        // --- CHIPGROUP : religions ---
        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        if (chipGroup != null) {
            for (int i = 0; i < chipGroup.getChildCount(); i++) {
                View v = chipGroup.getChildAt(i);
                if (v instanceof Chip) {
                    Chip chip = (Chip) v;
                    String religion = chip.getText().toString();
                    chip.setChecked(sharedPreferences.getBoolean("religion_" + religion, false));
                    chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        editor.putBoolean("religion_" + religion, isChecked);
                        editor.apply();


                        // Vérifie s’il reste au moins un chip sélectionné
                        auMoinsUnChip = false;
                        for (int j = 0; j < chipGroup.getChildCount(); j++) {
                            Chip c = (Chip) chipGroup.getChildAt(j);
                            if (c.isChecked()) {
                                auMoinsUnChip = true;
                                break;
                            }
                        }
                    });
                }
            }
        }


        // --- Bouton SUIVANT avec vérification avant de passer ---
        buttonSuivant.setOnClickListener(v -> {
            if (!auMoinsUneLangue) {
                Toast.makeText(this, "❗Veuillez sélectionner au moins une langue.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!auMoinsUnStyleMusical) {
                Toast.makeText(this, "❗Veuillez choisir au moins un style musical.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!auMoinsUnChip) {
                Toast.makeText(this, "❗Veuillez cocher au moins une religion.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!danseChoisie) {
                Toast.makeText(this, "❗Veuillez choisir une danse africaine.", Toast.LENGTH_SHORT).show();
                return;
            }


            Toast.makeText(this, "✅ Super ! Passage à la suite 🎉", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity3.this, Activity4.class));
        });
    }


    // Méthode pour ajuster la hauteur ListView
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        if (adapter == null) return;


        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}





