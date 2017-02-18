package co.lowprofile.ooquo.view.ui;

import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;

/**
 * Created by asilkaratas on 12/7/15.
 */
public class QuoteListItem extends BaseListItem
{
    private Quote quote;
    public QuoteListItem(Quote quote)
    {
        this.quote = quote;
    }

    public Quote getQuote()
    {
        return quote;
    }
}
