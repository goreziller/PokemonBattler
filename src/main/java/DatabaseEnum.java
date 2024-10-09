import lombok.Getter;

public enum DatabaseEnum
{
    PokemonTypesPath("jdbc:ucanaccess://src/main/resources/database/PokemonTypes.accdb"),
    PokemonTypeEffectivityPath("jdbc:ucanaccess://src/main/resources/database/PokemonEffectivity.accdb");

    @Getter
    private String _path;

    public String getPath()
    {
        return _path;
    }

    private DatabaseEnum(String path)
    {
        this._path = path;
    }
}
