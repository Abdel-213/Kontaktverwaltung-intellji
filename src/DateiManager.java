import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DateiManager {
    private static final String DATEINAME = "kontakte.json";
    private static final Gson gson = new Gson();

    public static void speichern(ArrayList<Kontakt> kontakte) {
        try (FileWriter writer = new FileWriter(DATEINAME)) {
            gson.toJson(kontakte, writer);
            System.out.println("Kontakte wurden gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public static ArrayList<Kontakt> laden() {
        try (FileReader reader = new FileReader(DATEINAME)) {
            Type kontaktListeTyp = new TypeToken<ArrayList<Kontakt>>() {}.getType();
            return gson.fromJson(reader, kontaktListeTyp);
        } catch (IOException e) {
            System.out.println("Keine gespeicherten Kontakte gefunden.");
            return new ArrayList<>();
        }
    }
}
