package isep.fr.easyolympics.csv;

import isep.fr.easyolympics.model.DatabaseQueries;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CSVGenerator {

    public static void generateCSV(List<String> data) {
        String csvFileName = "medals_by_country.csv";

        try (FileWriter csvWriter = new FileWriter(csvFileName)) {
            // Écrire l'en-tête du CSV
            csvWriter.append("Country,Athlete,Medal\n");

            // Écrire les données dans le fichier CSV
            for (String line : data) {
                csvWriter.append(line).append("\n");
            }

            System.out.println("Fichier CSV généré avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la génération du fichier CSV : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        // Exemple de données
        List<String> data = DatabaseQueries.getMedals();

        // Générer le CSV
        generateCSV(data);
    }

    // Méthode pour simuler la récupération des données depuis la base de données
    public static List<String> getDataFromDatabase() {
        // Données simulées
        // Remplacez cela par votre propre logique de récupération de données
        // Ceci est juste un exemple
        return List.of(

        );
    }
}
