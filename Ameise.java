import greenfoot.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Ameise.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Ameise  
{
    // Attribute -  ersetzen Sie das Beispiel hier mit ihren eigenen Attributen
    private Stadt standort;
    private int startID;
    private ArrayList<Integer> zuBesuchen = new ArrayList<Integer>(); 

    /**
     * Konstruktor für Objekte der Klasse Ameise
     */
    public Ameise()
    {
        standort = Stadt.getStadt(0);
        int anzahl = Stadt.getAnzahl();
        for (int s = 0; s < anzahl; s++)
        {
            zuBesuchen.add((Integer)s);    
        }
        zuBesuchen.remove((Integer)startID);
    }

    /**
     * Markiert einen möglichen Weg in rot. Versucht immer die Nächste offene Verbindung zu nehmen.
     */
    public ArrayList<Stadt> einWeg()
    {
        int anzahl = Stadt.getAnzahl();
        ArrayList<Stadt> weg = new ArrayList<Stadt>();
        weg.add(standort);
        for (int i = 0; i < anzahl -1; i++ )
        {
            ArrayList<Verbindung> anschluesse = standort.getAnschluesse();
            Collections.sort(anschluesse); // sortiert die Anschlüsse nach Entfernung
            int z = -1; // Zähler
            Stadt ziel;
            do { // prüft die Verbindungen nach der nächsten Stadt, die noch nicht besucht wurde
                z++;
                ziel = standort.getZiel(anschluesse.get(z));
            } while (zuBesuchen.remove((Integer) ziel.getIdNum()) == false); 
            anschluesse.get(z).setFarbe(Color.RED);
            standort = ziel;
            weg.add(standort);
        }
        return weg;
    }
}