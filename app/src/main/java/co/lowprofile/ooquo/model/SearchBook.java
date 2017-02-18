package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 12/13/15.
 */
public class SearchBook
{
    public enum Type
    {
        USER,
        AUTHOR
    }

    private Type type;
    private int id;
    private String key;

    public SearchBook(Type type, int id, String key)
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
