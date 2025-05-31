import java.awt.*; // Wird aktuell nicht verwendet – könnte entfernt werden
import java.util.ArrayList; // Import für dynamische Listen
import java.util.Scanner; // Import für Benutzereingaben

// Hauptklasse zur Verwaltung von Kontakten
public class KontaktVerwaltung {

    private ArrayList<Kontakt> kontakte; // Liste aller aktiven Kontakte
    private ArrayList<Kontakt> geloeschteKontakte; // Liste der gelöschten Kontakte zur Wiederherstellung
    private Scanner scanner; // Eingabewerkzeug für die Konsole

    // Konstruktor – wird beim Erstellen eines Objekts aufgerufen
    public KontaktVerwaltung() {
        kontakte = DateiManager.laden(); // Kontakte beim Start aus Datei laden
        geloeschteKontakte = new ArrayList<>(); // Liste für gelöschte Kontakte vorbereiten
        scanner = new Scanner(System.in); // Scanner zur Eingabe initialisieren
    }

    // Hauptmethode zum Starten des Programms
    public void starten() {
        boolean running = true; // Kontrollvariable für die Schleife

        while (running) { // Solange das Programm läuft
            // Menü anzeigen
            System.out.println("\n--- Kontaktverwaltung ---");
            System.out.println("1. Kontakt hinzufügen");
            System.out.println("2. Kontakte anzeigen");
            System.out.println("3. Kontakt löschen");
            System.out.println("4. Wiederherstellen");
            System.out.println("5. Filter");
            System.out.println("6. durchschnittlichesAlter ");
            System.out.println("7. Alterskathegorien");
            System.out.println("8. Speichern und Beenden");
            System.out.print("====> Wähle eine Option: ");
            String eingabe = scanner.nextLine(); // Eingabe einlesen

            // Auswahl verarbeiten
            switch (eingabe) {
                case "1":
                    kontaktHinzufuegen(); // Kontakt hinzufügen
                    break;
                case "2":
                    kontakteAnzeigen(); // Alle Kontakte anzeigen
                    break;
                case "3":
                    kontaktLoeschen(); // Kontakt löschen
                    break;
                case "4":
                    wiederherstellen(); // Gelöschten Kontakt wiederherstellen
                    break;
                case "5":
                    filtereNachAlterInteraktiv(); // Filter nach Mindestalter
                    break;
                case "6":
                    durchschnittlichesAlterBerechnen(); // Durchschnitt berechnen
                    break;
                case "7":
                    kontakteNachAlterskategorieAnzeigen(); // Altersgruppen anzeigen
                    break;
                case "8":
                    running = false; // Beenden
                    break;
                default:
                    System.out.println("Ungültige Eingabe."); // Fehlermeldung
            }
        }

        DateiManager.speichern(kontakte); // Kontakte beim Beenden speichern
        System.out.println("Programm beendet und Kontakte wurden gespeichert.");
    }

    // Methode zum Hinzufügen eines neuen Kontakts
    private void kontaktHinzufuegen() {
        System.out.print("Name: ");
        String name = scanner.nextLine(); // Name eingeben
        System.out.print("Alter: ");
        String age = scanner.nextLine(); // Alter eingeben
        System.out.print("Beruf: ");
        String job = scanner.nextLine(); // Beruf eingeben

        kontakte.add(new Kontakt(name, age, job)); // Neuen Kontakt erstellen und zur Liste hinzufügen
        System.out.println("Kontakt hinzugefügt.");
    }

    // Methode zum Anzeigen aller Kontakte
    private void kontakteAnzeigen() {
        if (kontakte.isEmpty()) { // Wenn Liste leer
            System.out.println("Keine Kontakte vorhanden.");
        } else {
            System.out.println("Es sind " + kontakte.size() + " Gespeicherte Kontakte:");
            for (int i = 0; i < kontakte.size(); i++) {
                System.out.println((i + 1) + ". " + kontakte.get(i)); // Kontakt anzeigen
            }
        }
    }

    // Methode zum Löschen eines Kontakts
    private void kontaktLoeschen() {
        kontakteAnzeigen(); // Erst Kontakte anzeigen
        if (kontakte.isEmpty()) return; // Wenn keine, dann abbrechen

        System.out.print("Nummer des zu löschenden Kontakts: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1; // Eingabe in Index umwandeln
            if (index >= 0 && index < kontakte.size()) {
                Kontakt k = kontakte.remove(index); // Kontakt entfernen
                geloeschteKontakte.add(k); // In gelöschte Liste speichern
                System.out.println("Kontakt gelöscht.");
            } else {
                System.out.println("Ungültiger Index."); // Fehlerbehandlung
            }
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe."); // Fehler bei falscher Zahl
        }
    }

    // Methode zum Wiederherstellen gelöschter Kontakte
    private void wiederherstellen() {
        if (geloeschteKontakte.isEmpty()) {
            System.out.println("Keine Kontakte zum Wiederherstellen.");
            return;
        }

        System.out.println("Gelöschte Kontakte:");
        for (int i = 0; i < geloeschteKontakte.size(); i++) {
            System.out.println((i + 1) + ". " + geloeschteKontakte.get(i));
        }

        System.out.print("Nummer des Kontakts zum Wiederherstellen: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < geloeschteKontakte.size()) {
                Kontakt k = geloeschteKontakte.remove(index);
                kontakte.add(k);
                System.out.println("Kontakt wiederhergestellt.");
            } else {
                System.out.println("Ungültiger Index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe.");
        }
    }

    // Methode zum Filtern der Kontakte nach Mindestalter
    private void filtereNachAlterInteraktiv() {
        System.out.print("Mindestalter eingeben: ");
        int minAlter;

        try {
            minAlter = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
            return;
        }

        System.out.println("\n Kontakte mit Alter ab " + minAlter + ":");

        boolean gefunden = false;
        for (Kontakt k : kontakte) {
            try {
                if (Integer.parseInt(k.getAge()) >= minAlter) {
                    System.out.println("- " + k.getName() + " (" + k.getAge() + ") " + "(" + k.getJob() + ")");
                    gefunden = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültiges Alter bei Kontakt: " + k.getName());
            }

            if (!gefunden) {
                System.out.println("Keine passenden Kontakte gefunden.");
            }
        }
    }

        // Methode zur Berechnung des Durchschnittsalters
    private void durchschnittlichesAlterBerechnen(){
            if (kontakte.isEmpty()) {
                System.out.println("Keine Kontakte vorhanden.");
                return;
            }

            int summe = 0;

            for (Kontakt k : kontakte) {
                try {
                    summe += Integer.parseInt(k.getAge()); // Alter aufsummieren
                } catch (NumberFormatException e) {
                    System.out.println("Ungültiges Alter bei Kontakt: " + k.getName());
                }
            }

            double durchschnitt = (double) summe / kontakte.size(); // Durchschnitt berechnen
            System.out.printf("Durchschnittsalter: %.2f Jahre%n", durchschnitt);
    }

        // Methode zur Anzeige nach Altersgruppen
    public void kontakteNachAlterskategorieAnzeigen() {

            ArrayList<Kontakt> gruppe18_24 = new ArrayList<>(); // Listen für Altersgruppen vorbereiten
            ArrayList<Kontakt> gruppe25_34 = new ArrayList<>();
            ArrayList<Kontakt> gruppe35_44 = new ArrayList<>();
            ArrayList<Kontakt> gruppe45_54 = new ArrayList<>();
            ArrayList<Kontakt> gruppe55_64 = new ArrayList<>();
            ArrayList<Kontakt> gruppe65plus = new ArrayList<>();

            for (Kontakt k : kontakte) {

                 try {

                    int alter = Integer.parseInt(k.getAge());

                    if (alter >= 18 && alter <= 24) {
                        gruppe18_24.add(k);
                    } else if (alter <= 34) {
                        gruppe25_34.add(k);
                    } else if (alter <= 44) {
                        gruppe35_44.add(k);
                    } else if (alter <= 54) {
                        gruppe45_54.add(k);
                    } else if (alter <= 64) {
                        gruppe55_64.add(k);
                    } else {
                        gruppe65plus.add(k);
                    }
                 }catch (NumberFormatException e){
                    System.out.println("Ungültiges Alter bei Kontakt: " + k.getName());
                 }
            }



           System.out.println("\n--- Alterskategorien ---");
           printGruppe("18–24 Jahre", gruppe18_24);
           printGruppe("25–34 Jahre", gruppe25_34);
           printGruppe("35–44 Jahre", gruppe35_44);
           printGruppe("45–54 Jahre", gruppe45_54);
           printGruppe("55–64 Jahre", gruppe55_64);
           printGruppe("65+ Jahre", gruppe65plus);   // Gruppen ausgeben

    }

            // Hilfsmethode zur Anzeige einer Gruppe
    private void printGruppe(String titel, ArrayList< Kontakt > gruppe){
        System.out.println("\n" + titel + " (" + gruppe.size() + " Kontakte):");
        for (Kontakt k : gruppe) {
            System.out.println("- " + k.getName() + " (" + k.getAge() + ")");
        }
    }

    public ArrayList<Kontakt> getKontakte () {
        return kontakte;
    }

}
