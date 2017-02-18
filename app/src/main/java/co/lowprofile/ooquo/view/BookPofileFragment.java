package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.GetBookProfileCommand;
import co.lowprofile.ooquo.event.GetBookProfileResponseEvent;
import co.lowprofile.ooquo.model.Book;
import co.lowprofile.ooquo.model.BookProfile;
import co.lowprofile.ooquo.model.SearchQuote;
import co.lowprofile.ooquo.view.ui.TabButton;
import co.lowprofile.ooquo.view.common.fragment.tab.AbstractTabFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class BookPofileFragment extends AbstractTabFragment
{
    private static final String BOOK_ID_KEY = "bookId";
    public static Bundle createBundle(int bookId)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(BOOK_ID_KEY, bookId);
        return bundle;
    }

    private int getBookId()
    {
        return getArguments().getInt(BOOK_ID_KEY);
    }

    private BookProfile bookProfile = null;

    private Button authorNameButton;
    private TextView bookTitleText;
    private TextView publishedYearText;
    private TabButton quotesButton;
    private ImageView coverPhoto;


    @Override
    protected int getLayout()
    {
        return R.layout.fragment_book_profile;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.book_info).toString());

        authorNameButton = (Button)view.findViewById(R.id.authorNameButton);
        authorNameButton.setOnClickListener(this);

        bookTitleText = (TextView)view.findViewById(R.id.bookTitleText);
        publishedYearText = (TextView)view.findViewById(R.id.publishedYearText);

        coverPhoto = (ImageView)view.findViewById(R.id.coverPhoto);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        authorNameButton.setOnClickListener(null);
        authorNameButton = null;
        bookTitleText = null;
        publishedYearText = null;

        quotesButton = null;

        coverPhoto.setImageBitmap(null);
        coverPhoto = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        if(bookProfile == null)
        {
            new GetBookProfileCommand(getBookId()).executeInBackground();
        }
        else
        {
            showBookProfile();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected List<Class<? extends Fragment>> getFragmentClasses()
    {
        List<Class<? extends Fragment>> fragmentClasses = new ArrayList<Class<? extends Fragment>>();
        fragmentClasses.add(SearchQuoteFragment.class);
        fragmentClasses.add(AboutBookFragment.class);

        return fragmentClasses;
    }

    @Override
    protected List<Bundle> getFragmentArguments()
    {
        List<Bundle> fragmentArguments = new ArrayList<Bundle>();
        fragmentArguments.add(SearchQuoteFragment.createBundle(SearchQuote.Type.BOOK, getBookId()));
        fragmentArguments.add(null);
        return fragmentArguments;
    }

    @Override
    public List<View> getTabButtons()
    {
        quotesButton = new TabButton(getTabLayout().getContext());
        quotesButton.getTitleText().setText(getResources().getString(R.string.quotes));

        TabButton aboutBookButton = new TabButton(getTabLayout().getContext());
        aboutBookButton.getTitleText().setText(getResources().getString(R.string.about_book));
        aboutBookButton.getCountText().setText("");

        List<View> tabButtons = new ArrayList<View>();
        tabButtons.add(quotesButton);
        tabButtons.add(aboutBookButton);
        return tabButtons;
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.authorNameButton:
                onAuthorNameClick();
                break;
        }
    }

    private void onAuthorNameClick()
    {
        showFragment(AuthorProfileFragment.class, AuthorProfileFragment.createBundle(bookProfile.getAuthor().getId()));
    }

    public void onEventMainThread(GetBookProfileResponseEvent event)
    {
        bookProfile = event.getResponse().getData();
        showBookProfile();
    }

    @Override
    protected void onBackClick()
    {
        bookProfile = null;
        super.onBackClick();
    }

    private void showBookProfile()
    {
        authorNameButton.setText(bookProfile.getAuthor().getFullName());
        bookTitleText.setText(bookProfile.getTitle());
        publishedYearText.setText(bookProfile.getPublishedYear().toString());

        quotesButton.getCountText().setText(String.valueOf(bookProfile.getQuoteCount()));


        coverPhoto.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(coverPhoto);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(bookProfile.getCoverPhoto())
                .into(coverPhoto);

        AboutBookFragment aboutBookFragment = (AboutBookFragment)getChildFragmentManager().getFragments().get(1);
        if(aboutBookFragment != null)
        {
            aboutBookFragment.setBook(bookProfile);
        }

    }


}
