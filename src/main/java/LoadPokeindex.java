import com.google.gson.Gson;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class LoadPokeindex
{
    private ArrayList<Integer> normalList;
    private ArrayList<Integer> fireList;
    private ArrayList<Integer> waterList;
    private ArrayList<Integer> grassList;
    private ArrayList<Integer> flyingList;
    private ArrayList<Integer> fightingList;
    private ArrayList<Integer> poisonList;
    private ArrayList<Integer> electricList;
    private ArrayList<Integer> groundList;
    private ArrayList<Integer> rockList;
    private ArrayList<Integer> psychicList;
    private ArrayList<Integer> iceList;
    private ArrayList<Integer> bugList;
    private ArrayList<Integer> ghostList;
    private ArrayList<Integer> steelList;
    private ArrayList<Integer> dragonList;
    private ArrayList<Integer> darkList;
    private ArrayList<Integer> fairyList;

    public ArrayList<Integer> getNormalList() {
        return normalList;
    }

    public ArrayList<Integer> getFireList() {
        return fireList;
    }

    public ArrayList<Integer> getWaterList() {
        return waterList;
    }

    public ArrayList<Integer> getGrassList() {
        return grassList;
    }

    public ArrayList<Integer> getFlyingList() {
        return flyingList;
    }

    public ArrayList<Integer> getFightingList() {
        return fightingList;
    }

    public ArrayList<Integer> getPoisonList() {
        return poisonList;
    }

    public ArrayList<Integer> getElectricList() {
        return electricList;
    }

    public ArrayList<Integer> getGroundList() {
        return groundList;
    }

    public ArrayList<Integer> getRockList() {
        return rockList;
    }

    public ArrayList<Integer> getPsychicList() {
        return psychicList;
    }

    public ArrayList<Integer> getIceList() {
        return iceList;
    }

    public ArrayList<Integer> getBugList() {
        return bugList;
    }

    public ArrayList<Integer> getGhostList() {
        return ghostList;
    }

    public ArrayList<Integer> getSteelList() {
        return steelList;
    }

    public ArrayList<Integer> getDragonList() {
        return dragonList;
    }

    public ArrayList<Integer> getDarkList() {
        return darkList;
    }

    public ArrayList<Integer> getFairyList() {
        return fairyList;
    }

    private int index = 0;
    private boolean abspeichern = false;
    private JFrame frame;
    private JLabel label;

    public void updatePokeindex()
    {
        zuweisen();

        showFrame();

        boolean schleifenLaedt = true;
        Gson gson = new Gson();
        for(int i = index; schleifenLaedt; i++)
        {
            try
            {
                if(i != index && !abspeichern)
                {
                    abspeichern = true;
                    frame.setVisible(true);
                }
                System.out.println(i);
                java.net.URL url = new java.net.URL("https://pokeapi.co/api/v2/pokemon/" + i);
                String json = stream(url);
                Pokemon p = gson.fromJson(json, Pokemon.class);
                label.setText("Herunterladen: Pokemon index: " + i);
                p.createMove(p);
                for (int j = 0; j < p.getTypes().size(); j++)
                {
                    if (p.getTypes().get(j).getType().getName().equals("normal"))
                    {
                        normalList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("fire"))
                    {
                        fireList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("water"))
                    {
                        waterList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("grass"))
                    {
                        grassList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("flying"))
                    {
                        flyingList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("fighting"))
                    {
                        fightingList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("poison"))
                    {
                        poisonList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("electric"))
                    {
                        electricList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("ground"))
                    {
                        groundList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("rock"))
                    {
                        rockList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("psychic"))
                    {
                        psychicList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("ice"))
                    {
                        iceList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("bug"))
                    {
                        bugList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("ghost"))
                    {
                        ghostList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("steel"))
                    {
                        steelList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("dragon"))
                    {
                        dragonList.add(i);;
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("dark"))
                    {
                        darkList.add(i);
                    }
                    else if (p.getTypes().get(j).getType().getName().equals("fairy"))
                    {
                        fairyList.add(i);
                    }
                    else
                    {
                        System.out.println("Error!");
                    }
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Ende von den Pokemon!");
                index = i;
                frame.dispose(); // Schließen des JFrame
                schleifenLaedt = false;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                new RuntimeException(e);
                schleifenLaedt = false;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                new RuntimeException(e);
                schleifenLaedt = false;
            }
        }
        if (abspeichern)
        {
            abspeichern();
        }
    }

    public void showFrame()
    {
        // Erstelle ein vom Loading Screen
        frame = new JFrame("Update Pokedex");
        label = new JLabel("Herunterladen: Pokemon index: " + index);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(label);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setIconImage(new ImageIcon("file:src/main/Bilder/GameIcon.png").getImage());
    }

    public static String stream(java.net.URL url) throws java.io.IOException
    {
        try (java.io.InputStream input = url.openStream())
        {
            java.io.InputStreamReader isr = new java.io.InputStreamReader(input);
            java.io.BufferedReader reader = new java.io.BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1)
            {
                json.append((char) c);
            }
            return json.toString();
        }
    }

    public void abspeichern()
    {
        pokesafe(normalList, "normalList");
        pokesafe(fireList, "fireList");
        pokesafe(waterList, "waterList");
        pokesafe(grassList, "grassList");
        pokesafe(flyingList, "flyingList");
        pokesafe(fightingList, "fightingList");
        pokesafe(poisonList, "poisonList");
        pokesafe(electricList, "electricList");
        pokesafe(groundList, "groundList");
        pokesafe(rockList, "rockList");
        pokesafe(psychicList, "psychicList");
        pokesafe(iceList, "iceList");
        pokesafe(bugList, "bugList");
        pokesafe(ghostList, "ghostList");
        pokesafe(steelList, "steelList");
        pokesafe(dragonList, "dragonList");
        pokesafe(darkList, "darkList");
        pokesafe(fairyList, "fairyList");

        String file = "src/main/Pokemon/index.ser";
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(index);
            System.out.println("Erfolgreich gespeichert index");
        }
        catch (IOException e)
        {
            System.out.println("Speicherung fehlgeschlagen");
            e.printStackTrace();
        }

    }

    public void zuweisen()
    {
        File file = new File("src/main/Pokemon/index.ser");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            this.index = (Integer)ois.readObject();
        }
        catch (ClassNotFoundException | IOException e)
        {
            System.out.println("Error index");
        }

        normalList = getPokeliste("normalList");
        fireList = getPokeliste("fireList");
        waterList = getPokeliste("waterList");
        grassList = getPokeliste("grassList");
        flyingList = getPokeliste("flyingList");
        fightingList = getPokeliste("fightingList");
        poisonList = getPokeliste("poisonList");
        electricList = getPokeliste("electricList");
        groundList = getPokeliste("groundList");
        rockList = getPokeliste("rockList");
        psychicList = getPokeliste("psychicList");
        iceList = getPokeliste("iceList");
        bugList = getPokeliste("bugList");
        ghostList = getPokeliste("ghostList");
        steelList = getPokeliste("steelList");
        dragonList = getPokeliste("dragonList");
        darkList = getPokeliste("darkList");
        fairyList = getPokeliste("fairyList");

        if (normalList == null || fireList == null || waterList == null || grassList == null || flyingList == null || fightingList == null ||
                poisonList == null || electricList == null || groundList == null || rockList == null || psychicList == null || iceList == null ||
                bugList == null || ghostList == null || steelList == null || dragonList == null || darkList == null || fairyList == null || index == 0)
        {
            System.out.println("Werte werden neu abgefragt!");

            File directory = new File("src/main/Pokemon");
            File[] files = directory.listFiles();
            if(files != null)
            {
                for(File file2 : files)
                {
                    file2.delete();
                }
            }

            normalList = new ArrayList<Integer>();
            fireList = new ArrayList<Integer>();
            waterList = new ArrayList<Integer>();
            grassList = new ArrayList<Integer>();
            flyingList = new ArrayList<Integer>();
            fightingList = new ArrayList<Integer>();
            poisonList = new ArrayList<Integer>();
            electricList = new ArrayList<Integer>();
            groundList = new ArrayList<Integer>();
            rockList = new ArrayList<Integer>();
            psychicList = new ArrayList<Integer>();
            iceList = new ArrayList<Integer>();
            bugList = new ArrayList<Integer>();
            ghostList = new ArrayList<Integer>();
            steelList = new ArrayList<Integer>();
            dragonList = new ArrayList<Integer>();
            darkList = new ArrayList<Integer>();
            fairyList = new ArrayList<Integer>();
            index = 1;
        }
        else
        {
            System.out.println("Alte werte konnten Erfolgreich abgerufen werden!");
        }
    }

    public void pokesafe(ArrayList<Integer> pokemons, String name)
    {
        String file = "src/main/Pokemon/" + name + ".ser";
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(pokemons);
            System.out.println("Erfolgreich gespeichert " + name);
        }
        catch (IOException e)
        {
            System.out.println("Speicherung fehlgeschlagen");
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getPokeliste(String name)
    {
        ArrayList<Integer> indexliste = new ArrayList<Integer>();
        File file = new File("src/main/Pokemon/" + name + ".ser");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            indexliste = (ArrayList<Integer>)ois.readObject();
        }
        catch (ClassNotFoundException | IOException e)
        {
            indexliste = null;
            //JOptionPane.showMessageDialog(null, "Die Liste Konnten nicht geladen werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error " + name);
        }
        return indexliste;
    }
}