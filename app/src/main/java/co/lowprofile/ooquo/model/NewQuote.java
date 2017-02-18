package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class NewQuote
{
    @SerializedName("Id")
    private int Id;

    @SerializedName("Text")
    private String text;

    @SerializedName("BookId")
    private int bookId;


    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getBookId()
    {
        return bookId;
    }

    public void setBookId(int bookId)
    {
        this.bookId = bookId;
    }
}
