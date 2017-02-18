package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.model.SearchQuote;
import co.lowprofile.ooquo.view.ui.QuoteListItem;
import co.lowprofile.ooquo.view.ui.QuoteListItemView;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class SearchQuoteFragment extends AbstractSearchFragment
{
    private static final String TYPE_KEY = "type";
    private static final String TYPE_OBJECT_ID_KEY = "typeObjectId";

    public static Bundle createBundle(SearchQuote.Type type, int typeObjectId)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TYPE_KEY, type);
        bundle.putInt(TYPE_OBJECT_ID_KEY, typeObjectId);
        return bundle;
    }

    private SearchQuote.Type getType()
    {
        return (SearchQuote.Type)getArguments().getSerializable(TYPE_KEY);
    }

    private int getTypeObjectId()
    {
        return getArguments().getInt(TYPE_OBJECT_ID_KEY);
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_search_quote;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        search();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public LinkedHashMap<Class<? extends BaseListItem>, Class<? extends ListItemView>> getViewMap()
    {
        LinkedHashMap<Class<? extends BaseListItem>, Class<? extends ListItemView>> viewMap = new LinkedHashMap<Class<? extends BaseListItem>, Class<? extends ListItemView>>();
        viewMap.put(QuoteListItem.class, QuoteListItemView.class);
        return viewMap;
    }

    @Override
    protected void onSearch(String key)
    {
        SearchQuote searchQuote = new SearchQuote(getType(), getTypeObjectId(), key);
        GenericEvent<SearchQuote> event = new GenericEvent<SearchQuote>(EventType.SEARCH_QUOTE, searchQuote);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.quoteListItem:
                onListItemViewClick(v);
                break;
        }
    }

    @Override
    protected void onItemSelected(BaseListItem selectedItem)
    {
        QuoteListItem item = (QuoteListItem)selectedItem;
        showFragment(QuoteDetailFragment.class, QuoteDetailFragment.createBundle(item.getQuote().getId()));
    }

    public void onEventMainThread(GenericEvent event)
    {
        /*
        switch (event.getType())
        {
            case EventType.SEARCH_QUOTE_RESPONSE :
                onSearchQuote(event);
                break;
        }
        */
    }

    /*
    private void onSearchQuote(GenericEvent<GenericResponse<List<Quote>>> event)
    {
        GenericResponse<List<Quote>> response = event.getData();
        List<QuoteListItem> items = new ArrayList<QuoteListItem>();
        for(Quote q: response.getData())
        {
            items.add(new QuoteListItem(q));
        }

        setItems(items);
    }
    */
}
