package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandTest {
    public static void main(String[] args) {
        String command = "calc.exe"; // Pour Windows, sur macOS/Linux utilise "open -a Calculator"

        try {
            System.out.println("Tentative d'exécution de la commande : " + command);
            Process process = Runtime.getRuntime().exec(command);

            // Lire et afficher la sortie de la commande
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Sortie commande : " + line);
            }

            // Lire et afficher les erreurs éventuelles
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println("Erreur commande : " + line);
            }

            // Attendre la fin du processus
            int exitCode = process.waitFor();
            System.out.println("Processus terminé avec le code de sortie : " + exitCode);

        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la commande : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
