package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class NewBook
{
    @SerializedName("Id")
    private int Id;

    @SerializedName("Title")
    private String title;

    @SerializedName("PublishedYear")
    private int publishedYear;

    @SerializedName("AuthorId")
    private int authorId;


    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getPublishedYear()
    {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear)
    {
        this.publishedYear = publishedYear;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(int authorId)
    {
        this.authorId = authorId;
    }
}
