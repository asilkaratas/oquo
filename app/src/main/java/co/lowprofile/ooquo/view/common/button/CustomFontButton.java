package co.lowprofile.ooquo.view.common.button;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import co.lowprofile.ooquo.view.common.font.CustomFontHelper;


public class CustomFontButton extends Button {

    public CustomFontButton(Context context) {
        super(context);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, context, attrs);
    }
}
