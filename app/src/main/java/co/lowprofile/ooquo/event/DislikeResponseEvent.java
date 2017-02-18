package co.lowprofile.ooquo.event;

/**
 * Created by asilkaratas on 1/26/16.
 */
public class DislikeResponseEvent
{
    private int quoteId;

    public DislikeResponseEvent(int quoteId)
    {
        this.quoteId = quoteId;
    }

    public int getQuoteId()
    {
        return quoteId;
    }
}
