package de.bcxp.challenge;

import de.bcxp.challenge.helpers.Reader;
import de.bcxp.challenge.helpers.Calculate;
import de.bcxp.challenge.helpers.UIComponents;

import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static de.bcxp.challenge.helpers.UIComponents.userChoice;

/**
 * Dieses Java-Programm ermöglicht es dem Benutzer, einen Dateipfad zu einer CSV-Datei einzugeben, aus der dann
 * Daten ausgelesen und in einer Liste gespeichert werden. Basierend auf der Auswahl von zwei Werten und einer
 * mathematischen Operation durch den Benutzer, berechnet das Programm dann die Zeile mit der größten Differenz
 * basierend auf der ausgewählten Operation und gibt diese aus.
 */
public final class App {

    /**
     * Diese Methode steuert das Programm
     *
     * Ablauf:
     * 1. Eingabe des Datei-Pfads durch den Benutzer
     * 2. Auslesen der eingegebenen Datei (CSV)
     * 3. Eingabe der zu berechnenden Spalten durch den Benutzer
     * 4. Berechnung (je nach Benutzerwahl max oder min)
     * 5. Ausgabe des Ergebnisses und Frage, ob weitere Dateien ausgelesen werden sollen
     * (6.) -> zurück zu Punkt 1. wenn weitere Dateien ausgelesen werden sollen
     */
    public void process(){

        // User Eingabe der auszulesenden Datei per Dialogfenster
        UIComponents uiComponents = new UIComponents();
        String filePath = uiComponents.createUIComponents();


        // Auslesen der Datei
        Reader reader = new Reader(filePath);
        List<String> dataList = reader.readCSV();

        // Eingabe des Benutzers der zu berechnenden Zeilen und auswahl der mathematischen Operation
        Object[] calculateInfos = uiComponents.chooseValuesAndOperation(dataList);

        // Maximum oder Mimimum berechnen und rückgabe des Maximums
        Calculate calculate = new Calculate();

        String claculate = null;
        if(calculateInfos[3].equals("Maximum")){
            claculate = calculate.max(dataList, (int) calculateInfos[0], (int) calculateInfos[1], (String) calculateInfos[2]);
        }
        else if(calculateInfos[3].equals("Minimum")){
            claculate = calculate.min(dataList, (int) calculateInfos[0], (int) calculateInfos[1], (String) calculateInfos[2]);
        }
        else{
            logger.warn("Unerlaubter Wert. Erlaubt: Maximum und Minimum");
        }

        // Ausgabe des Maximums und Frage, ob eine neue Datei eingelesen werden soll
        boolean choice = userChoice(claculate, "Möchtest du neue Berechnungen durchführen?");
        if (choice) {
            process(); // Ja, die Verarbeitung beginnt von vorne
        } else {
            System.exit(0); // Nein, das Programm wird verlassen
        }
    }

    /**
     * Startpunkt des Programms
     * Loggen der Start, Endzeit und Durchlaufzeit
     *
     * @param args --> keine Informationen enthalten
     */
    public static void main(String... args) throws IOException {

        // Programmstart-Zeitstempel
        long startTime = System.currentTimeMillis();
        logger.info("Programm gestartet");

        // Verarbeitungsbeginn
        App app = new App();
        app.process();

        // Programmende-Zeitstempel
        long endTime = System.currentTimeMillis();
        logger.info("Programm beendet");

        // Durchlaufzeit berechnen
        long elapsedTime = endTime - startTime;
        logger.info("Durchlaufzeit: " + elapsedTime + " Millisekunden");
    }

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(App.class.getName());


}
