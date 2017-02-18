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
import co.lowprofile.ooquo.controller.SearchAuthorCommand;
import co.lowprofile.ooquo.event.EventType;
import co.lowprofile.ooquo.event.GenericEvent;
import co.lowprofile.ooquo.event.SearchAuthorResponseEvent;
import co.lowprofile.ooquo.model.Author;
import co.lowprofile.ooquo.service.webapi.common.ServiceResponse;
import co.lowprofile.ooquo.view.ui.AuthorListItem;
import co.lowprofile.ooquo.view.ui.AuthorListItemView;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;
import de.greenrobot.event.EventBus;

/**
 * Created by asilkaratas on 12/1/15.
 */
public class SearchAuthorFragment extends AbstractSearchFragment
{
    private static final String TAG = "SearchAuthorFragment";

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_search_author;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getHeaderView().setAsBackTitleHeader();
        getHeaderView().setHeaderTitle(getText(R.string.search_author).toString());

        view.findViewById(R.id.okButton).setOnClickListener(this);
        view.findViewById(R.id.newAuthorButton).setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        getView().findViewById(R.id.okButton).setOnClickListener(null);
        getView().findViewById(R.id.newAuthorButton).setOnClickListener(null);

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
        viewMap.put(AuthorListItem.class, AuthorListItemView.class);
        return viewMap;
    }

    @Override
    protected void onSearch(String key)
    {
        new SearchAuthorCommand(key).executeInBackground();
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

            case R.id.newAuthorButton:
                onNewAuthorClick();
                break;

            case R.id.authorListItem:
                onListItemViewClick(v);
                break;
        }
    }

    private void onOkClick()
    {
        if(getSelectedAuthorItem() != null)
        {
            OoquoApplication.getAppModel().setSelectedAuthor(getSelectedAuthorItem().getAuthor());
            onBackClick();
        }
        else
        {
            showAlert(getText(R.string.author_choose_error).toString());
        }
    }

    private void onNewAuthorClick()
    {
        showFragment(NewAuthorFragment.class);
    }

    private AuthorListItem getSelectedAuthorItem()
    {
        return (AuthorListItem)getSelectedItem();
    }

    public void onEventMainThread(SearchAuthorResponseEvent event)
    {
        ServiceResponse<List<Author>> response = event.getResponse();
        List<AuthorListItem> items = new ArrayList<AuthorListItem>();
        for(Author a:response.getData())
        {
            items.add(new AuthorListItem(a));
        }
        setItems(items);
    }

}
