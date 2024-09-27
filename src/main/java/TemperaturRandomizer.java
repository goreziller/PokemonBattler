import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TemperaturRandomizer
{
    private double aktuelleTemperature;
    private double minTemperatur;
    private double maxTemperatur;

    public int prozentualleTemperaturZahl(int anzahlBereiche)
    {
        aktuelleTemperatur();

        double temperaturDifferenz = maxTemperatur - minTemperatur;
        double temperaturProBereich = temperaturDifferenz / anzahlBereiche;
        double[] prozentsaetze = new double[anzahlBereiche];
        int aktuellerBereich = (int) Math.floor((aktuelleTemperature - minTemperatur) / temperaturProBereich);
        if(aktuellerBereich >= anzahlBereiche)
        {
            aktuellerBereich = anzahlBereiche -1;
        }
        System.out.println(aktuellerBereich);
        double summe = 200;

        // die summe auf unter/ gleich 100 setzten
        for (double abzug = 5; summe >= 100; abzug += 0.01)
        {
            summe = 0;

            // Prozentsätze für die Bereiche berechnen
            for (int i = 0; i < anzahlBereiche; i++)
            {
                int distanzZumAktuellenBereich = Math.abs(aktuellerBereich - i);
                double kleinerAbzug = abzug / (0.5 + distanzZumAktuellenBereich); // Abzug wird kleiner mit zunehmender
                // Distanz
                double prozent = Math.round(Math.max(0.0, 33.0 - distanzZumAktuellenBereich * kleinerAbzug) * 10.0) / 10.0;
                prozentsaetze[i] = prozent;
                summe += prozent;
            }
            // System.out.println("Summe: " + summe + " Abzug: " + abzug);
        }

        // Ausgabe der Prozentsätze für jeden Bereich
        for (int i = 0; i < anzahlBereiche; i++)
        {
            System.out.println("Bereich " + (i + 1) + ": " + prozentsaetze[i] + "%");
        }

        double zufallszahl = new Random().nextDouble() * summe;
        //System.out.println("zufall: " + zufallszahl + " summe: " + summe);

        // Prozentsätze durchgehen und überprüfen, in welchem Bereich die Zufallszahl liegt
        double kumulativeProzentSumme = 0;
        for (int i = 0; i < prozentsaetze.length; i++)
        {
            kumulativeProzentSumme += prozentsaetze[i];
            if (zufallszahl <= kumulativeProzentSumme)
            {
                System.out.println("Ausgewählte Zahl: " + (i + 1));
                return i + 1;
            }
        }

        // Sollte normalerweise nie erreicht werden
        throw new RuntimeException("Fehler beim Generieren der Zufallszahl.");
    }

    public void aktuelleTemperatur()
    {
        try
        {
            URL url = new URL("https://ipinfo.io/json"); // ipinfo.io
            String json = stream(url);

            String[] parts = extractLocFromJson(json).split(",");
            double latitude = Double.parseDouble(parts[0].trim());
            double longitude = Double.parseDouble(parts[1].trim());

            url = new URL("https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude
                    + "&hourly=temperature_2m&past_days=92&forecast_days=16"); // api.open-meteo.com
            json = stream(url);

            ectractTemperaturFromJson(json);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String stream(URL url) throws IOException
    {
        try (InputStream input = url.openStream())
        {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1)
            {
                json.append((char) c);
            }
            return json.toString();
        }
    }

    public static String extractLocFromJson(String jsonString)
    {
        // Suche nach dem Index von "loc"
        int locIndex = jsonString.indexOf("loc");
        if (locIndex == -1)
        {
            // "loc" wurde nicht gefunden
            System.out.println("Es wurde nicht gefunden!");
            return null;
        }

        // Extrahiere den Teil nach "loc":"
        String locSubstring = jsonString.substring(locIndex + 7);
        // Suche nach dem Index des Abschlusstokens "
        int endIndex = locSubstring.indexOf("\"");
        if (endIndex == -1)
        {
            // Abschlusstoken " nicht gefunden
            System.out.println("Es wurde nicht gefunden!");
            return null;
        }

        // Extrahiere und gib die "loc" zurück
        return locSubstring.substring(0, endIndex);
    }

    public void ectractTemperaturFromJson(String jsonString)
    {
        try
        {
            int currentTemperatureIndex = jsonString.lastIndexOf("temperature_2m");
            int valueStartIndex = jsonString.indexOf("[", currentTemperatureIndex);
            int valueEndIndex = jsonString.indexOf("]", valueStartIndex);

            // Extrahiere aktuelle Temperatur
            String temperatureValues = jsonString.substring(valueStartIndex + 1, valueEndIndex).trim();
            String[] temperatureArray = temperatureValues.split(",");

            // System.out.println(temperatureArray.length);

            Date jetzt = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
            try
            {
                aktuelleTemperature = Double.parseDouble(temperatureArray[Integer.parseInt(dateFormat.format(jetzt)) + 92 * 24]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            minTemperatur = Double.parseDouble(temperatureArray[0]);
            maxTemperatur = Double.parseDouble(temperatureArray[0]);

            for (String string : temperatureArray)
            {
                double currentTemp = Double.parseDouble(string);
                if (minTemperatur > currentTemp)
                {
                    minTemperatur = currentTemp;
                } else if (maxTemperatur < currentTemp)
                {
                    maxTemperatur = currentTemp;
                }
            }

            // Gib die Ergebnisse aus
            System.out.println("Aktuelle Temperatur: " + aktuelleTemperature + "°C");
            System.out.println("Minimale Temperatur: " + minTemperatur + "°C");
            System.out.println("Maximale Temperatur: " + maxTemperatur + "°C");
        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("Fehler");
            e.printStackTrace();
        }
    }
}
