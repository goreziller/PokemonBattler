import lombok.Getter;

import javax.swing.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable
{
    private String name;
    private Statistik statistik;
    private ArrayList<Pokemon> pokemonliste;
    private int level;
    @Getter
    private int stage;
    @Getter
    private int coins;
    @Getter
    private int life = 5;
    private int selectedIndex = 0;

    public Player()
    {
        pokemonliste = new ArrayList<Pokemon>();
        statistik = new Statistik();
        level = 1;
        stage = 1;
        coins = 0;
    }

    public Player(Pokemon p, String name)
    {
        this();
        pokemonliste.add(p);
        this.name = name;
        StoredDataManagement storedDataManagement = new StoredDataManagement(this);
    }

    public ArrayList<Pokemon> getPokemonliste()
    {
        return pokemonliste;
    }

    public Statistik getStatistik()
    {
        return statistik;
    }

    public String getName()
    {
        return name;
    }

    public int getLevel()
    {
        return level;
    }

    public int getSelectedIndex()
    {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex)
    {
        this.selectedIndex = selectedIndex;
    }

    public void newStage()
    {
        if(stage > 3)
        {
            newLevel();
            stage = 1;
        }
        else
        {
            stage++;
        }
    }

    public void earnCoins()
    {
        Random rand = new Random();
        int max = 100;
        int min = 25;
        coins = coins + (rand.nextInt(max - min + 1) + min);
        System.out.println("Coins: " + coins);
    }
    public void loseLife()
    {
        if(life > 0)
        {
            life--;
        }
        else
        {
            //Verloren Daten löschen
        }
    }
    public void newLevel()
    {
        level++;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public void addPokemon(Pokemon p)
    {
        if(pokemonliste.size() < 6)
        {
            pokemonliste.add(p);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Die Maximale anzahl an Pokemon wurde schon Erreicht!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString()
    {
        return name;
    }
}
