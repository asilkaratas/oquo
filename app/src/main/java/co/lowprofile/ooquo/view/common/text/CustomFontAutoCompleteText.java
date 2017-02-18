package co.lowprofile.ooquo.view.common.text;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import co.lowprofile.ooquo.view.common.font.CustomFontHelper;


public class CustomFontAutoCompleteText extends AutoCompleteTextView
{

    public CustomFontAutoCompleteText(Context context) {
        super(context);
    }

    public CustomFontAutoCompleteText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    public CustomFontAutoCompleteText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    

}
