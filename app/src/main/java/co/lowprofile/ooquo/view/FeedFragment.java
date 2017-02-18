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
import co.lowprofile.ooquo.controller.GetFeedCommand;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.FeedResponseEvent;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.model.Feed;
import co.lowprofile.ooquo.model.GetUsers;
import co.lowprofile.ooquo.model.Quote;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.ui.FeedListItem;
import co.lowprofile.ooquo.view.ui.FeedListItemView;
import co.lowprofile.ooquo.view.common.fragment.list.AbstractListFragment;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class FeedFragment extends AbstractListFragment
{
    private List<Quote> feeds = null;
    private List<FeedListItem> items = null;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_feed;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsFeedHeader();

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

        /*
        if(feeds == null || OoquoApplication.getAppModel().isRefreshFeed())
        {
            OoquoApplication.getAppModel().setRefreshFeed(false);
            new GetFeedCommand().executeInBackground();
        }
        else
        {
            showResponse();
        }
        */

        new GetFeedCommand().executeInBackground();
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
        viewMap.put(FeedListItem.class, FeedListItemView.class);
        return viewMap;
    }

    @Override
    protected void onCameraClick()
    {
        OoquoApplication.getAppModel().setTakePhoto(true);
        showFragment(NewQuoteFragment.class);
    }

    public void onEventMainThread(FeedResponseEvent event)
    {
        ServiceResponse<List<Quote>> response = event.getResponse();
        feeds = response.getData();
        showResponse();
    }

    private void showResponse()
    {
        List<FeedListItem> items = new ArrayList<FeedListItem>();
        for(Quote quote: feeds)
        {
            items.add(new FeedListItem(quote));
        }

        setItems(items);
    }

    private void onLikesClick(View v)
    {
        FeedListItem item = (FeedListItem)getListAdapter().getItem(Integer.valueOf(v.getTag().toString()));
        Quote quote = item.getQuote();

        String title = getText(R.string.likes).toString();
        showFragment(UsersFragment.class, UsersFragment.createBundle(title, GetUsers.Type.LIKES, quote.getId()));
    }

    private void onUserClick(View v)
    {
        FeedListItem item = (FeedListItem)getListAdapter().getItem(Integer.valueOf(v.getTag().toString()));
        showFragment(UserProfileFragment.class, UserProfileFragment.createBundle(item.getQuote().getUser().getId()));
    }

    private void onBookClick(View v)
    {
        FeedListItem item = (FeedListItem)getListAdapter().getItem(Integer.valueOf(v.getTag().toString()));
        showFragment(BookPofileFragment.class, BookPofileFragment.createBundle(item.getQuote().getBook().getId()));
    }

    private void onLikeClick(View v)
    {
        /*
        FeedListItem item = (FeedListItem)getListAdapter().getItem((int)v.getTag());
        Feed feed = item.getFeed();

        LikeEvent event = new LikeEvent(feed.getQuote().getId(), !feed.isLiked());
        EventBus.getDefault().post(event);
        */
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

            case R.id.likeButton:
                onLikeClick(v);
                break;
        }
    }
}
