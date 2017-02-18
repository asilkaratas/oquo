package co.lowprofile.ooquo.view.common.text;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import co.lowprofile.ooquo.view.common.font.CustomFontHelper;


public class CustomFontEditText extends EditText {

    public CustomFontEditText(Context context) {
        super(context);
    }

    public CustomFontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    public CustomFontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    

}
