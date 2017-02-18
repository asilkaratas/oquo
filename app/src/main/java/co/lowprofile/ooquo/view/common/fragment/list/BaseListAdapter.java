package co.lowprofile.ooquo.view.common.fragment.list;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by asilkaratas on 10/20/15.
 */
public class BaseListAdapter extends ArrayAdapter<Object>
{
    private static final String TAG = "BaseListAdapter";

    private View.OnClickListener onClickListener;
    private LinkedHashMap<Class<? extends BaseListItem>, Class<? extends ListItemView>> viewMap;
    private List<Class<? extends BaseListItem>> itemClasses;

    public BaseListAdapter(Context context, LinkedHashMap<Class<? extends BaseListItem>, Class<? extends ListItemView>> viewMap, View.OnClickListener onClickListener)
    {
        super(context, 0);
        this.viewMap = viewMap;
        this.onClickListener = onClickListener;
        itemClasses = new ArrayList<Class<? extends BaseListItem>>(viewMap.keySet());
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ListItemView itemView = (ListItemView)convertView;
        BaseListItem item = (BaseListItem)getItem(position);

        if(itemView == null)
        {
            Class<?> clazz = viewMap.get(item.getClass());

            try
            {
                Constructor<?> constructor = clazz.getConstructor(Context.class);
                itemView = (ListItemView)constructor.newInstance(getContext());
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {}
            catch(InstantiationException e)
            {}
            catch (IllegalAccessException e)
            {}
        }

        if(itemView != null)
        {
            itemView.setItem(item, position, onClickListener);
        }


        return (View)itemView;
    }

    @Override
    public int getItemViewType(int position)
    {
        //Log.d(TAG, "getItemViewType:" + position);
        Object item = getItem(position);
        int index = itemClasses.indexOf(item.getClass());
        return index;
    }

    @Override
    public int getViewTypeCount()
    {
        return Math.max(itemClasses.size(), 1);
    }

}
