package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class AboutBookFragment extends AbstractFragment
{
    private TextView authorText;
    private TextView publishedYearText;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_about_book;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        authorText = (TextView)view.findViewById(R.id.authorText);
        publishedYearText = (TextView)view.findViewById(R.id.publishedYearText);

        return view;
    }

    public void setBook(BookProfile book)
    {
        authorText.setText(book.getAuthor().getFullName());
        publishedYearText.setText(book.getPublishedYear());
    }

    @Override
    public void onDestroyView()
    {
        authorText = null;
        publishedYearText = null;

        super.onDestroyView();
    }

}
