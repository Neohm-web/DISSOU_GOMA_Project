package com.example.dissou_goma_project;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;


public class Activity10 extends AppCompatActivity {

    private Button buttonCalculer;
    private TextView textResultat;
    private ImageView imageProfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_10);


        buttonCalculer = findViewById(R.id.buttonCalculer);
        textResultat = findViewById(R.id.textResultat);
        imageProfil = findViewById(R.id.imageProfil);


        buttonCalculer.setOnClickListener(v -> afficherProfilFinal());
    }


    private void afficherProfilFinal() {
        SharedPreferences prefs = getSharedPreferences("ExplorateurData", MODE_PRIVATE);

        // 🔹 Activity1
        String nom = prefs.getString("nom", "Explorateur inconnu");
        String age = prefs.getString("age", "Non défini");
        String sexe = prefs.getString("sexe", "Non défini");
        String heroChoisi = prefs.getString("hero_choisi", "Aucun");

        // 🔹 Activity1_1
        int q1_afrique = prefs.getInt("q1_afrique", -1);
        int q2_reve_afrique = prefs.getInt("q2_reve_afrique", -1);
        int q3_continents = prefs.getInt("q3_continents", -1);

        // 🔹 Activity2
        int selectionAnimal = prefs.getInt("selectionAnimal", -1);
        int valeurSeekBar = prefs.getInt("valeurSeekBar", -1);

        // 🔹 Activity3
        SharedPreferences prefs3 = getSharedPreferences("MesPreferences", MODE_PRIVATE);
        String danseChoisie = prefs3.getString("danse_choisie", "Aucune");

        // 🔹 Activity4
        SharedPreferences prefs4 = getSharedPreferences("Activity4Prefs", MODE_PRIVATE);
        boolean spicySwitch = prefs4.getBoolean("spicySwitch", false);
        int rating = prefs4.getInt("rating", 0);
        String baseFood = prefs4.getString("baseFood", "Aucun");
        String drink = prefs4.getString("drink", "Aucune");
        String ingredient = prefs4.getString("ingredient", "Aucun");

        // 🔹 Activity5 & Activity6 (même SharedPreferences)
        SharedPreferences prefs56 = getSharedPreferences("Activity5Prefs", MODE_PRIVATE);
        String agePyramides = prefs56.getString("agePyramides", "Non défini");
        String nbPyramides = prefs56.getString("nbPyramides", "Non défini");
        String dernierePharaonne = prefs56.getString("dernierePharaonne", "Aucune");
        String animauxSacres = prefs56.getString("animauxSacres", "Aucun");
        String teteSphinx = prefs56.getString("teteSphinx", "Aucun");

        // 🔹 Activity6 suite
        String mandela = prefs56.getString("mandela", "");
        String paysIndep = prefs56.getString("pays", "");
        int decennie = prefs56.getInt("decennie", 0);
        Set<String> checkBoxes = prefs56.getStringSet("checkBoxes", new HashSet<>());
        String femmeNobel = prefs56.getString("femmeNobel", "");

        // 🔹 Activity7
        SharedPreferences prefs7 = getSharedPreferences("Activity10Prefs", MODE_PRIVATE);
        String merveille = prefs7.getString("merveille", "Aucune");
        String paysage = prefs7.getString("paysage", "Aucun");
        String kilimandjaro = prefs7.getString("kilimandjaro", "—");
        String desert = prefs7.getString("desert", "Aucun");
        String paysChutes = prefs7.getString("paysChutes", "—");

        // 🔹 Activity8
        SharedPreferences prefs8 = getSharedPreferences("Activity8Prefs", MODE_PRIVATE);
        String totem = prefs8.getString("totem", "Aucun");
        String nbElephants = prefs8.getString("nbElephants", "—");
        String animalDangereux = prefs8.getString("animalDangereux", "Aucun");

        // 🔹 Activity9
        SharedPreferences prefs9 = getSharedPreferences("Activity9Prefs", MODE_PRIVATE);
        String capitale = prefs9.getString("capitale", "—");
        String marrakech = prefs9.getString("marrakech", "—");
        String ville = prefs9.getString("ville", "—");

        // 🔹 Profils calculés à partir de Activity2/3/4/5 etc.
        String interet = prefs.getString("interet", "Non défini");
        String musique = prefs.getString("musique_preferee", "Aucune");
        String sport = prefs.getString("sport_prefere", "Aucun");
        String animal = prefs.getString("animal_prefere", "Aucun");
        float noteGastro = prefs.getFloat("note_gastronomie", 0f);
        String paysPrefere = prefs.getString("pays_prefere", "—");

        int scoreAventurier = 0, scoreArtiste = 0, scoreGourmet = 0, scoreSage = 0, scorePaisible = 0;

        if (interet.contains("animaux") || animal.equalsIgnoreCase("Lion")) scoreAventurier += 2;
        if (musique.equalsIgnoreCase("Reggae") || musique.equalsIgnoreCase("Jazz") || musique.equalsIgnoreCase("Hip-hop")) scoreArtiste += 2;
        if (noteGastro >= 4) scoreGourmet += 2;
        if (interet.contains("histoire") || interet.contains("civilisation")) scoreSage += 2;
        if (sport.equalsIgnoreCase("Football") || sport.equalsIgnoreCase("Evala")) scoreAventurier += 1;
        if (animal.equalsIgnoreCase("Éléphant") || interet.contains("sagesse")) scoreSage += 1;
        if (musique.equalsIgnoreCase("Salsa") || musique.equalsIgnoreCase("Afrobeat")) scoreArtiste += 1;
        if (noteGastro <= 2) scorePaisible += 1;

        String profil;
        int imageResId;
        if (scoreAventurier >= scoreArtiste && scoreAventurier >= scoreGourmet && scoreAventurier >= scoreSage && scoreAventurier >= scorePaisible) {
            profil = "🦁 L’Aventurier intrépide";
            imageResId = R.drawable.aventurier;
        } else if (scoreArtiste >= scoreAventurier && scoreArtiste >= scoreGourmet && scoreArtiste >= scoreSage) {
            profil = "🎶 L’Artiste du monde";
            imageResId = R.drawable.artiste;
        } else if (scoreGourmet >= scoreArtiste && scoreGourmet >= scoreAventurier && scoreGourmet >= scoreSage) {
            profil = "🍲 Le Gourmet curieux";
            imageResId = R.drawable.gourmet;
        } else if (scoreSage >= scoreArtiste && scoreSage >= scoreAventurier && scoreSage >= scoreGourmet) {
            profil = "📜 Le Sage du désert";
            imageResId = R.drawable.sage;
        } else {
            profil = "🌍 Le Voyageur paisible";
            imageResId = R.drawable.voyageur;
        }

        String descriptionProfil = genererDescriptionProfil(profil, nom, paysPrefere);

        // 🔹 Construire le bilan complet
        String bilanComplet = "🎖️ Activity1 :\nNom : " + nom + "\nÂge : " + age + "\nSexe : " + sexe + "\nHéros choisi : " + heroChoisi + "\n\n"
                + "🎖️ Activity1_1 :\nQ1 Afrique : " + q1_afrique + "\nQ2 Rêve Afrique : " + q2_reve_afrique + "\nQ3 Continents : " + q3_continents + "\n\n"
                + "🎖️ Activity2 :\nAnimal sélectionné : " + selectionAnimal + "\nValeur SeekBar : " + valeurSeekBar + "\n\n"
                + "🎖️ Activity3 :\nDanse choisie : " + danseChoisie + "\n\n"
                + "🎖️ Activity4 :\nSpicy : " + spicySwitch + "\nRating : " + rating + "\nBase Food : " + baseFood + "\nDrink : " + drink + "\nIngredient : " + ingredient + "\n\n"
                + "🎖️ Activity5/6 :\nÂge pyramides : " + agePyramides + "\nNb Pyramides : " + nbPyramides + "\nDernière Pharaonne : " + dernierePharaonne
                + "\nAnimaux sacrés : " + animauxSacres + "\nTête Sphinx : " + teteSphinx
                + "\nMandela : " + mandela + "\nPays Indep : " + paysIndep + "\nDécennie : " + decennie
                + "\nCheckBoxes : " + checkBoxes + "\nFemme Nobel : " + femmeNobel + "\n\n"
                + "🎖️ Activity7 :\nMerveille : " + merveille + "\nPaysage : " + paysage + "\nKilimandjaro : " + kilimandjaro
                + "\nDésert : " + desert + "\nPays Chutes : " + paysChutes + "\n\n"
                + "🎖️ Activity8 :\nTotem : " + totem + "\nNb Elephants : " + nbElephants + "\nAnimal Dangereux : " + animalDangereux + "\n\n"
                + "🎖️ Activity9 :\nCapitale : " + capitale + "\nMarrakech : " + marrakech + "\nVille : " + ville + "\n\n"
                + "🎖️ Profil détecté :\n" + descriptionProfil;

        textResultat.setText(bilanComplet);
        imageProfil.setImageResource(imageResId);
    }




    /**
     * Retourne une description personnalisée selon le profil détecté.
     */
    private String genererDescriptionProfil(String profil, String nom, String pays) {
        switch (profil) {
            case "🦁 L’Aventurier intrépide":
                return "🦁 " + nom + ", tu es un explorateur courageux, toujours prêt à braver les mystères d’Afrique !\n"
                        + "Ton pays préféré, " + pays + ", t’attend pour une aventure hors du commun !";


            case "🎶 L’Artiste du monde":
                return "🎶 " + nom + ", ton cœur bat au rythme du continent ! La musique et la créativité t’unissent à l’Afrique.";


            case "🍲 Le Gourmet curieux":
                return "🍲 " + nom + ", les saveurs africaines t’ont conquis ! Entre épices et traditions, ton voyage se savoure à chaque plat.";


            case "📜 Le Sage du désert":
                return "📜 " + nom + ", passionné d’histoire et de culture, tu es la mémoire vivante de l’Afrique ancienne.";


            default:
                return "🌍 " + nom + ", tu es un voyageur paisible, curieux de tout et prêt à découvrir les mille visages du continent africain.";
        }
    }
}

