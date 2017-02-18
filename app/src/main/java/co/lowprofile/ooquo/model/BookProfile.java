package co.lowprofile.ooquo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asilkaratas on 12/14/15.
 */
public class BookProfile extends Book
{
    @SerializedName("QuoteCount")
    private int quoteCount;

    public int getQuoteCount()
    {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount)
    {
        this.quoteCount = quoteCount;
    }
}
