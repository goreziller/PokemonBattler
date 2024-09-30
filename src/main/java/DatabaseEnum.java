import lombok.Getter;

public enum DatabaseEnum
{
    PokemonTypesPath("jdbc:ucanaccess://src/main/resources/database/PokemonTypes.accdb");

    private DatabaseEnum(String path)
    {
        this.path = path;
    }
    @Getter
    private String path;
}
