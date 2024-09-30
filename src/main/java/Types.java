import lombok.Getter;

public class Types
{
    @Getter
    private String Type1;
    @Getter
    private String Type2;
    @Getter
    private String Png1;
    @Getter
    private String Png2;

    public Types(String type1, String type2, String png1, String png2)
    {
        this.Type1 = type1;
        this.Type2 = type2;
        this.Png1 = png1;
        this.Png2 = png2;
    }
}
