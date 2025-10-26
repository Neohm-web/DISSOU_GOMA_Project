package com.example.dissou_goma_project;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Activity4 extends AppCompatActivity {


    private Switch spicySwitch;
    private RatingBar ratingBar;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private ImageButton[] ingredientButtons;
    private ImageView selectedIngredientImage;
    private Button buttonSuivant;


    private String[] ingredients = {
            "Fonio", "Moringa", "Beurre de karité", "Poivre de Guinée", "Tamarin"
    };


    private int[] ingredientImages = {
            R.drawable.fonio, R.drawable.moringa,
            R.drawable.beurre_de_karite, R.drawable.poivre_de_guinee, R.drawable.tamarin
    };


    // SharedPreferences
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_4);


        sharedPreferences = getSharedPreferences("Activity4Prefs", MODE_PRIVATE);


        // Gestion EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Boutons navigation
        buttonSuivant = findViewById(R.id.button11);
        Button buttonPrecedent = findViewById(R.id.button10);


        buttonPrecedent.setOnClickListener(v -> {
            startActivity(new Intent(Activity4.this, Activity3.class));
        });


        buttonSuivant.setOnClickListener(v -> {
            if (areAllFieldsFilled()) {
                savePreferences();
                startActivity(new Intent(Activity4.this, Activity5.class));
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs avant de continuer", Toast.LENGTH_SHORT).show();
            }
        });


        // Switch : piment
        spicySwitch = findViewById(R.id.switch1);
        spicySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String msg = isChecked ? "🔥 Vous osez le piment !" : "❄️ Douceur avant tout !";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });


        // RatingBar : niveau de piquant
        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            Toast.makeText(this, "Niveau de piquant : " + (int) rating, Toast.LENGTH_SHORT).show();
        });


        // RadioGroup : aliment de base
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selected = findViewById(checkedId);
            if (selected != null) {
                Toast.makeText(this, "Aliment de base : " + selected.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        // Spinner : boisson
        spinner = findViewById(R.id.spinner);
        String[] drinks = {"Sodabi", "Café touba", "Tsamba", "Bissap", "Jus de baobab"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, drinks);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        // ImageView pour l’ingrédient sélectionné
        selectedIngredientImage = findViewById(R.id.selectedIngredientImage);


        // ImageButtons : ingrédients africains
        ingredientButtons = new ImageButton[5];
        int[] buttonIds = {
                R.id.imageButton1, R.id.imageButton2, R.id.imageButton3,
                R.id.imageButton4, R.id.imageButton5
        };


        for (int i = 0; i < ingredientButtons.length; i++) {
            int index = i;
            ingredientButtons[i] = findViewById(buttonIds[i]);


            ingredientButtons[i].setOnClickListener(v -> {
                selectedIngredientImage.setImageResource(ingredientImages[index]);
                selectedIngredientImage.setVisibility(ImageView.VISIBLE);


                Toast.makeText(this,
                        "Ingrédient sélectionné : " + ingredients[index],
                        Toast.LENGTH_SHORT).show();
            });
        }
    }


    // Vérifie si tous les champs ont été remplis
    private boolean areAllFieldsFilled() {
        boolean switchChecked = spicySwitch.isChecked() || !spicySwitch.isChecked(); // Toujours sélectionné
        boolean ratingSelected = ratingBar.getRating() > 0;
        boolean radioSelected = radioGroup.getCheckedRadioButtonId() != -1;
        boolean spinnerSelected = spinner.getSelectedItemPosition() != Spinner.INVALID_POSITION;
        boolean ingredientSelected = selectedIngredientImage.getVisibility() == ImageView.VISIBLE;


        return switchChecked && ratingSelected && radioSelected && spinnerSelected && ingredientSelected;
    }


    // Sauvegarde les réponses dans SharedPreferences
    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("spicySwitch", spicySwitch.isChecked());
        editor.putInt("rating", (int) ratingBar.getRating());


        int selectedRadioId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioId != -1) {
            RadioButton selectedRadio = findViewById(selectedRadioId);
            editor.putString("baseFood", selectedRadio.getText().toString());
        }


        editor.putString("drink", spinner.getSelectedItem().toString());


        // Ingrédient sélectionné
        for (int i = 0; i < ingredientButtons.length; i++) {
            if (selectedIngredientImage.getDrawable().getConstantState() ==
                    getResources().getDrawable(ingredientImages[i]).getConstantState()) {
                editor.putString("ingredient", ingredients[i]);
                break;
            }
        }


        editor.apply();
        Toast.makeText(this, "Réponses sauvegardées ✅", Toast.LENGTH_SHORT).show();
    }
}





