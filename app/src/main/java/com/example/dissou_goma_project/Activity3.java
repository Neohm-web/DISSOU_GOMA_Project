package com.example.dissou_goma_project;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Boutons navigation
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(v -> {
            Intent intent7 = new Intent(Activity3.this, Activity4.class);
            startActivity(intent7);
        });
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(v -> {
            Intent intent8 = new Intent(Activity3.this, Activity2.class);
            startActivity(intent8);
        });

        // ToggleButtons - assurer que le texte est visible
        int[] toggleIds = {R.id.toggleButton, R.id.toggleButton2, R.id.toggleButton3,
                R.id.toggleButton4, R.id.toggleButton5, R.id.toggleButton6};
        String[] toggleTexts = {"Ewe", "Lingala", "Anglais", "Français", "Arabe", "Swahili"};

        for (int i = 0; i < toggleIds.length; i++) {
            ToggleButton toggle = findViewById(toggleIds[i]);
            toggle.setTextOn(toggleTexts[i]);
            toggle.setTextOff(toggleTexts[i]);
            toggle.setChecked(false);

            // --- Couleur selon la langue ---
            toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
                String text = buttonView.getText().toString();

                if (isChecked) {
                    // Si activé → on change la couleur selon la langue
                    if (text.equals("Ewe") || text.equals("Lingala") || text.equals("Swahili")) {
                        buttonView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    } else {
                        buttonView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    }
                } else {
                    // Si désactivé → on remet la couleur par défaut
                    buttonView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
            });
        }

        // ListView musics
        listViewMusics = findViewById(R.id.listViewMusics);
        String[] musics = {"Blues", "Jazz", "Reggae", "Salsa", "Hip-hop", "Rock"};
        ArrayAdapter<String> adapterMusics = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,
                musics);
        listViewMusics.setAdapter(adapterMusics);
        listViewMusics.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Ajuster la hauteur ListView pour afficher tous les éléments
        setListViewHeightBasedOnChildren(listViewMusics);

        // Spinner danses africaines
        spinner3 = findViewById(R.id.spinner3);
        String[] dances = {"Coupé-décalé", "Ndombolo", "Azonto", "Gwara Gwara", "Kizomba", "Aucune mais je suis curieux !"};
        ArrayAdapter<String> adapterDances = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                dances);
        adapterDances.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapterDances);

        ChipGroup chipGroup = findViewById(R.id.chipGroup);
        if (chipGroup != null) {
            for (int i = 0; i < chipGroup.getChildCount(); i++) {
                View v = chipGroup.getChildAt(i);
                if (v instanceof Chip) {
                    Chip chip = (Chip) v;

                    // Couleur initiale
                    if (chip.isChecked()) {
                        chip.setChipBackgroundColorResource(R.color.chip_checked);
                        chip.setTextColor(getResources().getColor(R.color.text_light));
                    } else {
                        chip.setChipBackgroundColorResource(R.color.chip_unchecked);
                        chip.setTextColor(getResources().getColor(R.color.text_dark));
                    }

                    // Nouveau comportement : sélection / désélection naturelle
                    chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (isChecked) {
                            chip.setChipBackgroundColorResource(R.color.chip_checked);
                            chip.setTextColor(getResources().getColor(R.color.text_light));
                        } else {
                            chip.setChipBackgroundColorResource(R.color.chip_unchecked);
                            chip.setTextColor(getResources().getColor(R.color.text_dark));
                        }
                    });
                }
            }
        }

    }


    // Méthode pour ajuster la hauteur ListView en fonction des items
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        if (adapter == null) {
            return;
        }
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