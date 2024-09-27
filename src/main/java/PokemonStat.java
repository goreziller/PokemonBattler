import java.io.Serializable;

public class PokemonStat implements Serializable
{
    private NamedAPIResource stat;
    private int effort;
    private int base_stat;

    public PokemonStat(NamedAPIResource stat, int effort, int base_stat)
    {
        this.stat = stat;
        this.effort = effort;
        this.base_stat = base_stat;
    }

    public NamedAPIResource getStat()
    {
        return stat;
    }

    public int getBase_stat()
    {
        return base_stat;
    }

    @Override
    public String toString() {
        return "PokemonStat{" +
                "stat=" + stat +
                ", effort=" + effort +
                ", base_stat=" + base_stat +
                '}';
    }
}
