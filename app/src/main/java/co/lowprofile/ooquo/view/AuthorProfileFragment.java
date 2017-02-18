package co.lowprofile.ooquo.view;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.lowprofile.ooquo.OoquoApplication;
import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.GetAuthorCommand;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.GetAuthorResponseEvent;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.model.AuthorProfile;
import co.lowprofile.ooquo.model.SearchBook;
import co.lowprofile.ooquo.model.SearchQuote;
import co.lowprofile.ooquo.view.ui.TabButton;
import co.lowprofile.ooquo.view.common.fragment.tab.AbstractTabFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class AuthorProfileFragment extends AbstractTabFragment
{
    private static final String AUTHOR_ID_KEY = "authorId";
    public static Bundle createBundle(int authorId)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(AUTHOR_ID_KEY, authorId);
        return bundle;
    }

    private Author author = null;

    private TextView authorNameText;
    private TabButton quotesButton;
    private TabButton booksButton;
    private ImageView profilePhoto;

    private int getAuthorId()
    {
        return getArguments().getInt(AUTHOR_ID_KEY);
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_author_profile;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.author_info).toString());

        authorNameText = (TextView)view.findViewById(R.id.authorNameText);

        profilePhoto = (ImageView)view.findViewById(R.id.profilePhoto);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        authorNameText = null;

        quotesButton = null;

        profilePhoto.setImageBitmap(null);
        profilePhoto = null;

        author = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        if(author == null)
        {
            new GetAuthorCommand(getAuthorId()).executeInBackground();
        }
        else
        {
            showAuthorProfile();
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
        fragmentClasses.add(SearchQuoteBookFragment.class);

        return fragmentClasses;
    }

    @Override
    protected List<Bundle> getFragmentArguments()
    {
        List<Bundle> fragmentArguments = new ArrayList<Bundle>();
        fragmentArguments.add(SearchQuoteFragment.createBundle(SearchQuote.Type.AUTHOR, getAuthorId()));
        fragmentArguments.add(SearchQuoteBookFragment.createBundle(SearchBook.Type.AUTHOR, getAuthorId()));
        return fragmentArguments;
    }

    @Override
    public List<View> getTabButtons()
    {
        quotesButton = new TabButton(getTabLayout().getContext());
        quotesButton.getTitleText().setText(getResources().getString(R.string.quotes));

        booksButton = new TabButton(getTabLayout().getContext());
        booksButton.getTitleText().setText(getResources().getString(R.string.books));

        List<View> tabButtons = new ArrayList<View>();
        tabButtons.add(quotesButton);
        tabButtons.add(booksButton);
        return tabButtons;
    }

    public void onEventMainThread(GetAuthorResponseEvent event)
    {
        author = event.getResponse().getData();
        showAuthorProfile();
    }

    /*
    private void onGetAuthorProfile(GenericEvent<GenericResponse<AuthorProfile>> event)
    {
        authorProfile = event.getData().getData();
        showAuthorProfile();
    }
    */

    @Override
    protected void onBackClick()
    {
        author = null;
        super.onBackClick();
    }

    private void showAuthorProfile()
    {
        authorNameText.setText(author.getFullName());

        profilePhoto.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(profilePhoto);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(author.getPhoto())
                .into(profilePhoto);

        //quotesButton.getCountText().setText(String.valueOf(authorProfile.getQuoteCount()));
        //booksButton.getCountText().setText(String.valueOf(authorProfile.getBookCount()));
    }


}
