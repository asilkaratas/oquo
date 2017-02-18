package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 12/13/15.
 */
public class SearchForUser
{
    private int userId;
    private String key;

    public SearchForUser(int userId, String key)
    {
        this.userId = userId;
        this.key = key;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getKey()
    {
        return key;
    }
}
