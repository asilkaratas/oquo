package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import co.lowprofile.ooquo.R;


/**
 * Created by asilkaratas on 10/4/15.
 */
public class TextIconButton extends FrameLayout
{
    private ImageView iconImage;
    private TextView titleText;

    public TextIconButton(Context context)
    {
        this(context, null);
    }

    public TextIconButton(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TextIconButton(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        inflate(getContext(), R.layout.text_icon_button, this);

        iconImage = (ImageView)findViewById(R.id.imgIcon);
        titleText = (TextView)findViewById(R.id.txtTitle);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.TextIconButton, 0, 0);

        if(a.hasValue(R.styleable.TextIconButton_text_icon_title))
        {
            String title = a.getString(R.styleable.TextIconButton_text_icon_title);
            setTitle(title);
        }

        if(a.hasValue(R.styleable.TextIconButton_text_icon_icon))
        {
            int icon = a.getResourceId(R.styleable.TextIconButton_text_icon_icon, 0);
            setIcon(icon);
        }

        a.recycle();
    }

    public void setIcon(int icon)
    {
        iconImage.setImageResource(icon);
    }

    public void setTitle(String title)
    {
        titleText.setText(title);
    }

    public ImageView getIconImage()
    {
        return  iconImage;
    }
}
