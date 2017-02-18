package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import co.lowprofile.ooquo.R;
import co.lowprofile.ooquo.view.common.fragment.list.BaseListItem;
import co.lowprofile.ooquo.view.common.fragment.list.ListItemView;

/**
 * Created by asilkaratas on 12/6/15.
 */
public class QuoteListItemView extends FrameLayout implements ListItemView
{
    public QuoteListItemView(Context context)
    {
        this(context, null);
    }

    public QuoteListItemView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public QuoteListItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.quote_list_item_view, this);
    }

    @Override
    public void setItem(BaseListItem item, int position, OnClickListener onClickListener)
    {
        QuoteListItem quoteListItem = (QuoteListItem)item;

        TextView titleText = (TextView)findViewById(R.id.titleText);
        titleText.setText(quoteListItem.getQuote().getText());

        View view = findViewById(R.id.quoteListItem);
        view.setTag(position);
        view.setOnClickListener(onClickListener);
        view.setSelected(quoteListItem.isSelected());
    }
}
