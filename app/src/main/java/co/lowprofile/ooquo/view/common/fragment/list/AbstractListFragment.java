package co.lowprofile.ooquo.view.common.fragment.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.LinkedHashMap;
import java.util.List;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.AbstractFragment;


/**
 * Created by asilkaratas on 10/20/15.
 */
public abstract class AbstractListFragment extends AbstractFragment implements View.OnClickListener
{
    private static final String TAG = "AbstractListFragment";

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        BaseListAdapter adapter = new BaseListAdapter(getContext(), getViewMap(), this);

        listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        return view;
    }

    public ListView getListView()
    {
        return listView;
    }

    public BaseListAdapter getListAdapter()
    {
        return (BaseListAdapter)getListView().getAdapter();
    }

    public abstract LinkedHashMap<Class<? extends BaseListItem>, Class<? extends ListItemView>> getViewMap();

    public void setItems(List<? extends Object> items)
    {
        BaseListAdapter listAdapter = getListAdapter();
        listAdapter.clear();
        listAdapter.addAll(items);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();

        Log.d(TAG, "onDestroyView");
        listView.setAdapter(null);
        listView = null;
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);

        Log.d(TAG, "OnClick:" + v.getId() + " tag:" + v.getTag());
    }

}
