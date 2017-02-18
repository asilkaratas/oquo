package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 12/13/15.
 */
public class SearchQuote
{
    public enum Type
    {
        USER,
        BOOK,
        AUTHOR
    }

    private Type type;
    private int id;
    private String key;

    public SearchQuote(Type type, int id, String key)
    {
        this.type = type;
        this.id = id;
        this.key = key;
    }

    public Type getType()
    {
        return type;
    }

    public int getId()
    {
        return id;
    }

    public String getKey()
    {
        return key;
    }
}
