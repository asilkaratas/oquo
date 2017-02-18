package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class Book
{
    @SerializedName("Id")
    private int id;

    @SerializedName("Title")
    private String title;

    @SerializedName("PublishedYear")
    private String publishedYear;

    @SerializedName("PhotoUrl")
    private String coverPhoto;

    @SerializedName("Author")
    private Author author = null;

    public Book()
    {

    }

    public Book(int id, String title, String publishedYear, String coverPhoto)
    {
        setId(id);
        setTitle(title);
        setPublishedYear(publishedYear);
        setCoverPhoto(coverPhoto);
    }

    public Book(String title, String publishedYear, String coverPhoto)
    {
        this(0, title, publishedYear, coverPhoto);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPublishedYear()
    {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear)
    {
        this.publishedYear = publishedYear;
    }

    public String getCoverPhoto()
    {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto)
    {
        this.coverPhoto = coverPhoto;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
