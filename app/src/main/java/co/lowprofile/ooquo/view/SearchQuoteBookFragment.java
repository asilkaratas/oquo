package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.SearchBook;
import co.lowprofile.ooquo.view.ui.BookListItem;
import co.lowprofile.ooquo.view.ui.BookListItemView;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class SearchQuoteBookFragment extends AbstractSearchFragment
{
    private static final String TYPE_KEY = "type";
    private static final String TYPE_OBJECT_ID_KEY = "typeObjectId";

    public static Bundle createBundle(SearchBook.Type type, int typeObjectId)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TYPE_KEY, type);
        bundle.putInt(TYPE_OBJECT_ID_KEY, typeObjectId);
        return bundle;
    }

    private SearchBook.Type getType()
    {
        return (SearchBook.Type)getArguments().getSerializable(TYPE_KEY);
    }

    private int getTypeObjectId()
    {
        return getArguments().getInt(TYPE_OBJECT_ID_KEY);
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_search_quote_book;
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
        viewMap.put(BookListItem.class, BookListItemView.class);
        return viewMap;
    }

    @Override
    protected void onSearch(String key)
    {
        SearchBook searchBook = new SearchBook(getType(), getTypeObjectId(), key);
        GenericEvent<SearchBook> event = new GenericEvent<SearchBook>(EventType.SEARCH_QUOTE_BOOK, searchBook);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.bookListItem:
                onListItemViewClick(v);
                break;
        }
    }

    @Override
    protected void onItemSelected(BaseListItem selectedItem)
    {
        BookListItem item = (BookListItem)selectedItem;
        showFragment(BookPofileFragment.class, BookPofileFragment.createBundle(item.getBook().getId()));
    }

    public void onEventMainThread(GenericEvent event)
    {
        /*
        switch (event.getType())
        {
            case EventType.SEARCH_QUOTE_BOOK_RESPONSE :
                onSearchQuoteBook(event);
                break;
        }
        */
    }

    /*
    private void onSearchQuoteBook(GenericEvent<GenericResponse<List<Book>>> event)
    {
        GenericResponse<List<Book>> response = event.getData();
        List<BookListItem> items = new ArrayList<BookListItem>();
        for(Book b: response.getData())
        {
            items.add(new BookListItem(b));
        }

        setItems(items);
    }
    */
}
