import java.io.Serializable;

public class Move implements Serializable
{
    private int power;
    private String name;
    private int accuracy;
    private int pp;
    private int prioity;
    private NamedAPIResource type;

    public int getPower()
    {
        return power;
    }

    public String getName()
    {
        return name;
    }

    public int getAccuracy()
    {
        return accuracy;
    }

    public int getPp()
    {
        return pp;
    }

    public int getPrioity()
    {
        return prioity;
    }

    public NamedAPIResource getType()
    {
        return type;
    }
}
