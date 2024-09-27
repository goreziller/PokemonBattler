import java.io.Serializable;

public class Statistik implements Serializable
{
    private int siege = 0;
    private int niederlagen = 0;
    private int spielStunden = 0;
    private int spielMinuten = 0;
    private int spielSekunden = 0;

    private boolean timerRunnung = false;
    public int getSiege()
    {
        return siege;
    }
    public int getNiederlagen()
    {
        return niederlagen;
    }
    public int getSpielStunden()
    {
        return spielStunden;
    }
    public int getSpielMinuten()
    {
        return spielMinuten;
    }
    public int getSpielSekunden()
    {
        return spielSekunden;
    }
    public void setSiege(int siege)
    {
        this.siege = siege;
    }
    public void setNiederlagen(int niederlagen)
    {
        this.niederlagen = niederlagen;
    }
    public void setSpielStunden(int spielStunden)
    {
        this.spielStunden = spielStunden;
    }
    public void setSpielMinuten(int spielMinuten)
    {
        this.spielMinuten = spielMinuten;
    }
    public void setSpielSekunden(int spielSekunden)
    {
        this.spielSekunden = spielSekunden;
    }

    public void neueNiederlage()
    {
        niederlagen++;
    }

    public void neuerSieg()
    {
        siege++;
    }

    public void start()
    {
        timerRunnung = true;
        Thread timeThread = new Thread(() -> timeRunning());
        timeThread.start();
    }

    public void stopTimer()
    {
        timerRunnung = false;
    }

    public void timeRunning()
    {
        while (timerRunnung)
        {
            if (spielSekunden >= 60)
            { // Wenn 60 Sekunden erreicht sind
                spielSekunden = 0; // Sekunden zurücksetzen
                spielMinuten++; // Minuten erhöhen
                if (spielMinuten >= 60)
                { // Wenn 60 Minuten erreicht sind
                    spielMinuten = 0; // Minuten zurücksetzen
                    spielStunden++; // Stunden erhöhen
                }
            }
            System.out.println(spielStunden + ":" + spielMinuten + ":" + spielSekunden);
            try
            {
                Thread.sleep(1000); // 1000 milliseconds = 1 second
                spielSekunden ++; // Sekunden erhöhen
            }
            catch (InterruptedException e)
            {
                System.out.println("Fehler");
            }
        }
    }
}

