package com.example.dissou_goma_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_10);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Récupération du nom de l’utilisateur ---
        SharedPreferences prefs = getSharedPreferences("ExplorateurData", MODE_PRIVATE);
        String nom = prefs.getString("nomUtilisateur", "Explorateur Mystère");

        TextView txtNom = findViewById(R.id.txtNom);
        txtNom.setText("🎉 Félicitations " + nom + " !");

        TextView txtMessageFinal = findViewById(R.id.txtMessageFinal);
        txtMessageFinal.setText("Vous êtes arrivé au bout de cette exploration de l’Afrique ! 🌍\n"
                + "Merci pour votre curiosité et votre enthousiasme.\n\n"
                + "Découvrez maintenant quelques lectures conseillées pour continuer le voyage 📚");

        // --- Section lectures conseillées ---
        LinearLayout layoutLectures = findViewById(R.id.layoutLectures);
        Button btnConseilLecture = findViewById(R.id.btnConseilLecture);

        btnConseilLecture.setOnClickListener(v -> {
            if (layoutLectures.getVisibility() == View.GONE) {
                layoutLectures.setVisibility(View.VISIBLE);
                btnConseilLecture.setText("Masquer les lectures 📕");
            } else {
                layoutLectures.setVisibility(View.GONE);
                btnConseilLecture.setText("Voir les lectures conseillées 📖");
            }
        });

        // --- Message d’arrivée ---
        Toast.makeText(this, "👏 Bravo " + nom + " pour ton parcours complet !", Toast.LENGTH_LONG).show();
    }
}
