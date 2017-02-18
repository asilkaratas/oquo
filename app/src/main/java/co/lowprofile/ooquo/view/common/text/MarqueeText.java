package co.lowprofile.ooquo.view.common.text;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class MarqueeText extends CustomFontText {
    public MarqueeText(Context context) {
        super(context);
        init();
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.setSingleLine(true);
        this.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.setMarqueeRepeatLimit(-1);
        this.setHorizontallyScrolling(true);
        this.setFocusableInTouchMode(true);
        this.setHorizontalFadingEdgeEnabled(true);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
       });
    }
}
