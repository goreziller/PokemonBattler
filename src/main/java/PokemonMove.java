import java.io.Serializable;
import java.util.ArrayList;

public class PokemonMove implements Serializable
{
    private NamedAPIResource move;

    private ArrayList <PokemonMoveVersion> version_group_details;

    public NamedAPIResource getMove() {
        return move;
    }
}
