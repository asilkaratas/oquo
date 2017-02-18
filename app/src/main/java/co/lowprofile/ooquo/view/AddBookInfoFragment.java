package co.lowprofile.ooquo.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.AddQuoteCommand;
import co.lowprofile.ooquo.event.AddQuoteResponseEvent;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.model.AppModel;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.NewQuote;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.ui.BookListItem;
import co.lowprofile.ooquo.view.ui.BookListItemView;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class AddBookInfoFragment extends AbstractFragment
{
    private EditText infoInput;
    private BookListItemView bookView;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_add_book_info;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.add_book_info).toString());

        view.findViewById(R.id.saveButton).setOnClickListener(this);
        view.findViewById(R.id.searchBookButton).setOnClickListener(this);

        infoInput = (EditText)view.findViewById(R.id.infoInput);
        bookView = (BookListItemView)view.findViewById(R.id.bookView);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        getView().findViewById(R.id.saveButton).setOnClickListener(null);
        getView().findViewById(R.id.searchBookButton).setOnClickListener(null);

        infoInput = null;
        bookView = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        Book book = OoquoApplication.getAppModel().getSelectedBook();
        if(book != null)
        {
            BookListItem bookListItem = new BookListItem(book);
            bookListItem.setSelected(true);
            bookView.setItem(bookListItem, 0, null);
            bookView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void onSaveClick()
    {
        String error = getError();
        if(error != null)
        {
            showAlert(error);
        }
        else
        {
            AppModel appModel = OoquoApplication.getAppModel();
            Book book = appModel.getSelectedBook();

            NewQuote newQuote = new NewQuote();
            newQuote.setText(infoInput.getText().toString());
            newQuote.setBookId(book.getId());

            new AddQuoteCommand(newQuote, appModel.getQuoteBitmap()).executeInBackground();
        }
    }

    private void onSearchBookClick()
    {
        OoquoApplication.getAppModel().setSelectedBook(null);
        showFragment(SearchBookFragment.class);
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.saveButton:
                onSaveClick();
                break;

            case R.id.searchBookButton:
                onSearchBookClick();
                break;
        }
    }

    private String getError()
    {
        if(infoInput.getText().length() < 5)
        {
            return getText(R.string.info_error).toString();
        }

        if(OoquoApplication.getAppModel().getSelectedBook() == null)
        {
            return getText(R.string.book_choose_error).toString();
        }

        return null;
    }

    public void onEventMainThread(AddQuoteResponseEvent event)
    {
        ServiceResponse<Quote> response = event.getResponse();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
        }
        else
        {
            //OoquoApplication.getAppModel().setSelectedAuthor(event.getResponse().getAuthor());

            AppModel appModel = OoquoApplication.getAppModel();
            appModel.setRefreshFeed(true);
            appModel.setQuoteBitmap(null);

            onBackClick();
            onBackClick();
            //onBackClick();
        }
    }

    @Override
    protected void onBackClick()
    {
        OoquoApplication.getAppModel().setSelectedBook(null);
        super.onBackClick();
    }
}
