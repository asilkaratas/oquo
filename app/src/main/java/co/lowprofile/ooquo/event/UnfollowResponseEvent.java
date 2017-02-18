package co.lowprofile.ooquo.event;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class UnfollowResponseEvent
{
    private int userId;

    public UnfollowResponseEvent(int userId)
    {
        this.userId = userId;
    }

    public int getUserId()
    {
        return userId;
    }
}
