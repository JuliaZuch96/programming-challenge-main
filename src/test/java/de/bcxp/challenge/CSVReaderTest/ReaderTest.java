import de.bcxp.challenge.helpers.Reader;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 In diesen Unit-Tests werden verschiedene Szenarien getestet, einschließlich des Lesens einer existierenden und nicht
 leeren CSV-Datei, des Lesens einer leeren CSV-Datei, des Lesens einer nicht existierenden CSV-Datei sowie der
 Überprüfung des Vorhandenseins einer CSV-Datei. Die Tests überprüfen die erwarteten Ergebnisse und stellen sicher,
 dass die Methoden der `Reader`-Klasse wie erwartet funktionieren.

 */

public class ReaderTest {

    @Test
    public void testReadCSV_FileExistsAndNotEmpty() throws IOException {
        // Vorbereitung der Testdaten
        Path tempFilePath = Files.createTempFile("test", ".csv");
        List<String> csvData = new ArrayList<>();
        csvData.add("1,2,3");
        csvData.add("4,5,6");
        Files.write(tempFilePath, csvData);

        // Erzeugung der Reader-Instanz
        Reader reader = new Reader(tempFilePath.toString());

        // Ausführung des Tests
        List<String> dataList = reader.readCSV();

        // Überprüfung der Ergebnisse
        assertNotNull(dataList);
        assertFalse(dataList.isEmpty());
        assertEquals(2, dataList.size());
        assertEquals("1,2,3", dataList.get(0));
        assertEquals("4,5,6", dataList.get(1));

        // Bereinigung der Testdaten
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    public void testReadCSV_FileDoesNotExist() {
        // Erzeugung der Reader-Instanz mit einem nicht existierenden Pfad
        Reader reader = new Reader("non_existing_file.csv");

        // Ausführung des Tests
        List<String> dataList = reader.readCSV();

        // Überprüfung der Ergebnisse
        assertNotNull(dataList);
        assertTrue(dataList.isEmpty());
    }

    @Test
    public void testReadCSV_EmptyFile() throws IOException {
        // Vorbereitung der Testdaten
        Path tempFilePath = Files.createTempFile("test", ".csv");

        // Erzeugung der Reader-Instanz
        Reader reader = new Reader(tempFilePath.toString());

        // Ausführung des Tests
        List<String> dataList = reader.readCSV();

        // Überprüfung der Ergebnisse
        assertNotNull(dataList);
        assertTrue(dataList.isEmpty());

        // Bereinigung der Testdaten
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    public void testIsCSVEmpty_EmptyFile() throws IOException {
        // Vorbereitung der Testdaten
        Path tempFilePath = Files.createTempFile("test", ".csv");

        // Erzeugung der Reader-Instanz
        Reader reader = new Reader(tempFilePath.toString());

        // Ausführung des Tests
        boolean isCSVEmpty = reader.isCSVEmpty(tempFilePath.toString());

        // Überprüfung der Ergebnisse
        assertTrue(isCSVEmpty);

        // Bereinigung der Testdaten
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    public void testFileExist_FileExists() throws IOException {
        // Vorbereitung der Testdaten
        Path tempFilePath = Files.createTempFile("test", ".csv");

        // Erzeugung der Reader-Instanz
        Reader reader = new Reader(tempFilePath.toString());

        // Ausführung des Tests
        boolean fileExists = reader.fileExist(tempFilePath.toString());

        // Überprüfung der Ergebnisse
        assertTrue(fileExists);

        // Bereinigung der Testdaten
        Files.deleteIfExists(tempFilePath);
    }

    @Test
    public void testFileExist_FileDoesNotExist() {
        // Erzeugung der Reader-Instanz mit einem nicht existierenden Pfad
        Reader reader = new Reader("non_existing_file.csv");

        // Ausführung des Tests
        boolean fileExists = reader.fileExist("non_existing_file.csv");

        // Überprüfung der Ergebnisse
        assertFalse(fileExists);
    }
}