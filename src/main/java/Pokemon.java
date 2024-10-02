import com.google.gson.Gson;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable
{
    private String name;
    private ArrayList<PokemonType> types;
    private ArrayList<PokemonStat> stats;
    private ArrayList<PokemonMove> moves;
    private PokemonSprites sprites;
    private boolean inBounds;
    private Move move1;
    private Move move2;
    private Move move3;
    private Move move4;
    private int randomMoveNumber1;
    private int randomMoveNumber2;
    private int randomMoveNumber3;
    private int randomMoveNumber4;
    private int size;
    private int level;
    private boolean shiny;

    public Pokemon()
    {
       types = new ArrayList<PokemonType>();
       stats = new ArrayList();
       moves = new ArrayList<>();
       level = 5;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<PokemonType> getTypes() {
        return types;
    }

    public ArrayList<PokemonStat> getStats()
    {
        return stats;
    }

    public ArrayList<PokemonMove> getMoves()
    {
        return moves;
    }

    public PokemonSprites getSprites()
    {
        return sprites;
    }

    public void createMove(Pokemon p)
    {
        try
        {
            Gson gson = new Gson();
            size = p.getMoves().size();
            randomMoveNumber1 = (int) (Math.random() * size);
            java.net.URL url1 = new java.net.URL(p.getMoves().get(randomMoveNumber1).getMove().getUrl());
            String json1 = SelectPokemonController.stream(url1);
            move1 = gson.fromJson(json1, Move.class);

            randomMoveNumber2 = (int) (Math.random() * size);
            java.net.URL url2 = new java.net.URL(p.getMoves().get(randomMoveNumber2).getMove().getUrl());
            String json2 = SelectPokemonController.stream(url2);
            move2 = gson.fromJson(json2, Move.class);

            randomMoveNumber3 = (int) (Math.random() * size);
            java.net.URL url3 = new java.net.URL(p.getMoves().get(randomMoveNumber3).getMove().getUrl());
            String json3 = SelectPokemonController.stream(url3);
            move3 = gson.fromJson(json3, Move.class);

            randomMoveNumber4 = (int) (Math.random() * size);
            java.net.URL url4 = new java.net.URL(p.getMoves().get(randomMoveNumber4).getMove().getUrl());
            String json4 = SelectPokemonController.stream(url4);
            move4 = gson.fromJson(json4, Move.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Move getMove1()
    {
        return move1;
    }

    public Move getMove2()
    {
        return move2;
    }

    public Move getMove3()
    {
        return move3;
    }

    public Move getMove4()
    {
        return move4;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public boolean isShiny()
    {
        return shiny;
    }

    public void setShiny(boolean shiny)
    {
        this.shiny = shiny;
    }

    public void newLevel()
    {
        if(this.level < 100)
        {
            this.level++;
        }
    }

    @Override
    public String toString()
    {
        inBounds = (1 < types.size());
        if(inBounds)
        {
            return  "Pokemon{" +
                    "name='" + name + '\'' +
                    ", typ1 = " + types.get(0).getType().getName() +
                    ", typ2 = " + types.get(1).getType().getName() + "\n" +
                    " " + stats.get(0).getStat().getName() + " = " +  stats.get(0).getBase_stat() +
                    " " + stats.get(1).getStat().getName() + " = " +  stats.get(1).getBase_stat() +
                    " " + stats.get(2).getStat().getName() + " = " +  stats.get(2).getBase_stat() +
                    " " + stats.get(5).getStat().getName() + " = " +  stats.get(5).getBase_stat() + "\n" +
                    " " + sprites +
                    '}';
        }
        else
        {
            return  "Pokemon{" +
                    "name='" + name + '\'' +
                    ", typ1 = " + types.get(0).getType().getName() + "\n" +
                    " " + stats.get(0).getStat().getName() + " = " +  stats.get(0).getBase_stat() +
                    " " + stats.get(1).getStat().getName() + " = " +  stats.get(1).getBase_stat() +
                    " " + stats.get(2).getStat().getName() + " = " +  stats.get(2).getBase_stat() +
                    " " + stats.get(5).getStat().getName() + " = " +  stats.get(5).getBase_stat() + "\n" +
                    " " + sprites +
                    '}';
        }


    }
}
