package com.walmartlabs.code.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.walmartlabs.code.R;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * Class provies custom TextView with Typeface
 */
public class WLItemTextView extends AppCompatTextView {

    public WLItemTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public WLItemTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public WLItemTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        setFocusable(false);
        if (isInEditMode()) {
            return;
        }

        int typefaceValue = 0;
        if (attrs != null) {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.WLItemTextView,
                    defStyle, 0);
            typefaceValue = values.getInt(R.styleable.WLItemTextView_menu_one_typeface, 0);
            values.recycle();
        }

        Typeface robotoTypeface = MenuTypefaceManager.obtaintTypeface(context, typefaceValue);
        setTypeface(robotoTypeface);

    }
}
