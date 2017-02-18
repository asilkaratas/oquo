package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import co.lowprofile.ooquo.R;


/**
 * Created by asilkaratas on 10/4/15.
 */
public class HeaderView extends FrameLayout
{
    private TextView titleText;
    private View backButton;
    private View menuButton;
    private View cameraButton;
    private ImageView logoImage;


    public HeaderView(Context context)
    {
        this(context, null);
    }

    public HeaderView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.HeaderView, 0, 0);
        String headerTitle = a.getString(R.styleable.HeaderView_header_title);
        a.recycle();

        LayoutInflater.from(context).inflate(getLayout(), this, true);

        titleText = (TextView)findViewById(R.id.titleText);
        backButton = findViewById(R.id.backButton);
        menuButton = findViewById(R.id.menuButton);
        cameraButton = findViewById(R.id.cameraButton);
        logoImage = (ImageView)findViewById(R.id.logoImage);

        titleText.setVisibility(GONE);
        backButton.setVisibility(GONE);
        menuButton.setVisibility(GONE);
        cameraButton.setVisibility(GONE);
        logoImage.setVisibility(GONE);


        setHeaderTitle(headerTitle);
    }

    public void setOnClickListener(OnClickListener onClickListener)
    {
        backButton.setOnClickListener(onClickListener);
        cameraButton.setOnClickListener(onClickListener);
        menuButton.setOnClickListener(onClickListener);
    }

    protected int getLayout()
    {
        return R.layout.header_view;
    }

    public void setHeaderTitle(String headerTitle)
    {
        titleText.setText(headerTitle);
    }

    public void setAsDefaultHeader()
    {
        backButton.setVisibility(VISIBLE);
        titleText.setVisibility(VISIBLE);
        cameraButton.setVisibility(VISIBLE);
    }

    public void setAsBackTitleHeader()
    {
        backButton.setVisibility(VISIBLE);
        titleText.setVisibility(VISIBLE);
    }

    public void setAsFeedHeader()
    {
        menuButton.setVisibility(VISIBLE);
        cameraButton.setVisibility(VISIBLE);
        logoImage.setVisibility(VISIBLE);
    }
}
