package co.lowprofile.ooquo.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.AddBookCommand;
import co.lowprofile.ooquo.event.AddBookResponseEvent;
import co.lowprofile.ooquo.event.TakePhotoEvent;
import co.lowprofile.ooquo.model.AppModel;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.NewBook;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.ui.AuthorListItem;
import co.lowprofile.ooquo.view.ui.AuthorListItemView;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class NewBookFragment extends AbstractFragment
{
    private EditText titleInput;
    private EditText publishedYearInput;
    private AuthorListItemView authorView;
    private ImageButton photoButton;

    private Bitmap bitmap = null;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_new_book;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.new_book).toString());

        view.findViewById(R.id.saveBookButton).setOnClickListener(this);
        view.findViewById(R.id.searchAuthorButton).setOnClickListener(this);

        titleInput = (EditText)view.findViewById(R.id.titleInput);
        publishedYearInput = (EditText)view.findViewById(R.id.publishedYearInput);
        authorView = (AuthorListItemView)view.findViewById(R.id.authorView);

        photoButton = (ImageButton)view.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        getView().findViewById(R.id.saveBookButton).setOnClickListener(null);
        getView().findViewById(R.id.searchAuthorButton).setOnClickListener(null);

        titleInput = null;
        publishedYearInput = null;
        authorView = null;

        photoButton.setImageBitmap(null);
        photoButton.setOnClickListener(null);
        photoButton = null;

        bitmap = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        AppModel appModel = OoquoApplication.getAppModel();

        Author author = appModel.getSelectedAuthor();
        if(author != null)
        {
            AuthorListItem authorListItem = new AuthorListItem(author);
            authorListItem.setSelected(true);
            authorView.setItem(authorListItem, 0, null);
            authorView.setVisibility(View.VISIBLE);
        }


        if(appModel.getBitmap() != null)
        {
            appModel.setBookBitmap(appModel.getBitmap());
            appModel.setBitmap(null);
        }

        if(appModel.getBookBitmap() != null)
        {
            bitmap = appModel.getBookBitmap();
            photoButton.setImageBitmap(bitmap);
        }

    }

    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void onSaveBookClick()
    {
        String error = getError();
        if(error != null)
        {
            showAlert(error);
        }
        else
        {
            Author author = OoquoApplication.getAppModel().getSelectedAuthor();

            NewBook newBook = new NewBook();
            newBook.setTitle(titleInput.getText().toString());
            newBook.setPublishedYear(Integer.valueOf(publishedYearInput.getText().toString()));
            newBook.setAuthorId(author.getId());

            new AddBookCommand(newBook, bitmap).executeInBackground();
        }
    }

    private void onSearchAuthorClick()
    {
        OoquoApplication.getAppModel().setSelectedAuthor(null);
        showFragment(SearchAuthorFragment.class);
    }

    private void onPhotoClick()
    {
        EventBus.getDefault().post(new TakePhotoEvent());
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.saveBookButton:
                onSaveBookClick();
                break;

            case R.id.searchAuthorButton:
                onSearchAuthorClick();
                break;

            case R.id.photoButton:
                onPhotoClick();
                break;
        }
    }

    private String getError()
    {
        if(titleInput.getText().length() < 2)
        {
            return getText(R.string.title_error).toString();
        }

        if(publishedYearInput.getText().length() < 4)
        {
            return getText(R.string.published_year_error).toString();
        }

        if(OoquoApplication.getAppModel().getSelectedAuthor() == null)
        {
            return getText(R.string.author_choose_error).toString();
        }

        return null;
    }

    public void onEventMainThread(AddBookResponseEvent event)
    {
        ServiceResponse<Book> response = event.getResponse();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
        }
        else
        {
            OoquoApplication.getAppModel().setBookBitmap(null);
            OoquoApplication.getAppModel().setSelectedBook(response.getData());

            onBackClick();
            onBackClick();
        }
    }

    @Override
    protected void onBackClick()
    {
        OoquoApplication.getAppModel().setBookBitmap(null);
        OoquoApplication.getAppModel().setSelectedAuthor(null);
        super.onBackClick();
    }
}
