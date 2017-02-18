package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class Comment
{
    @SerializedName("Id")
    private int id;

    @SerializedName("Text")
    private String text;

    @SerializedName("User")
    private User user;

    public Comment(int id, String text)
    {
        this.id = id;
        this.text = text;
    }

    public Comment(String text)
    {
        this(0, text);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
