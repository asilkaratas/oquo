package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 12/13/15.
 */
public class GetUsers
{
    public enum Type
    {
        LIKES,
        FOLLOWING,
        FOLLOWERS
    }

    private Type type;
    private int id;

    public GetUsers(Type type, int id)
    {
        this.type = type;
        this.id = id;
    }

    public Type getType()
    {
        return type;
    }

    public int getId()
    {
        return id;
    }
}
