import lombok.Getter;

public class PokemonTypeEffectivity
{
    @Getter
    private String type1;
    @Getter
    private String type2;
    @Getter
    private boolean twice;
    @Getter
    private boolean half;
    @Getter
    private boolean zero;

    public PokemonTypeEffectivity(String type1, String type2, boolean twice, boolean half, boolean zero)
    {
        this.type1 = type1;
        this.type2 = type2;
        this.twice = twice;
        this.half = half;
        this.zero = zero;
    }
}
