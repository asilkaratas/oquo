package co.lowprofile.ooquo.view.common.text;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import co.lowprofile.ooquo.view.common.font.CustomFontHelper;


public class CustomFontText extends TextView {
    public CustomFontText(Context context) {
        super(context);
    }

    public CustomFontText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    public CustomFontText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }
}
