import com.google.gson.Gson;  // GSON-Bibliothek zum Umwandeln von Java-Objekten in JSON und umgekert
import com.google.gson.reflect.TypeToken; // // Wird verwendet, um komplexe Typinformationen für GSON bereitzustellen


import java.io.FileReader; // Zum Lesen von Dateien
import java.io.FileWriter; // Zum Schreiben in Dateien
import java.io.IOException; // Behandelt Fehler bei Dateioperationen
import java.lang.reflect.Type; // Wird für die Definition von generischen Typen genutzt (z. B. ArrayList<Kontakt>)
import java.util.ArrayList; // Für die Liste der Kontakte

// Diese Klasse übernimmt das Laden und Speichern der Kontakte in einer JSON-Datei.
public class DateiManager {
    private static final String DATEINAME = "kontakte.json";  //der Wert kann nicht mehr geändert werden.
    private static final Gson gson = new Gson();  // Objekte in JSON verwandeln oder umgekehrt

    // Öffentliche Methode, um eine Liste von Kontakten in eine Datei zu speichern.
    public static void speichern(ArrayList<Kontakt> kontakte) {
        try (FileWriter writer = new FileWriter(DATEINAME)) {
            gson.toJson(kontakte, writer);    // Die Liste kontakte wird in JSON umgewandelt und in die Datei geschrieben.
            System.out.println("Kontakte wurden gespeichert.");
        } catch (IOException e) {   // Falls beim Speichern ein Fehler passiert, wird eine Fehlermeldung ausgegeben
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }
// Öffentliche Methode, um gespeicherte Kontakte aus der Datei zu laden.
    public static ArrayList<Kontakt> laden() {
        try (FileReader reader = new FileReader(DATEINAME)) {
            Type kontaktListeTyp = new TypeToken<ArrayList<Kontakt>>() {}.getType(); //  um die JSON korrekt zu interpretieren.
            return gson.fromJson(reader, kontaktListeTyp); // Die Datei wird gelesen und in eine ArrayList<Kontakt> umgewandelt.
        } catch (IOException e) {
            System.out.println("Keine gespeicherten Kontakte gefunden.");
            return new ArrayList<>(); // Wenn die Datei nicht gefunden wird (z. B. beim ersten Start), wird eine leere Liste zurückgegeben.
        }
    }
}
