import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.Stack;
import java.util.ArrayList;

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse MyWorld.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MyWorld extends World
{
    private static final int BREITE = 600;
    private static final int HOEHE = 400;
    private final int STAEDTE = 12;
    private final int Ameisen = 20;
    RundReise besterWeg = new RundReise();

    double bestDist;
    /**
     * Konstruktor für Objekte der Klasse MyWorld
     * 
     */
    public MyWorld()
    {    
        // Erstellt eine neue Welt mit 600x400 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(BREITE, HOEHE, 1); 
        setPaintOrder(Stadt.class, Verbindung.class);

        // Stadtnetzwerk bauen
        Stadt.reset();  // löscht mögliche Städtde von vor dem RESET
        // Generiert die Städte an zufälligen Orten und erstellt die Verbindungen
        for (int i = 0; i < STAEDTE; i++)
        {
            new Stadt(this);
        }
        
        // new Stadt(this, 570, 148);
        // new Stadt(this, 251, 295);
        // new Stadt(this, 363, 150);
        // new Stadt(this, 500, 237);
        // new Stadt(this, 462, 61);
        
    }

    /**
     * permutate - writes all the permutations of the toProcess List on screen using recursion
     *
     * @param  prev  The part of the List already traversed (use an empty list in the beginning)
     * @param  toProcess  The list that is to be permutated
     */
    private static void searchBest(ArrayList<Integer> prev,ArrayList<Integer> toProcess)
    {   
        ArrayList<Integer> nextLvlPrev, nextLvlProc;
        if (toProcess.size() != 0) {
            for (int i=0; i<toProcess.size(); i++) {
                nextLvlPrev = new ArrayList<Integer> (prev);
                nextLvlPrev.add(toProcess.get(i));
                nextLvlProc = new ArrayList<Integer> (toProcess);
                nextLvlProc.remove(i);
                searchBest (nextLvlPrev, nextLvlProc);
            }
        }
        else{
            // printList(prev);
        }
    }

    public void findeBestenWeg()
    {
        ArrayList<Stadt> unBesucht = Stadt.getAlleStaedte();  // Alle unbesuchten Städte
        bestDist = new RundReise(unBesucht).getDistanz()+1; // die Distanz der einfachsten Rundreise
        besterWeg.markiere(Verbindung.urFarbe);
        besterWeg = new RundReise();
        besterWeg.add(unBesucht.remove(0)); 
        besterWeg = recuBesterWeg(besterWeg, unBesucht, bestDist);
        besterWeg.markiere(Color.RED);
    }

    private RundReise recuBesterWeg(RundReise besucht, ArrayList<Stadt> unbesucht, double besteDistanz)
    {
        RundReise besterWeg = null;
        RundReise testWeg;
        if( besucht.getDistanz() > bestDist ) {
            return null;
        }
        if( unbesucht.isEmpty() ) {
            return besucht;
        }
        RundReise neuBesucht;
        ArrayList<Stadt> neuUnbesucht;
        for ( int i=0; i < unbesucht.size(); i++) {
            neuBesucht = new RundReise( besucht );
            neuUnbesucht = new ArrayList<Stadt>(unbesucht);
            neuBesucht.add(neuUnbesucht.remove(i));
            testWeg = recuBesterWeg(neuBesucht, neuUnbesucht, besteDistanz);
            if( testWeg != null && testWeg.getDistanz() < besteDistanz) {
                besteDistanz = testWeg.getDistanz();
                besterWeg = testWeg;
            }
        }
        return besterWeg;
    }
}
