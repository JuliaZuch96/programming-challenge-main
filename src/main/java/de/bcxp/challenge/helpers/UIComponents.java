package de.bcxp.challenge.helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Klasse für Benutzeroberfläche, die es dem Benutzer ermöglicht, einen Dateipfad einzugeben, Datenwerte und eine
 * mathematische Operation auszuwählen, und dann eine Ergebnisausgabe anzeigt, auf Basis derer der Benutzer
 * entscheiden kann, ob er fortfahren oder das Programm beenden möchte.
 */
public class UIComponents extends JDialog {


    /**
     * Benutzeroberfläche für das einlesen des Dateipfad zur die auszulesende Datei
     *
     * @return filePathTextField.getText() --> Dateipfad als String
     */
    public String createUIComponents() {
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel instructionLabel = new JLabel("Pfad zur CSV-Datei eingeben:");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        filePathTextField = new JTextField();
        filePathTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE, filePathTextField.getPreferredSize().height));

        JButton submitButton = new JButton("OK");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        mainPanel.add(instructionLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(filePathTextField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(submitButton);

        add(mainPanel);

        setVisible(true);

        return filePathTextField.getText();
    }

    /**
     * Benutzeroberfläche zur Auswahl von zwei Datenwerte und einer
     * mathematischen Operation
     *
     * @param dataList --> Liste mit den auszuwählenden Datenwerte
     * @return Object[] mit dem Index der beiden auszuwählenden Datenwerte und der ausgewählten mathematischen Operation
     */
    public Object[] chooseValuesAndOperation(List<String> dataList) {

        // Prüfen, ob die übergebene Datenliste leer ist. Wenn ja, loggt eine Warnmeldung und gibt null zurück
        if (dataList.isEmpty()) {
            logger.warn("Die Datenliste ist leer.");
            return null;
        }

        // Teilen des ersten String in der Liste anhand der Trennzeichen ";" oder "," auf. Jeder getrennte Wert wird als separater String in einem Array gespeichert
        String[] values = dataList.get(0).split("[;,]");

        // Erzeugung einer Dropdown-Liste zur Auswahl des ersten und des zweiten Wertes. Der erste Wert in der Liste wird als Standardauswahl verwendet
        String firstChoice = (String) JOptionPane.showInputDialog(null, "Wähle den ersten Wert:",
                "Erste Auswahl", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);

        String secondChoice = (String) JOptionPane.showInputDialog(null, "Wähle den zweiten Wert:",
                "Zweite Auswahl", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);

        // Dropdown-Liste zur Auswahl der Rechenoperation. "-" und "/"
        String[] operations = {"-", "/"}; // Erweiterung durch "*" und "+" möglich

        String operation = (String) JOptionPane.showInputDialog(null, "Wähle die Rechenoperation:",
                "Operation Auswahl", JOptionPane.QUESTION_MESSAGE, null, operations, operations[0]);

        // Dropdown-Liste zur Auswahl der Rechenoperation. "-" und "/"
        String[] maxOrmins = {"Maximum", "Minimum"}; // Erweiterung durch "*" und "+" möglich

        // Dropdown-Liste zur Auswahl der max oder min Differenzberechnung
        String maxOrmin = (String) JOptionPane.showInputDialog(null, "Wähle die Differenzberechnung:",
                "Operation Auswahl", JOptionPane.QUESTION_MESSAGE, null, maxOrmins, maxOrmins[0]);


        // Suchen des Indizes der ausgewählten Werte in der Werte-Array
        int firstIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(firstChoice)) {
                firstIndex = i;
            }
            if (values[i].equals(secondChoice)) {
                secondIndex = i;
            }
        }

        // Gib ein Array zurück, das die Indizes der ausgewählten Werte und die ausgewählte Operation enthält
        return new Object[]{firstIndex, secondIndex, operation, maxOrmin};
    }



    /**
     * Ausgabefenster des Ergebnisses und auswahl, ob weitere Dateien eingelesen werden sollen
     *
     * @param result --> Ergebnis als String
     * @param furtherCalculations --> Frage, ob weitere Daten eingelesen werden sollen
     * @return true oder false --> ture weitere Dateien einlesen, false Programm beenden
     */
    public static boolean userChoice(String result, String furtherCalculations) {

        // erzeugen der grafischen Benutzeroberfläche
        AtomicBoolean userChoice = new AtomicBoolean(false);

        JFrame frame = new JFrame("Ergebnis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel label1 = new JLabel(result);
        JLabel label2 = new JLabel(furtherCalculations);
        panel.add(label1);
        panel.add(label2);

        // Contin-Button
        JButton contin = new JButton("Ja");
        contin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userChoice.set(true);
                frame.dispose();
            }
        });
        panel.add(contin);

        // Exit-Button
        JButton exit = new JButton("Nein-Programm beenden");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userChoice.set(false);
                frame.dispose();
            }
        });
        panel.add(exit);

        // Frame in der Mitte des anzeigen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Warten, bis die Benutzereingabe fertig ist
        while (frame.isShowing()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        return userChoice.get();
    }

    /**
     * Textfeld
     */
    private JTextField filePathTextField;

    /**
     * FilePath
     */
    private String selectedFilePath;

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(UIComponents.class.getName());
}
