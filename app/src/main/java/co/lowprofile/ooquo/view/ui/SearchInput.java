package co.lowprofile.ooquo.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import co.lowprofile.ooquo.R;

/**
 * Created by asilkaratas on 10/4/15.
 */
public class SearchInput extends FrameLayout
{
    private EditText inputText;
    private ImageButton closeButton;

    public SearchInput(Context context)
    {
        this(context, null);
    }

    public SearchInput(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public SearchInput(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        inflate(getContext(), R.layout.search_input, this);

        inputText = (EditText)findViewById(R.id.txtInput);
        inputText.addTextChangedListener(new SearchInputChangedListener());

        closeButton = (ImageButton)findViewById(R.id.btnClose);
        closeButton.setVisibility(INVISIBLE);
        closeButton.setOnClickListener(new CloseButtonOnClickListener());


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SearchInput, 0, 0);
        if(a.hasValue(R.styleable.SearchInput_search_hint))
        {
            String searchHint = a.getString(R.styleable.SearchInput_search_hint);
            inputText.setHint(searchHint);
        }
        a.recycle();

    }

    public EditText getInputText()
    {
        return inputText;
    }

    private class CloseButtonOnClickListener implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            inputText.setText("");
        }
    }

    private class SearchInputChangedListener implements TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            //HotelManager.getInstance().getHotelSearchParams().setSearchResultObject(null);

            if(s.length() == 0)
            {
                closeButton.setVisibility(INVISIBLE);
            }
            else
            {
                closeButton.setVisibility(VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s)
        {

        }
    }

}
