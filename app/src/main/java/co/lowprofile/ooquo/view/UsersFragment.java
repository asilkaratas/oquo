package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.controller.GetUsersCommand;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.GetUsersResponseEvent;
import co.lowprofile.ooquo.model.GetUsers;
import co.lowprofile.ooquo.model.User;
import co.lowprofile.ooquo.view.ui.UserListItem;
import co.lowprofile.ooquo.view.ui.UserListItemView;
import co.lowprofile.ooquo.view.common.fragment.list.AbstractListFragment;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class UsersFragment extends AbstractListFragment
{
    private static final String TAG = "UsersFragment";

    private static final String TITLE_KEY = "title";
    private static final String TYPE_KEY = "type";
    private static final String TYPE_OBJECT_ID_KEY = "typeObjectId";

    public static Bundle createBundle(String title, GetUsers.Type type, int typeObjectId)
    {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_KEY, title);
        bundle.putSerializable(TYPE_KEY, type);
        bundle.putInt(TYPE_OBJECT_ID_KEY, typeObjectId);
        return bundle;
    }

    private GetUsers.Type getType()
    {
        return (GetUsers.Type)getArguments().getSerializable(TYPE_KEY);
    }

    private String getTitle()
    {
        return getArguments().getString(TITLE_KEY);
    }

    private int getTypeObjectId()
    {
        return getArguments().getInt(TYPE_OBJECT_ID_KEY);
    }

    private List<User> users = null;

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_users;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getTitle());

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

        if(users == null)
        {
            GetUsers getUsers = new GetUsers(getType(), getTypeObjectId());
            new GetUsersCommand(getUsers).executeInBackground();
        }
        else
        {
            showUsers();
        }
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
        viewMap.put(UserListItem.class, UserListItemView.class);
        return viewMap;
    }

    public void onEventMainThread(GetUsersResponseEvent event)
    {
        users = event.getResponse().getData();
        showUsers();

    }

    private void showUsers()
    {
        List<UserListItem> items = new ArrayList<UserListItem>();
        for(User user: users)
        {
            items.add(new UserListItem(user));
        }
        setItems(items);
    }

    @Override
    protected void onBackClick()
    {
        users = null;
        super.onBackClick();
    }

    private void onUserClick(View v)
    {
        UserListItem item = (UserListItem)getListAdapter().getItem(Integer.valueOf(v.getTag().toString()));
        showFragment(UserProfileFragment.class, UserProfileFragment.createBundle(item.getUser().getId()));
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        switch (v.getId())
        {
            case R.id.userListItem:
                onUserClick(v);
                break;
        }
    }
}
