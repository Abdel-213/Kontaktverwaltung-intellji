import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Kontakt> kontakte = new ArrayList<>();
    private static ArrayList<Kontakt> safeContacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {


            System.out.println("\n--- Kontaktverwaltung ---");
            System.out.println("1. Kontakt hinzufügen");
            System.out.println("2. Kontakte anzeigen");
            System.out.println("3. Kontakt löschen");
            System.out.println("4. Beenden");
            System.out.println("5.wiederherstellen");
            System.out.print("Wähle eine Option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    addContact();
                    break;
                case "2":
                    showContacts();
                    break;
                case "3":
                    deleteContact();
                    break;
                case "5":
                    restoration();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Eingabe. Bitte erneut versuchen.");
            }
        }

        System.out.println("Programm beendet.");
    }

    private static void addContact() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("alter: ");
        String age = scanner.nextLine();
        System.out.println("beruf: ");
        String job = scanner.nextLine();
        kontakte.add(new Kontakt(name, age, job));
        System.out.println("Kontakt hinzugefügt.");
    }

    private static void showContacts() {
        if (kontakte.isEmpty()) {
            System.out.println("Keine Kontakte vorhanden.");
        } else {
            System.out.println("Gespeicherte Kontakte:");
            for (int i = 0; i < kontakte.size(); i++) {
                System.out.println((i + 1) + ". " + kontakte.get(i));
            }
        }
    }

    private static void deleteContact() {
        showContacts();

        if (!kontakte.isEmpty()) {
            System.out.print("Nummer des zu löschenden Kontakts: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index >= 0 && index < kontakte.size()) {
                Kontakt geloeschterKontakt = kontakte.remove(index);
                safeContacts.add(geloeschterKontakt);

                System.out.println("Kontakt gelöscht.");
            } else {
                System.out.println("Ungültiger Index.");
            }
        }
    }


    private static void restoration() {
        if (safeContacts.isEmpty()) {
            System.out.println("Keine gelöschten Kontakte zum Wiederherstellen.");
            return;
        }

        System.out.println("Gelöschte Kontakte:");
        for (int i = 0; i < safeContacts.size(); i++) {
            System.out.println((i + 1) + ". " + safeContacts.get(i));
        }

        System.out.print("Nummer des Kontakts, den du wiederherstellen möchtest: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index >= 0 && index < safeContacts.size()) {
                Kontakt wiederhergestellt = safeContacts.remove(index);
                kontakte.add(wiederhergestellt);
                System.out.println("Kontakt wurde wiederhergestellt.");
            } else {
                System.out.println("Ungültiger Index.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Bitte gib eine gültige Zahl ein.");
        }
    }

}

