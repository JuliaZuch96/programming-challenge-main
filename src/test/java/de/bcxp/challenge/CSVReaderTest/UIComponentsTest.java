package de.bcxp.challenge.CSVReaderTest;

import de.bcxp.challenge.helpers.UIComponents;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *  Abrufs des Dateipfads, der Auswahl von Werten und Operationen sowie der Benutzerentscheidung. Die Tests simulieren
 *  die Benutzeroberfläche und überprüfen die erwarteten Ergebnisse.
 */
public class UIComponentsTest {

    @Test
    public void testCreateUIComponents() {
        // Erzeugung der UIComponents-Instanz
        UIComponents uiComponents = new UIComponents();

        // Ausführung des Tests
        String filePath = uiComponents.createUIComponents();

        // Überprüfung der Ergebnisse
        assertNotNull(filePath);
        assertFalse(filePath.isEmpty());
    }

    @Test
    public void testChooseValuesAndOperation_EmptyDataList() {
        // Erzeugung der UIComponents-Instanz
        UIComponents uiComponents = new UIComponents();

        // Erzeugung einer leeren Datenliste
        List<String> dataList = new ArrayList<>();

        // Ausführung des Tests
        Object[] result = uiComponents.chooseValuesAndOperation(dataList);

        // Überprüfung der Ergebnisse
        assertNull(result);
    }

    @Test
    public void testChooseValuesAndOperation_ValidDataList() {
        // Erzeugung der UIComponents-Instanz
        UIComponents uiComponents = new UIComponents();

        // Erzeugung einer Datenliste mit Werten
        List<String> dataList = new ArrayList<>();
        dataList.add("1");
        dataList.add("2");
        dataList.add("3");
        dataList.add("4");

        // Erzeugung eines grafischen Benutzerdialogs, um den Test zu simulieren
        GraphicsEnvironment.getLocalGraphicsEnvironment();
        EventQueue.invokeLater(() -> {
            Object[] result = uiComponents.chooseValuesAndOperation(dataList);

            // Überprüfung der Ergebnisse
            assertNotNull(result);
            assertEquals(3, result.length);
            assertEquals(0, (int) result[0]);
            assertEquals(0, (int) result[1]);
            assertEquals("-", result[2]);
        });
    }

    @Test
    public void testUserChoice_ContinButtonClicked() {
        // Erzeugung der UIComponents-Instanz
        UIComponents uiComponents = new UIComponents();

        // Erzeugung eines grafischen Benutzerdialogs, um den Test zu simulieren
        GraphicsEnvironment.getLocalGraphicsEnvironment();
        EventQueue.invokeLater(() -> {
            boolean result = uiComponents.userChoice("Ergebnis", "Möchten Sie fortfahren?");

            // Überprüfung der Ergebnisse
            assertTrue(result);
        });
    }

    @Test
    public void testUserChoice_ExitButtonClicked() {
        // Erzeugung der UIComponents-Instanz
        UIComponents uiComponents = new UIComponents();

        // Erzeugung eines grafischen Benutzerdialogs, um den Test zu simulieren
        GraphicsEnvironment.getLocalGraphicsEnvironment();
        EventQueue.invokeLater(() -> {
            boolean result = uiComponents.userChoice("Ergebnis", "Möchten Sie fortfahren?");

            // Überprüfung der Ergebnisse
            assertFalse(result);
        });
    }
}
