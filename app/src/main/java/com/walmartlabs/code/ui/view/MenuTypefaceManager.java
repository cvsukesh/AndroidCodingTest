package com.walmartlabs.code.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;

/**
 * This class creates and manages the Typeface for application UI.
 */
public class MenuTypefaceManager {

    public final static int ROBOTO_LIGHT = 0;
    public final static int ROBOTO_REGULAR = 1;
    public final static int ROBOTO_MEDIUM = 2;
    public final static int ROBOTO_BOLD = 3;

    /**
     * Array of created typefaces for later reused.
     */
    private final static SparseArray<Typeface> mTypefaces = new SparseArray<>();

    /**
     * this method provides typeface depend on typeface value.
     *
     * @param context       The Context the widget is running in, through which it can access the current
     *                      theme, resources, etc.
     * @param typefaceValue The value of "typeface" attribute
     * @return specify {@link Typeface}
     * @throws IllegalArgumentException if unknown typeface attribute value.
     */
    public static Typeface obtaintTypeface(Context context, int typefaceValue)
            throws IllegalArgumentException {
        Typeface typeface = mTypefaces.get(typefaceValue);
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue);
            mTypefaces.put(typefaceValue, typeface);
        }
        return typeface;
    }

    /**
     * Create typeface from assets.
     *
     * @param context       The Context the widget is running in, through which it can access the current
     *                      theme, resources, etc.
     * @param typefaceValue The value of "typeface" attribute
     * @return Roboto {@link Typeface}
     * @throws IllegalArgumentException if unknown typeface attribute value.
     */
    private static Typeface createTypeface(Context context, int typefaceValue)
            throws IllegalArgumentException {
        Typeface typeface;
        switch (typefaceValue) {
            case ROBOTO_LIGHT:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
                break;
            case ROBOTO_REGULAR:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
                break;
            case ROBOTO_MEDIUM:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
                break;
            case ROBOTO_BOLD:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
                break;
            default:
                throw new IllegalArgumentException("Unknown typeface attribute value " + typefaceValue);
        }
        return typeface;
    }

}