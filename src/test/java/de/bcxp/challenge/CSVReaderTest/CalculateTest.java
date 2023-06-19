package de.bcxp.challenge.CSVReaderTest;

import de.bcxp.challenge.helpers.Calculate;
import de.bcxp.challenge.helpers.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class CalculateTest {

    Calculate calculate;

    @BeforeEach // bevor jeder Test ausgeführt wird die Klasse gesetzt
    void setUp() {
        calculate = new Calculate();
    }

    @Test //countries Aufgabe
    void test_countriesAufgabe(){
        String filePath = "src/main/resources/de/bcxp/challenge/countries.csv";
        int columnNumber1 = 3;
        int columnNumber2 = 4;
        String operator = "/";
        Reader reader = new Reader(filePath);
        List<String> dataList = reader.readCSV();

        String maxString = calculate.max(dataList, columnNumber1, columnNumber2, operator);

        assertEquals("Maximum:Malta Maximale Differenz: 1633", maxString);

    }

    @Test //countries Aufgabe
    void test_weatherAufgabe(){
        String filePath = "src/main/resources/de/bcxp/challenge/weather.csv";
        int columnNumber1 = 1;
        int columnNumber2 = 2;
        String operator = "-";
        Reader reader = new Reader(filePath);
        List<String> dataList = reader.readCSV();

        String maxString = calculate.min(dataList, columnNumber1, columnNumber2, operator);

        assertEquals("Minimum: 14 Minimale Differenz: 2", maxString);

    }

    @Test // Operation subtraction
    void testMax_Subtraction() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;2;3", "4;6;7", "8;11;12");
        assertEquals("Maximum:8 Maximale Differenz: 3", calculate.max(testData, 1, 0, "-"));
    }

    @Test // Operation division
    void testMax_Division() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;2;3", "4;5;6", "7;8;9");
        assertEquals("Maximum:7 Maximale Differenz: 1", calculate.max(testData, 0, 1, "/"));
    }

    @Test // Liste ist leer
    void testMax_EmptyList() {
        List<String> testData = Arrays.asList();
        assertNull(calculate.max(testData, 0, 1, "/"));
    }

    @Test // unerlaubte Operation
    void testMax_InvalidOperation() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;2;3", "4;5;6", "7;8;9");
        assertNull(calculate.max(testData, 0, 1, "%"));
    }

    @Test // Unerlaubter Wert (keine Zahl)
    void testMax_InvalidNumberFormat() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;b;3", "4;5;6", "7;8;9");
        assertEquals("Maximum:7 Maximale Differenz: 1", calculate.max(testData, 0, 1, "/"));
        // Und Log-Information: Unerlaubtes Zahlenformat
    }

    @Test // Operation subtraction
    void testMin_Subtraction() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;2;3", "4;6;7", "8;11;12");
        assertEquals("Minimum: 1 Minimale Differenz: 1", calculate.min(testData, 1, 0, "-"));
    }

    @Test // Operation division
    void testMin_Division() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;2;3", "4;5;6", "7;8;9");
        assertEquals("Minimum: 1 Minimale Differenz: 1", calculate.min(testData, 0, 1, "/"));
    }

    @Test // Liste ist leer
    void testMin_EmptyList() {
        List<String> testData = Arrays.asList();
        assertNull(calculate.min(testData, 0, 1, "/"));
    }

    @Test // unerlaubte Operation
    void testMin_InvalidOperation() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;2;3", "4;5;6", "7;8;9");
        assertNull(calculate.min(testData, 0, 1, "%"));
    }

    @Test // Unerlaubter Wert (keine Zahl)
    void testMin_InvalidNumberFormat() {
        List<String> testData = Arrays.asList("header1;header2;header3", "1;b;3", "4;5;6", "7;8;9");
        assertEquals("Minimum: 4 Minimale Differenz: 1", calculate.min(testData, 0, 1, "/"));
        // Und Log-Information: Unerlaubtes Zahlenformat
    }
}
