package co.lowprofile.ooquo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.ui.SearchInput;
import co.lowprofile.ooquo.view.common.fragment.list.AbstractListFragment;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;

/**
 * Created by asilkaratas on 12/13/15.
 */
public abstract class AbstractSearchFragment extends AbstractListFragment implements TextWatcher
{
    private SearchInput searchInput;
    private View selectedView = null;
    private BaseListItem selectedItem = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        searchInput = (SearchInput)view.findViewById(R.id.searchInput);
        searchInput.getInputText().addTextChangedListener(this);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        searchInput.getInputText().removeTextChangedListener(this);
        searchInput = null;

        onListItemViewClick(null);

        super.onDestroyView();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        search();
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }

    protected SearchInput getSearchInput()
    {
        return searchInput;
    }

    protected BaseListItem getSelectedItem()
    {
        return selectedItem;
    }

    protected void onListItemViewClick(View v)
    {
        if(v != null)
        {
            int index = Integer.valueOf(v.getTag().toString());

            if(selectedView != null)
                selectedView.setSelected(false);
            selectedView = v;
            selectedView.setSelected(true);

            if(selectedItem != null)
                selectedItem.setSelected(false);
            selectedItem = (BaseListItem)getListAdapter().getItem(index);
            selectedItem.setSelected(true);

            onItemSelected(selectedItem);
        }
        else
        {
            selectedView = null;
            selectedItem = null;
        }
    }

    protected final void search()
    {
        onSearch(searchInput.getInputText().getText().toString());
    }

    protected abstract void onSearch(String key);

    protected void onItemSelected(BaseListItem selectedItem)
    {

    }
}
