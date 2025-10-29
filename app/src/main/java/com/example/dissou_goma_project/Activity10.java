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
        // 🧭 Interprétation des réponses Activity1_1
        String reponseQ1, reponseQ2, reponseQ3;

// Q1 – Connaissance du continent africain
        switch (q1_afrique) {
            case 1:
                reponseQ1 = "🌍 Tu connais déjà beaucoup de choses sur l’Afrique !";
                break;
            case 2:
                reponseQ1 = "🧭 Tu as quelques notions, mais tu es curieux d’en apprendre plus.";
                break;
            case 3:
                reponseQ1 = "❓ Tu découvres encore ce vaste continent — l’aventure commence !";
                break;
            default:
                reponseQ1 = "— Réponse non renseignée —";
        }

/// Q2 – Rêve de visiter l’Afrique
        if (q2_reve_afrique >= 8) {
            reponseQ2 = "✨ Tu rêves absolument de visiter l’Afrique, quelle belle motivation !";
        } else if (q2_reve_afrique >= 4) {
            reponseQ2 = "🌅 Tu aimerais y aller un jour, l’idée te séduit déjà.";
        } else if (q2_reve_afrique >= 0) {
            reponseQ2 = "💭 Ce n’est pas encore dans tes projets, mais qui sait ?";
        } else {
            reponseQ2 = "— Réponse non renseignée —";
        }

// Q3 – Nombre de continents
        if (q3_continents == 7) {
            reponseQ3 = "✅ Tu as bien répondu : il y a 7 continents sur Terre !";
        } else if (q3_continents > 0) {
            reponseQ3 = "🌎 Tu as répondu " + q3_continents + " continents — pas loin !";
        } else {
            reponseQ3 = "❓ Réponse non fournie.";
        }





        // 🔹 Activity2
        SharedPreferences prefs2 = getSharedPreferences("safariPrefs", MODE_PRIVATE);
        int selectionAnimal = prefs2.getInt("selectionAnimal", -1);
        int valeurSeekBar = prefs2.getInt("valeurSeekBar", -1);
        // 🦒 Interprétation des réponses Activity2 – Safari des Curiosités
        String[] animauxSafari = {
                "❌ Aucun animal sélectionné",
                "🦁 Le lion — symbole de puissance et de noblesse",
                "🐆 Le guépard — rapide et agile, roi de la savane",
                "🦌 La gazelle — élégante et vive",
                "🐦 L’autruche — surprenante et pleine d’énergie"
        };

        String animalChoisiTexte = (selectionAnimal >= 0 && selectionAnimal < animauxSafari.length)
                ? animauxSafari[selectionAnimal]
                : "❓ Réponse non enregistrée";

        String estimationDeserts;
        if (valeurSeekBar >= 10) {
            estimationDeserts = "🔥 Tu penses qu’il existe plus de 10 déserts — quelle imagination !";
        } else if (valeurSeekBar >= 6) {
            estimationDeserts = "🏜️ Tu estimes environ " + valeurSeekBar + " déserts — un vrai explorateur !";
        } else if (valeurSeekBar >= 3) {
            estimationDeserts = "🌾 Tu supposes " + valeurSeekBar + " déserts — une estimation réaliste.";
        } else if (valeurSeekBar >= 0) {
            estimationDeserts = "🌵 Seulement " + valeurSeekBar + " ? L’Afrique te réserve bien des surprises !";
        } else {
            estimationDeserts = "❓ Aucune estimation donnée.";
        }

        // 🔹 Activity3
        SharedPreferences prefs3 = getSharedPreferences("MesPreferences", MODE_PRIVATE);
        String danseChoisie = prefs3.getString("danse_choisie", "Aucune");
        String commentaireDanse;
        switch (danseChoisie) {
            case "Salsa":
                commentaireDanse = "💃 Tu adores le rythme et l'énergie de la Salsa !";
                break;
            case "Hip-hop":
                commentaireDanse = "🕺 Ton style Hip-hop montre ton côté créatif et dynamique !";
                break;
            case "Afrobeat":
                commentaireDanse = "🎶 L'Afrobeat fait battre ton cœur au rythme du continent !";
                break;
            default:
                commentaireDanse = "🩰 Une danse originale, reflet de ta personnalité unique !";
                break;
        }



        // 🔹 Activity4
        SharedPreferences prefs4 = getSharedPreferences("Activity4Prefs", MODE_PRIVATE);
        boolean spicySwitch = prefs4.getBoolean("spicySwitch", false);
        int rating = prefs4.getInt("rating", 0);
        String baseFood = prefs4.getString("baseFood", "Aucun");
        String drink = prefs4.getString("drink", "Aucune");
        String ingredient = prefs4.getString("ingredient", "Aucun");

        switch (danseChoisie) {
            case "Salsa":
                commentaireDanse = "💃 Tu adores le rythme et l'énergie de la Salsa !";
                break;
            case "Hip-hop":
                commentaireDanse = "🕺 Ton style Hip-hop montre ton côté créatif et dynamique !";
                break;
            case "Afrobeat":
                commentaireDanse = "🎶 L'Afrobeat fait battre ton cœur au rythme du continent !";
                break;
            default:
                commentaireDanse = "🩰 Une danse originale, reflet de ta personnalité unique !";
                break;
        }




        // 🔹 Activity5
        SharedPreferences prefs5 = getSharedPreferences("Activity5Prefs", MODE_PRIVATE);
        String agePyramides = prefs5.getString("agePyramides", "Non défini");
        String nbPyramides = prefs5.getString("nbPyramides", "Non défini");
        String dernierePharaonne = prefs5.getString("dernierePharaonne", "Aucune");
        String animauxSacres = prefs5.getString("animauxSacres", "Aucun");
        String teteSphinx = prefs5.getString("teteSphinx", "Aucun");


// 🔹 Activity6
        SharedPreferences prefs6 = getSharedPreferences("Activity6Prefs", MODE_PRIVATE);
        String mandela = prefs6.getString("mandela", "");
        String paysIndep = prefs6.getString("pays", "");
        int decennie = prefs6.getInt("decennie", 0);
        Set<String> checkBoxes = prefs6.getStringSet("checkBoxes", new HashSet<>());
        String femmeNobel = prefs6.getString("femmeNobel", "");






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
        String ambiance = prefs9.getString("ambiance", "Aucune");
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


        String bilanComplet =
                "══════════════════════════════════\n" +
                        "🎖️  PROFIL D’EXPLORATEUR AFRICAIN\n" +
                        "══════════════════════════════════\n\n" +

                        "👤  [Activity 1]\n" +
                        String.format("• Nom                : %s\n", nom) +
                        String.format("• Âge                : %s\n", age) +
                        String.format("• Sexe               : %s\n", sexe) +
                        String.format("• Héros choisi       : %s\n\n", heroChoisi) +

                        "🎖️ Activity1_1 – Tes premières impressions :\n"
                        + "• " + reponseQ1 + "\n"
                        + "• " + reponseQ2 + "\n"
                        + "• " + reponseQ3 + "\n\n"

                        + "🦒 Activity2 – Safari des Curiosités :\n"
                        + "• Animal choisi : " + animalChoisiTexte + "\n"
                        + "• Estimation des déserts : " + estimationDeserts + "\n\n"+


                        "🎖️ [Activity3] :\nDanse choisie : " + danseChoisie + "\n" + commentaireDanse + "\n\n"+

                        "🍽️  [Activity 4]\n" +
                        String.format("• Spicy              : %b\n", spicySwitch) +
                        String.format("• Note               : %d\n", rating) +
                        String.format("• Plat de base       : %s\n", baseFood) +
                        String.format("• Boisson            : %s\n", drink) +
                        String.format("• Ingrédient secret  : %s\n\n", ingredient) +

                        "🏺  [Activity 5/6]\n" +
                        String.format("• Âge des pyramides  : %s\n", agePyramides) +
                        String.format("• Nb Pyramides       : %s\n", nbPyramides) +
                        String.format("• Dernière Pharaonne : %s\n", dernierePharaonne) +
                        String.format("• Animaux sacrés     : %s\n", animauxSacres) +
                        String.format("• Tête du Sphinx     : %s\n", teteSphinx) +
                        String.format("• Mandela            : %s\n", mandela) +
                        String.format("• Pays Indep.        : %s\n", paysIndep) +
                        String.format("• Décennie           : %d\n", decennie) +
                        String.format("• Femme Nobel        : %s\n\n", femmeNobel) +

                        "🏞️  [Activity 7]\n" +
                        String.format("• Merveille          : %s\n", merveille) +
                        String.format("• Paysage            : %s\n", paysage) +
                        String.format("• Kilimandjaro       : %s\n", kilimandjaro) +
                        String.format("• Désert             : %s\n", desert) +
                        String.format("• Pays Chutes        : %s\n\n", paysChutes) +

                        "🐘  [Activity 8]\n" +
                        String.format("• Totem              : %s\n", totem) +
                        String.format("• Nb Éléphants       : %s\n", nbElephants) +
                        String.format("• Animal Dangereux   : %s\n\n", animalDangereux) +

                        "🏙️  [Activity 9]\n" +
                        String.format("• Ambiance           : %s\n", ambiance) +
                        String.format("• Capitale           : %s\n", capitale) +
                        String.format("• Marrakech          : %s\n", marrakech) +
                        String.format("• Ville              : %s\n\n", ville) +

                        "══════════════════════════════════\n" +
                        "🔎  PROFIL DÉTECTÉ\n" +
                        "══════════════════════════════════\n\n" +
                        descriptionProfil;


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




