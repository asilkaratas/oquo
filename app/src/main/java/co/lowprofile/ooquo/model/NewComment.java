package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class NewComment
{
    @SerializedName("Id")
    private int id;

    @SerializedName("Text")
    private String text;

    @SerializedName("QuoteId")
    private int quoteId;


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

    public int getQuoteId()
    {
        return quoteId;
    }

    public void setQuoteId(int quoteId)
    {
        this.quoteId = quoteId;
    }
}
