package de.companyname.core.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * This TextView could be used if the TextView should appear with a custom font type.
 * The types REGULAR, BOLD and LIGHT are available. By default, the regular font type is used. For the other types,
 * follow the two steps:
 * <p/>
 * <p/>
 * 1) Add the customAttr namespace to your xml:
 * <p/>
 * xmlns:customAttrs="http://schemas.android.com/apk/res-auto"
 * <p/>
 * <p/>
 * 2) Set the companynameFont attribute to 'bold','light' or 'regular'.
 * <p/>
 * <de.companyname.core.ui.widget.companynameFontTextView
 * customAttrs:companynameFont="bold"
 * ...
 * />
 */
public class companynameFontTextView extends TextView {


    public companynameFontTextView(Context context) {
        super(context);
        init(null);
    }


    public companynameFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public companynameFontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public companynameFontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
       //abbreviated
    }

    private void setupGraph(){
        // shortened
    }

    public void linkifyAndSetText(String text, int linkifyMask) {
        // shortened
    }

}
