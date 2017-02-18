package co.lowprofile.ooquo.model;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class Like
{
    private int id;
    private User user;
    private Quote quote;

    public Like(int id, User user, Quote quote)
    {
        this.id = id;
        this.user = user;
        this.quote = quote;
    }

    public Like(User user, Quote quote)
    {
        this(0, user, quote);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Quote getQuote()
    {
        return quote;
    }

    public void setQuote(Quote quote)
    {
        this.quote = quote;
    }
}
