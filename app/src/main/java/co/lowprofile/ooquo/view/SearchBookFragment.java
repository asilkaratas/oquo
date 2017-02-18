package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.SearchBookCommand;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.SearchBookResponseEvent;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.ui.BookListItem;
import co.lowprofile.ooquo.view.ui.BookListItemView;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class SearchBookFragment extends AbstractSearchFragment
{
    private static final String TAG = "SearchBookFragment";

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_search_book;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.search_book).toString());

        view.findViewById(R.id.okButton).setOnClickListener(this);
        view.findViewById(R.id.newBookButton).setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        getView().findViewById(R.id.okButton).setOnClickListener(null);
        getView().findViewById(R.id.newBookButton).setOnClickListener(null);

        super.onDestroyView();
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
        new SearchBookCommand(key).executeInBackground();
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.okButton:
                onOkClick();
                break;

            case R.id.newBookButton:
                onNewBookClick();
                break;

            case R.id.bookListItem:
                onListItemViewClick(v);
                break;
        }
    }

    private void onOkClick()
    {
        if(getSelectedBookItem() != null)
        {
            OoquoApplication.getAppModel().setSelectedBook(getSelectedBookItem().getBook());
            onBackClick();
        }
        else
        {
            showAlert(getText(R.string.book_choose_error).toString());
        }
    }

    private void onNewBookClick()
    {
        showFragment(NewBookFragment.class);
    }

    private BookListItem getSelectedBookItem()
    {
        return (BookListItem)getSelectedItem();
    }

    public void onEventMainThread(SearchBookResponseEvent event)
    {
        ServiceResponse<List<Book>> response = event.getResponse();
        List<BookListItem> items = new ArrayList<BookListItem>();
        for(Book b: response.getData())
        {
            items.add(new BookListItem(b));
        }
        setItems(items);
    }

}
