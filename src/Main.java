import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Kontakt> kontakte = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Kontaktverwaltung ---");
            System.out.println("1. Kontakt hinzufügen");
            System.out.println("2. Kontakte anzeigen");
            System.out.println("3. Kontakt löschen");
            System.out.println("4. Beenden");
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
        System.out.print("Telefonnummer: ");
        String phone = scanner.nextLine();
        System.out.println("beruf: ");
        String job = scanner.nextLine();

        kontakte.add(new Kontakt(name, phone, job));
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
                kontakte.remove(index);
                System.out.println("Kontakt gelöscht.");
            } else {
                System.out.println("Ungültiger Index.");
            }
        }
    }
}
