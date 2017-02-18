package co.lowprofile.ooquo.view.ui;

import co.lowprofile.ooquo.model.Feed;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;

/**
 * Created by asilkaratas on 12/7/15.
 */
public class FeedListItem extends BaseListItem
{
    private Quote quote;
    public FeedListItem(Quote quote)
    {
        this.quote = quote;
    }

    public Quote getQuote()
    {
        return quote;
    }
}
