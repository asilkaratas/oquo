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
import co.lowprofile.ooquo.controller.FollowCommand;
import co.lowprofile.ooquo.controller.GetFollowerCountCommand;
import co.lowprofile.ooquo.controller.GetUserProfileCommand;
import co.lowprofile.ooquo.controller.UnfollowCommand;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.FollowResponseEvent;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.GetFollowerCountResponseEvent;
import co.lowprofile.ooquo.event.GetUserProfileResponseEvent;
import co.lowprofile.ooquo.event.UnfollowResponseEvent;
import co.lowprofile.ooquo.model.Follow;
import co.lowprofile.ooquo.model.GetUsers;
import co.lowprofile.ooquo.model.SearchBook;
import co.lowprofile.ooquo.model.SearchQuote;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.model.UserProfile;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.ui.FollowButton;
import co.lowprofile.ooquo.view.ui.TabButton;
import co.lowprofile.ooquo.view.common.fragment.tab.AbstractTabFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class UserProfileFragment extends AbstractTabFragment
{
    private static final String TAG = "UsersFragment";

    private static final String USER_ID_KEY = "userId";
    public static Bundle createBundle(int userId)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(USER_ID_KEY, userId);
        return bundle;
    }

    private int getUserId()
    {
        return getArguments().getInt(USER_ID_KEY);
    }
    private UserProfile userProfile = null;

    private Button editProfileButton;
    private Button followButton;

    private TextView fullNameText;

    private FollowButton followerButton;
    private FollowButton followingButton;

    private TabButton quotesButton;
    private TabButton booksButton;

    private ImageView profilePhoto;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_profile;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.profile).toString());

        editProfileButton = (Button)view.findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(this);

        followButton = (Button)view.findViewById(R.id.followButton);
        followButton.setOnClickListener(this);

        fullNameText = (TextView)view.findViewById(R.id.fullNameText);

        followerButton = (FollowButton)view.findViewById(R.id.followerButton);
        followerButton.setOnClickListener(this);

        followingButton = (FollowButton)view.findViewById(R.id.followingButton);
        followingButton.setOnClickListener(this);
        followingButton.getTitleText().setText(getText(R.string.following).toString());

        profilePhoto = (ImageView)view.findViewById(R.id.profilePhoto);

        setAsOwnProfile(true);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        editProfileButton.setOnClickListener(null);
        followButton.setOnClickListener(null);

        editProfileButton = null;
        followButton = null;

        fullNameText = null;

        followerButton.setOnClickListener(null);
        followerButton = null;

        followingButton.setOnClickListener(null);
        followingButton = null;

        quotesButton = null;
        booksButton = null;

        profilePhoto.setImageBitmap(null);
        profilePhoto = null;

        userProfile = null;

        super.onDestroyView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        EventBus.getDefault().register(this);

        new GetUserProfileCommand(getUserId()).executeInBackground();
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
        fragmentArguments.add(SearchQuoteFragment.createBundle(SearchQuote.Type.USER, getUserId()));
        fragmentArguments.add(SearchQuoteBookFragment.createBundle(SearchBook.Type.USER, getUserId()));
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

    private void setAsOwnProfile(boolean isOwnProfile)
    {
        int editButtonVisibility = isOwnProfile ? Button.VISIBLE : Button.GONE;
        int followButtonVisibility = isOwnProfile ? Button.GONE : Button.VISIBLE;

        editProfileButton.setVisibility(editButtonVisibility);
        followButton.setVisibility(followButtonVisibility);
    }

    @Override
    protected void onBackClick()
    {
        userProfile = null;
        super.onBackClick();
    }

    public void onEventMainThread(GetUserProfileResponseEvent event)
    {
        ServiceResponse<UserProfile> response = event.getResponse();
        if(response.hasError())
        {
            showAlert(response.getError().getMessage());
            return;
        }

        userProfile = response.getData();
        showUserProfile();
    }

    public void onEventMainThread(FollowResponseEvent event)
    {
        updateFollowButton();

        new GetFollowerCountCommand(userProfile).executeInBackground();
    }

    public void onEventMainThread(UnfollowResponseEvent event)
    {
        updateFollowButton();

        new GetFollowerCountCommand(userProfile).executeInBackground();
    }

    public void onEventMainThread(GetFollowerCountResponseEvent event)
    {
        userProfile.setFollowerCount(userProfile.getFollowerCount());
    }

    private void showUserProfile()
    {
        fullNameText.setText(userProfile.getFullName());

        profilePhoto.setImageBitmap(null);

        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .cancelRequest(profilePhoto);
        Picasso.with(OoquoApplication.getInstance().getBaseContext())
                .load(userProfile.getPhotoUrl())
                .into(profilePhoto);

        setAsOwnProfile(userProfile.isSelf());

        updateFollowButton();
    }

    private void updateFollowButton()
    {
        int titleResourse = userProfile.isFollowed() ? R.string.unfollow : R.string.follow;
        String title = getText(titleResourse).toString();
        followButton.setText(title);

        followerButton.getCountText().setText(String.valueOf(userProfile.getFollowerCount()));
        followingButton.getCountText().setText(String.valueOf(userProfile.getFollowingCount()));
    }

    private void onEditProfileClick()
    {
        showFragment(EditUserFragment.class);
    }

    private void onFollowerClick()
    {
        String title = getText(R.string.followers).toString();
        showFragment(UsersFragment.class, UsersFragment.createBundle(title, GetUsers.Type.FOLLOWERS, userProfile.getId()));
    }

    private void onFollowingClick()
    {
        String title = getText(R.string.following).toString();
        showFragment(UsersFragment.class, UsersFragment.createBundle(title, GetUsers.Type.FOLLOWING, userProfile.getId()));
    }

    private void onFollowClick()
    {
        if(userProfile.isFollowed())
        {
            new UnfollowCommand(userProfile).executeInBackground();
        }
        else
        {
            new FollowCommand(userProfile).executeInBackground();
        }
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.editProfileButton:
                onEditProfileClick();
                break;

            case R.id.followerButton:
                onFollowerClick();
                break;

            case R.id.followingButton:
                onFollowingClick();
                break;

            case R.id.followButton:
                onFollowClick();
                break;
        }
    }
}
