import java.util.ArrayList;
import java.util.Scanner;

public class KontaktVerwaltung {

    private ArrayList<Kontakt> kontakte;
    private ArrayList<Kontakt> geloeschteKontakte;
    private Scanner scanner;

    public KontaktVerwaltung() {
        kontakte = DateiManager.laden(); // Kontakte beim Start laden
        geloeschteKontakte = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void starten() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Kontaktverwaltung ---");
            System.out.println("1. Kontakt hinzufügen");
            System.out.println("2. Kontakte anzeigen");
            System.out.println("3. Kontakt löschen");
            System.out.println("4. Wiederherstellen");
            System.out.println("5. Beenden");
            System.out.print("Wähle eine Option: ");

            String eingabe = scanner.nextLine();

            switch (eingabe) {
                case "1":
                    kontaktHinzufuegen();
                    break;
                case "2":
                    kontakteAnzeigen();
                    break;
                case "3":
                    kontaktLoeschen();
                    break;
                case "4":
                    wiederherstellen();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe.");
            }
        }

        DateiManager.speichern(kontakte); // Kontakte beim Beenden speichern
        System.out.println("Programm beendet.");
    }

    private void kontaktHinzufuegen() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Alter: ");
        String age = scanner.nextLine();
        System.out.print("Beruf: ");
        String job = scanner.nextLine();

        kontakte.add(new Kontakt(name, age, job));
        System.out.println("Kontakt hinzugefügt.");
    }

    private void kontakteAnzeigen() {
        if (kontakte.isEmpty()) {
            System.out.println("Keine Kontakte vorhanden.");
        } else {
            System.out.println("Gespeicherte Kontakte:");
            for (int i = 0; i < kontakte.size(); i++) {
                System.out.println((i + 1) + ". " + kontakte.get(i));
            }
        }
    }

    private void kontaktLoeschen() {
        kontakteAnzeigen();
        if (kontakte.isEmpty()) return;

        System.out.print("Nummer des zu löschenden Kontakts: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < kontakte.size()) {
                Kontakt k = kontakte.remove(index);
                geloeschteKontakte.add(k);
                System.out.println("Kontakt gelöscht.");
            } else {
                System.out.println("Ungültiger Index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe.");
        }
    }

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
}
