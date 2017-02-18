package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 12/13/15.
 */
public class Follow
{
    private int id;
    private User user;
    private User follower;

    public Follow(int id, User user, User follower)
    {
        this.id = id;
        this.user = user;
        this.follower = follower;
    }

    public Follow(User user, User follower)
    {
        this(0, user, follower);
    }

    public int getId()
    {
        return id;
    }

    public User getUser()
    {
        return user;
    }

    public User getFollower()
    {
        return follower;
    }
}
