import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class StoredDataManagement
{
    public StoredDataManagement()
    {

    }

    public StoredDataManagement(Player player)
    {
        Thread timeThread = new Thread(() -> autoSave(player));
        timeThread.start();
    }

    public static ArrayList<Player> getSpielerliste()
    {
        ArrayList<Player> spielerliste = new ArrayList<Player>();
        File file = new File("src/main/player");
        File[] files = file.listFiles();
        if (files != null) // Erforderliche Berechtigungen etc. sind vorhanden
        {
            for (int i = 0; i < files.length; i++)
            {
                String playerFile = files[i].getAbsolutePath().toString();
                try (FileInputStream fis = new FileInputStream(playerFile);
                     ObjectInputStream ois = new ObjectInputStream(fis))
                {
                    spielerliste.add((Player)ois.readObject());
                }
                catch (ClassNotFoundException | IOException e)
                {
                    e.printStackTrace();
                    File file1 = new File(playerFile);
                    file1.delete();
                    JOptionPane.showMessageDialog(null, "Die Spieler Konnten nicht geladen werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    System.out.print("Error");
                }
            }
        }
        return spielerliste;
    }

    public void autoSave(Player player)
    {
        if(player != null)
        {
            while (true)
            {
                try
                {
                    playerSave(player);
                    Thread.sleep(180000); // 180.000 milliseconds = 3 Minuten
                }
                catch (InterruptedException e)
                {
                    System.out.println("Fehler");
                }
            }
        }
    }

    public void playerSave(Player player)
    {
        String file = "src/main/Player/" + player.getName() + ".ser";
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(player);
            System.out.println("Erfolgreich gespeichert");
        }
        catch (IOException e)
        {
            System.out.println("Speicherung fehlgeschlagen");
            e.printStackTrace();
        }
    }
}
