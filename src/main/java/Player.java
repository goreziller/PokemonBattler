import javax.swing.*;
import java.util.ArrayList;
import java.io.Serializable;

public class Player implements Serializable
{
    private String name;
    private Statistik statistik;
    private ArrayList<Pokemon> pokemonliste;
    private int level;
    private int selectedIndex = 0;

    public Player()
    {
        pokemonliste = new ArrayList<Pokemon>();
        statistik = new Statistik();
        level = 1;
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
