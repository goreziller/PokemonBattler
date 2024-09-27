import java.io.Serializable;

public class NamedAPIResource implements Serializable
{
    public String getUrl()
    {
        return url;
    }

    public String getName()
    {
        return name;
    }

    private String name;
    private String url;

    public NamedAPIResource(String name, String url)
    {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
