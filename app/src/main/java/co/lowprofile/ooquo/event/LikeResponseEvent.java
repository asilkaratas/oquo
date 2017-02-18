package co.lowprofile.ooquo.event;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class LikeResponseEvent
{
    private int quoteId;

    public LikeResponseEvent(int quoteId)
    {
        this.quoteId = quoteId;
    }

    public int getQuoteId()
    {
        return quoteId;
    }
}
