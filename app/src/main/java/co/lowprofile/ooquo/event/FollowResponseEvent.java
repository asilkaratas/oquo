package co.lowprofile.ooquo.event;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class FollowResponseEvent
{
    private int userId;

    public FollowResponseEvent(int userId)
    {
        this.userId = userId;
    }

    public int getUserId()
    {
        return userId;
    }
}
