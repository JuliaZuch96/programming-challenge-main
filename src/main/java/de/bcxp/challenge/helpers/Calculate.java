package de.bcxp.challenge.helpers;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Klasse zur Berechnung des Maximums
 */
public class Calculate {

    /**
     * verarbeitet eine Liste von Zeilen (Strings), teilt jede Zeile in durch Semikolon oder Komma getrennte Werte auf,
     * führt eine spezifizierte mathematische Operation an den Werten an zwei gegebenen Positionen durch und
     * gibt die Zeile mit der größten resultierenden Differenz zurück, wobei sie Fehler und ungültige Daten abfängt.
     *
     * @param dataList --> Liste mit den übergebenen Daten
     * @param firstPosition --> Position des ersten Werts in der Zeile der Liste
     * @param secondPosition --> Position des zweiten Werts in der Zeile der Liste
     * @param operation --> Operation die durchgeführt werden soll
     * @return --> Zeile die den max Wert beinhaltet
     */
    public String max(List<String> dataList, int firstPosition, int secondPosition, String operation) {

        if (firstPosition==secondPosition){ //Prüfen ob die Positionen unterschiedlich sind
            logger.info("Erste und zweite Position dürfen nicht identisch sein.");
            return null;
        }

        String resultRow = null; // Reihe in der dataList des Maximums
        double maxDifference = 0; // Differenz des Max und Min Werts

        for (int i = 1; i < dataList.size(); i++) {  // Überschrift der Datenliste überspringen
            try {
                String[] row = dataList.get(i).split("[;,]" );  // Zeile in Werte aufteilen
                double value1 = Double.parseDouble(row[firstPosition]);
                double value2 = Double.parseDouble(row[secondPosition]);

                // Auswahl der übergebenen Operation (auch + und * möglich, aber bei der Eingabe des Benutzers aktuell auskommentiert)
                double difference;
                switch (operation) {
                    case "+":
                        difference = Math.abs(value1 + value2);
                        break;
                    case "-":
                        difference = Math.abs(value1 - value2);
                        break;
                    case "*":
                        difference = Math.abs(value1 * value2);
                        break;
                    case "/":
                        if (value2 != 0) {
                            difference = Math.abs(value1 / value2);
                        } else {
                            logger.info("Durch 0 teilen ist nicht erlaubt");
                            continue;
                        }
                        break;
                    default:
                        logger.info("Unerlaubte Operation: " + operation);
                        continue;
                }

                // Vergleich mit dem aktuell größten Wert und abspeichern des größten Werts
                if (difference > maxDifference) {
                    maxDifference = difference;
                    String[] resultRowList = dataList.get(i).split("[;,]" );
                    // Speichern des ersten Werts des Maxmimums in der dataList und die maximale Differenz für die Ausgabe
                    resultRow = "Maximum:"+ resultRowList[0]+ " Maximale Differenz: "+ Math.round(maxDifference) ;
                }

            } catch (NumberFormatException e) {
                logger.info("Unerlaubtes Zahlenformat.");
            }
        }

        // Wenn die Datei leer ist
        if (resultRow == null) {
            logger.info("Es wurden keine gültigen Zeilen in den Daten gefunden.");
        }

        // Datensatz mit dem max Wert wird zurück gegeben
        return resultRow;
    }

    /**
     * verarbeitet eine Liste von Zeilen (Strings), teilt jede Zeile in durch Semikolon oder Komma getrennte Werte auf,
     * führt eine spezifizierte mathematische Operation an den Werten an zwei gegebenen Positionen durch und
     * gibt die Zeile mit der kleinsten resultierenden Differenz zurück, wobei sie Fehler und ungültige Daten abfängt.
     *
     * @param dataList --> Liste mit den übergebenen Daten
     * @param firstPosition --> Position des ersten Werts in der Zeile der Liste
     * @param secondPosition --> Position des zweiten Werts in der Zeile der Liste
     * @param operation --> Operation die durchgeführt werden soll
     * @return --> Zeile die den min Wert beinhaltet
     */
    public String min(List<String> dataList, int firstPosition, int secondPosition, String operation) {

        if (firstPosition==secondPosition){
            logger.info("Erste und zweite Position dürfen nicht identisch sein.");
            return null;
        }

        String resultRow = null;
        double minDifference = Double.MAX_VALUE;  // Initialisierung mit hohem Wert

        for (int i = 1; i < dataList.size(); i++) {
            try {
                String[] row = dataList.get(i).split("[;,]" );
                double value1 = Double.parseDouble(row[firstPosition]);
                double value2 = Double.parseDouble(row[secondPosition]);

                double difference;
                switch (operation) {
                    case "+":
                        difference = Math.abs(value1 + value2);
                        break;
                    case "-":
                        difference = Math.abs(value1 - value2);
                        break;
                    case "*":
                        difference = Math.abs(value1 * value2);
                        break;
                    case "/":
                        if (value2 != 0) {
                            difference = Math.abs(value1 / value2);
                        } else {
                            logger.info("Durch 0 teilen ist nicht erlaubt");
                            continue;
                        }
                        break;
                    default:
                        logger.info("Unerlaubte Operation: " + operation);
                        continue;
                }

                // Vergleich mit dem aktuell kleinsten Wert und abspeichern des kleinsten Werts
                if (difference < minDifference) {
                    minDifference = difference;
                    String[] resultRowList = dataList.get(i).split("[;,]" );
                    resultRow = "Minimum: "+ resultRowList[0]+ " Minimale Differenz: "+ Math.round(minDifference);
                }

            } catch (NumberFormatException e) {
                logger.info("Unerlaubtes Zahlenformat.");
            }
        }

        if (resultRow == null) {
            logger.info("Es wurden keine gültigen Zeilen in den Daten gefunden.");
        }

        return resultRow;
    }


    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(Calculate.class.getName());
}
