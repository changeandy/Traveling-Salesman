import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.ArrayList;
import java.util.Comparator;

//import java.util.List;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Stadt.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Stadt extends Actor
{
    private static int anzahl = 0; // Die Anzahl aller erzeugten Städte
    private static ArrayList<Stadt> staedte = new ArrayList<Stadt>(); // Eine Liste aller Städte
    private static ArrayList<ArrayList<Verbindung>> verbindungen = new ArrayList<ArrayList<Verbindung>>(); // Eine Tabelle aller Verbindungen

    private int idNum; // die Seriennummer der Stadt

    public Stadt(World spielwelt)
    {
            spielwelt.addObject(this, Greenfoot.getRandomNumber(spielwelt.getWidth()-20)+10, 
                Greenfoot.getRandomNumber(spielwelt.getHeight()-20)+10);
            eingliedern();
    }

    public Stadt(World spielwelt, int x, int y)
    {
            spielwelt.addObject(this, x, y);
            eingliedern();
    }

    /**
     * reset - leert die statische Städteliste und die Verbindungstabelle für einen Szenario-neustart
     */
    public static void reset()
    {
        anzahl = 0;
        staedte.clear();
        verbindungen.clear();
    }

    /**
     * eingliedern - Sobald die Stadt in die Welt gesetzt wird, muss sie eingegliedert werden, d.h. sie 
     *    * bekommt ihre ID
     *    * sie wird mit allen anderen Städten verbunden
     *    * die Verbindungen werden in die Verbindungstabelle eingetragen.
     */
    private void eingliedern()
    {
        idNum = anzahl;
        anzahl++;
        staedte.add(this);
        verbindungen.add(new ArrayList<Verbindung>());
        for (int i = 0; i < idNum; i++) // fügt eine Verbindung zu allen vorherigen Städten hinzu
        {
            verbindungen.get(idNum).add(new Verbindung(staedte.get(i), this));// fügt die Verbindung in die Verbindungstabelle ein
        }
    }

    /**
     * getAnschluesse - Gibt eine Liste mit Verbindungen, die von der Stadt ausgehen 
     */
    public ArrayList<Verbindung> getAnschluesse()
    {
        ArrayList<Verbindung> anschluesse = new ArrayList<Verbindung>(verbindungen.get(idNum)); // Verb. zu älteren Städten
        for (int i = idNum+1; i < anzahl; i++) // fügt die Verbindungen zu jüngeren Städten hinzu
        {
            anschluesse.add(verbindungen.get(i).get(idNum));
        }
        return anschluesse;
    }

    /**
     * updateAnschluesse - Nachdem eine Stadt verschoben wurde, sollten die Anschlüsse aktualisiert werden
     */
    public void updateAnschluesse()
    {
        double entfNeu;
        // arbeite die Verbindungen zu Städten mit niedrigerer ID durch
        for (int i = 0; i < idNum; i++)
        {
            verbindungen.get(idNum).get(i).zeichne();
        }
        // arbeite die Verbindungen zu Städten mit höherer ID durch
        for (int i = idNum+1; i < anzahl; i++)
        {
            verbindungen.get(i).get(idNum).zeichne();
        }
    }

    public Stadt getZiel(Verbindung verbindung)
    {
        Stadt a = verbindung.getA();
        if (a != this) 
        {
            return a;
        }
        return verbindung.getB();
    }

    /**
     * Verbindung - gibt das Verbindungsobjekt zum Ziel aus
     */
    public Verbindung getVerbindung(int ziel)
    {
        if (this.idNum > ziel)
        {
            return verbindungen.get(this.idNum).get(ziel);
        }
        return verbindungen.get(ziel).get(this.idNum);
    }
    
    public Verbindung getVerbindung(Stadt ziel)
    {
        if (this.idNum > ziel.idNum)
        {
            return verbindungen.get(this.idNum).get(ziel.idNum);
        }
        return verbindungen.get(ziel.idNum).get(this.idNum);
    }
    
    public int getIdNum()
    {
        return idNum;
    }
    
    /**
     * updateAlleVerbindungen - aktualisiert alle Verbindungen in der Verbindungstabelle
     */
    public static void updateAlleVerbindungen()
    {
        for (int i = 1; i < anzahl; i++)
        {
            for (int j = 0; j < i; j++)
            {
                verbindungen.get(i).get(j).zeichne();
            }
        }
    }

    /**
     * getStadt - Gibt die Stadtinstanz mit der entsprechenden Identifikationsnummer aus.
     * 
     * @param idNum die laufende Nummer der Stadt
     */
    public static Stadt getStadt(int idNum)
    {
        return staedte.get(idNum);
    }

    public double calcEntf(Stadt b)
    {
        int dx = this.getX()-b.getX();
        int dy = this.getY()-b.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double getEntf(Stadt b)
    {
        if( this == b ) { return 0.0; }
        return this.getVerbindung(b).getEntfernung();
    }

    public static ArrayList<Stadt> getAlleStaedte() 
    {
        return new ArrayList<Stadt>(staedte);    
    }

    /**
     * Act - tut, was auch immer Stadt tun will. Diese Methode wird aufgerufen, 
     * sobald der 'Act' oder 'Run' Button in der Umgebung angeklickt werden. 
     */
    public void act() 
    {
        // Ergänzen Sie Ihren Quelltext hier...
    }

    public static int getAnzahl()
    {
        return anzahl;
    }
}