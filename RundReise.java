import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)
import java.util.ArrayList;

class RundReise
{
    private ArrayList<Stadt> stationen = new ArrayList<Stadt>();
    private double distanz = 0.0;

    public RundReise()
    {       
    }
    
    public RundReise(ArrayList<Stadt> stationen)
    {
        int n = stationen.size();
        for( int i = 0; i < n; i++ ) {
            this.stationen.add(stationen.get(i));
        }
        calcDistanz();
    }
    
    // public RundReise(ArrayList<Stadt> stationen)
    // {
        // this.stationen = (ArrayList<Stadt>) stationen.clone();
        // this.calcDistanz();        
    // }
    
    public RundReise(RundReise orig)
    {
        this.stationen = (ArrayList<Stadt>) orig.stationen.clone();
        this.distanz = orig.distanz;        
    }
    
    public void add(Stadt station)
    {
        int n = stationen.size();
        stationen.add(station);
        if (n > 0) {
            distanz -= stationen.get(n-1).getEntf(stationen.get(0));    
            distanz += stationen.get(n).getEntf(stationen.get(n-1));
            distanz += stationen.get(n).getEntf(stationen.get(0));
        }
    }

    public void cut()
    {
        int n = stationen.size();
        if (n >= 2) {
            distanz -= stationen.get(n-1).getEntf(stationen.get(n-2));
            distanz -= stationen.get(n-1).getEntf(stationen.get(0));    
            distanz += stationen.get(n-2).getEntf(stationen.get(0));   
        }
        stationen.remove(n-1);
    }

    public double getDistanz()
    {
        return distanz;
    }

    public double calcDistanz()
    {
        distanz = 0.0;
        int n = stationen.size();
        if( n > 1) {
            for(int i = 1; i < n; i++) {
                distanz += stationen.get(i-1).getEntf(stationen.get(i));
            }
            distanz += stationen.get(stationen.size()-1).getEntf(stationen.get(0));
        }
        return distanz;
    }
    
    public void markiere(Color farbe)
    {
        int n = this.stationen.size();
        for( int i = 1; i < n; i++) {
            stationen.get(i).getVerbindung(stationen.get(i-1)).setFarbe(farbe);
        }
        if( n>1 ) {stationen.get(n-1).getVerbindung(stationen.get(0)).setFarbe(farbe);}
    }
}