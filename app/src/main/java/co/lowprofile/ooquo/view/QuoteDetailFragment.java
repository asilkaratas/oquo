package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.model.Feed;
import co.lowprofile.ooquo.model.GetUsers;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.view.ui.FeedListItem;
import co.lowprofile.ooquo.view.ui.FeedListItemView;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class QuoteDetailFragment extends AbstractFragment
{
    private static final String QUOTE_ID_KEY = "quoteId";
    public static Bundle createBundle(int quoteId)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(QUOTE_ID_KEY, quoteId);
        return bundle;
    }

    private int getQuoteId()
    {
        return getArguments().getInt(QUOTE_ID_KEY);
    }

    private Quote quote = null;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_quote_detail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.quote_detail).toString());


        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        if(quote == null)
        {
            GenericEvent<Integer> event = new GenericEvent<Integer>(EventType.GET_QUOTE_DETAIL, getQuoteId());
            EventBus.getDefault().post(event);
        }
        else
        {
            showFeed();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(GenericEvent event)
    {
        /*
        switch (event.getType())
        {
            case EventType.GET_QUOTE_DETAIL_RESPONSE:
                onGetQuoteDetail(event);
                break;
        }
        */
    }

    /*
    private void onGetQuoteDetail(GenericEvent<GenericResponse<Feed>> event)
    {
        feed = event.getData().getData();
        showFeed();
    }
    */

    @Override
    protected void onBackClick()
    {
        quote = null;
        super.onBackClick();
    }

    private void showFeed()
    {
        FeedListItem feedListItem = new FeedListItem(quote);
        FeedListItemView feedListItemView = (FeedListItemView)getView().findViewById(R.id.feedListItem);
        feedListItemView.setItem(feedListItem, 0, this);
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.likesButton:
                onLikesClick(v);
                break;

            case R.id.profilePhoto:
            case R.id.userNameButton:
                onUserClick(v);
                break;

            case R.id.bookAuthorNameButton:
                onBookClick(v);
                break;
        }
    }

    private void onLikesClick(View v)
    {

        String title = getText(R.string.likes).toString();
        showFragment(UsersFragment.class, UsersFragment.createBundle(title, GetUsers.Type.LIKES, quote.getId()));
    }

    private void onUserClick(View v)
    {
        showFragment(UserProfileFragment.class, UserProfileFragment.createBundle(quote.getUser().getId()));
    }

    private void onBookClick(View v)
    {
        showFragment(BookPofileFragment.class, BookPofileFragment.createBundle(quote.getBook().getId()));
    }

}
