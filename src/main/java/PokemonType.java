import java.io.Serializable;

public class PokemonType implements Serializable
{
    private int slot;

    private NamedAPIResource type;

    public PokemonType(int slot, NamedAPIResource type)
    {
        this.slot = slot;
        this.type = type;
    }

    public NamedAPIResource getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return "PokemonType{" +
                "slot=" + slot +
                ", types=" + type +
                '}';
    }
}
