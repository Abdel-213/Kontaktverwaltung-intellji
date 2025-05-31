import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class KontaktVerwaltungGUI extends JFrame {

    private KontaktVerwaltung verwaltung;
    private JTextField minAlterField;
    private JTextArea ausgabeArea;

    public KontaktVerwaltungGUI(KontaktVerwaltung verwaltung) {
        this.verwaltung = verwaltung;

        setTitle("Kontaktverwaltung - Filter");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Eingabefeld + Button oben
        JPanel obenPanel = new JPanel();
        obenPanel.add(new JLabel("Mindestalter:"));
        minAlterField = new JTextField(5);
        obenPanel.add(minAlterField);
        JButton filterButton = new JButton("Filtern");
        filterButton.setBackground(Color.GREEN);
        filterButton.setForeground(Color.WHITE);

        obenPanel.add(filterButton);

        add(obenPanel, BorderLayout.NORTH);

        // Textfeld in der Mitte
        ausgabeArea = new JTextArea();
        ausgabeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(ausgabeArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button-Action
        filterButton.addActionListener(e -> filtereKontakte());

        setVisible(true);
    }

    private void filtereKontakte() {
        try {
            int minAlter = Integer.parseInt(minAlterField.getText());
            ArrayList<Kontakt> kontakte = verwaltung.getKontakte();

            StringBuilder builder = new StringBuilder();
            for (Kontakt k : kontakte) {
                int alter = Integer.parseInt(k.getAge());
                if (alter >= minAlter) {
                    builder.append(k.getName()).append(" (").append(alter).append(")\n");
                }
            }

            if (builder.length() == 0) {
                builder.append("Keine passenden Kontakte gefunden.");
            }

            ausgabeArea.setText(builder.toString());

        } catch (NumberFormatException ex){
            ausgabeArea.setText("Bitte eine gültige Zahl eingeben.");
        }
    }

    // Zum Starten
    public static void main(String[] args) {
        KontaktVerwaltung verwaltung = new KontaktVerwaltung();  // lädt die Kontakte
        new KontaktVerwaltungGUI(verwaltung);                    // öffnet die GUI
    }

}

