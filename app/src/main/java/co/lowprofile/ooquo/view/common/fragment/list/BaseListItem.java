package co.lowprofile.ooquo.view.common.fragment.list;

/**
 * Created by asilkaratas on 10/28/15.
 */
public class BaseListItem
{
    private OnChangedListener onChangeListener =  null;
    private OnChangedListener onChangeListenerForItemView =  null;
    
    private boolean selected;

    public void setOnChangeListener(OnChangedListener onChangeListener)
    {
        this.onChangeListener = onChangeListener;
    }

    public void setOnChangeListenerForItemView(OnChangedListener onChangeListenerForItemView)
    {
        this.onChangeListenerForItemView = onChangeListenerForItemView;
    }

    protected void performOnChanged()
    {
        if(onChangeListener != null)
        {
            onChangeListener.onChanged(this);
        }

        if(onChangeListenerForItemView != null)
        {
            onChangeListenerForItemView.onChanged(this);
        }
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        if(this.selected == selected) return;

        this.selected = selected;
        performOnChanged();
    }

    public static interface OnChangedListener
    {
        void onChanged(BaseListItem item);
    }
}
