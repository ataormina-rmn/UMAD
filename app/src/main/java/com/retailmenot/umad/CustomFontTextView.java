package com.retailmenot.umad;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Hashtable;

public class CustomFontTextView
        extends TextView {

    private static final Hashtable<String, Typeface> sTypefaceCache = new Hashtable<>();

    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomFontView(this, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCustomFontView(this, attrs);
    }

    public static void initCustomFontView(TextView tv, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = tv.getContext().obtainStyledAttributes(attrs, R.styleable.CustomFontView);
            String fontName = a.getString(R.styleable.CustomFontView_fontName);
            if (fontName != null && !tv.isInEditMode()) {
                Typeface myTypeface = getTypeface(tv.getContext(), "fonts/" + fontName);
                tv.setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

    private static Typeface getTypeface(Context c, String assetPath) {
        synchronized (sTypefaceCache) {
            if (!sTypefaceCache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(),
                            assetPath);
                    sTypefaceCache.put(assetPath, t);
                } catch (Exception e) {
                    // It prints a lot of these, so comment or uncomment this as you need
//					Log.e(TAG, "Could not get typeface '" + assetPath
//							   + "' because " + e.getMessage());
                    return null;
                }
            }
            return sTypefaceCache.get(assetPath);
        }
    }
}
