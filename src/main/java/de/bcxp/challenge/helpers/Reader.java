package de.bcxp.challenge.helpers;

import java.io.File;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Klasse zum Auslesen der gewünschten Datei des Benutzers
 */
public class Reader {

    /**
     * CSV-Datei wird eingelesen und in einer List<Sting> gespeichert
     * es findet vor dem Auslesen eine Prüfung statt, ob der filePath existiert und ob die CSV-Datei leer ist
     *
     * (Die Methode kann auch .txt-Dateien einlesen)
     *
     *  @return dataList --> ausgelesene CSV-Datei in einer List<String>
     */
    public List<String> readCSV() {
        List<String> dataList = new ArrayList<>();

        if(fileExist(filePath) == true){ // Prüfung Pfad vorhanden
            if(isCSVEmpty(filePath) == true){ // Prüfung Datei befüllt
                try {
                    // Auslesen der Datei
                    dataList  = Files.readAllLines(Paths.get(filePath));
                    for (String line : dataList) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    logger.error("Fehlermeldung: "+e);
                }

            }
        }
        logger.info("Die Daten wurden erfolgreich ausgelesen. Pfad: "+filePath);

        // ausgelesene Werte werden zurück gegeben
        return dataList;
    }

    /**
     * Prüfen, dass die CSV-Datei nicht leer ist
     * @param filePath (Pfad zur CSV-Datei)
     * @return --> true oder false (bei false wird das Programm beendet)
     */
    public boolean isCSVEmpty(String filePath) {
        try {
            List<String> firstLine  = Files.readAllLines(Paths.get(filePath));
            return ! (firstLine == null);
        } catch (IOException e) {
            logger.error("Fehlermeldung: "+e);
        }
        return true;
    }

    /**
     * Prüfen, ob die CSV-Datei vorhanden ist
     * @param filePath (Pfad zur CSV-Datei)
     * @return --> true oder false (bei false wird das Programm beendet)
     */
    public boolean fileExist(String filePath){
        File file = new File(filePath);
        if (!(file.exists())) {
            logger.error("Die CSV-Datei existiert nicht. Das Programm wird beendet.");
            System.exit(0); // Programm wird beendet
            return false;
        }
        return true;
    }

    /**
     * Konstruktor
     *
     * @param filePath --> Auszulesender Pfad
     */
    public Reader(String filePath){
        this.filePath =filePath;
    }

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(Reader.class.getName());

    /**
     * FilePath
     */
    private String filePath;

}


