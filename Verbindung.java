import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.Comparator;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Verbindung.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public class Verbindung extends Actor implements Comparable<Verbindung>
{
    private Stadt a,b;
    private double entfernung;
    private double pheromonDichte = 0;
    public static Color urFarbe = new Color(0,0,255,50);

    /**
     * Konstruktor. Eine Verbindung fügt sich immer in die Welt ihrer Städte ein 
     */
    public Verbindung(Stadt a, Stadt b)
    {
        a.getWorld().addObject(this, 0, 0);
        verbinde(a, b);
        zeichne();
    }
    
    /**
     * getEntfernung - gibt Distanz, die von der Verbindung abgedeckt wird
     */
    public double getEntfernung()
    {
        return entfernung;
    }

    public Stadt getA()
    {
        return a;
    }

    public Stadt getB()
    {
        return b;
    }
    
    /**
     * getPheromonDichte - gibt die Pheromondichte aus, die auf dieser Verbindung besteht
     */
    public double getPheromonDichte()
    {
        return pheromonDichte;
    }

    /**
     * verbinde - Setzt die Endpunkte der Verbindung, berechnet die Entfernung,
     * und generiert eine Linie, die die beiden Punkte verbindet
     */
    public void verbinde(Stadt a, Stadt b)
    {
        // Sorgt dafür, dass die ältere Stadt immer an erster Stelle steht
        if (a.getIdNum() < b.getIdNum())
        {
            this.a = a;
            this.b = b;
        }
        else 
        {
            this.a = b;
            this.b = a;
        }
    }
    
    /**
     * zeichne - berechnet die Entfernung und generiert eine Linie, die die beiden Punkte zeichnet
     */
    public void zeichne()
    {
        int xa = a.getX();
        int ya = a.getY();
        int xb = b.getX();
        int yb = b.getY();

        entfernung = Math.sqrt((xa-xb)*(xa-xb) + (ya-yb)*(ya-yb));
        GreenfootImage linie = new GreenfootImage(2,(int)entfernung);
        linie.setColor(urFarbe);
        linie.fill();
        setLocation((xa+xb)/2, (ya+yb)/2);
        setRotation((int)Math.round((Math.atan(((double)(xa-xb))/(yb-ya)) * 180 / Math.PI)));
        setImage(linie);
    }

    /**
     * setFarbe - Gibt der Linie eine neue Farbe
     */
    public void setFarbe(Color farbe)
    {
        GreenfootImage linie = getImage();
        linie.clear();
        linie.setColor(farbe);
        linie.fill();
        setImage(linie);
    }
    
    /**
     * Act - tut, was auch immer Verbindung tun will. Diese Methode wird aufgerufen, 
     * sobald der 'Act' oder 'Run' Button in der Umgebung angeklickt werden. 
     */
    public void act() 
    {
        // Ergänzen Sie Ihren Quelltext hier...
    }    

    /**
     * compareTo - Diese Methode dient dem Sortieren von Verbindungen
     */
    public int compareTo(Verbindung verbindung2) 
    {
        double vergleicheMit = verbindung2.getEntfernung(); 
        if (this.entfernung - vergleicheMit < 0) {
            return -1;
        } 
        if (this.entfernung - vergleicheMit == 0) {
            return 0;
        }
        return 1;
    }
}
