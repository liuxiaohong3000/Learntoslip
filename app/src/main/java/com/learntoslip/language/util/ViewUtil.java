package com.learntoslip.language.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.InputFilter.LengthFilter;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ActionMode.Callback;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.TextView;

public final class ViewUtil {
    public ViewUtil() {
    }

    public static void setTextWeightBold(@NonNull TextView... tvs) {
        TextView[] var1 = tvs;
        int var2 = tvs.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TextView tv = var1[var3];
            if(tv != null) {
                tv.getPaint().setFakeBoldText(true);
            }
        }

    }

    public static void setTextWeightNormal(@NonNull TextView... tvs) {
        TextView[] var1 = tvs;
        int var2 = tvs.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TextView tv = var1[var3];
            if(tv != null) {
                tv.getPaint().setFakeBoldText(false);
            }
        }

    }

    public static View inflateViewStub(@NonNull ViewStub vs) {
        return vs.inflate();
    }

    public static View inflate(@NonNull Context context, @LayoutRes int layout) {
        return LayoutInflater.from(context).inflate(layout, (ViewGroup)null);
    }

    public static View inflate(@NonNull Context context, @LayoutRes int layout, ViewGroup root) {
        return LayoutInflater.from(context).inflate(layout, root, false);
    }

    public static View inflate(@NonNull Context context, @LayoutRes int layout, ViewGroup root, boolean attachToRoot) {
        return LayoutInflater.from(context).inflate(layout, root, attachToRoot);
    }

    public static int measureTextHeight(float textSizePx) {
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTextSize(textSizePx);
        paint.getTextBounds("测试", 0, "测试".length(), rect);
        return rect.height();
    }

    public static int measureTextWidth(float textSizePx) {
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTextSize(textSizePx);
        paint.getTextBounds("测", 0, "测".length(), rect);
        return rect.width();
    }

    public static void disableCopyPaste(@NonNull EditText editText) {
        editText.setLongClickable(false);
        editText.setTextIsSelectable(false);
        editText.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                return true;
            }
        });
        editText.setCustomSelectionActionModeCallback(new Callback() {
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            public void onDestroyActionMode(ActionMode mode) {
            }
        });
    }

    public static Bitmap screenshot(@NonNull View view, Config config) {
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), config);
        view.draw(new Canvas(bitmap));
        return bitmap;
    }

    public static void limitEditOnlyASCII(EditText editText) {
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuffer buffer = new StringBuffer();

                for(int i = 0; i < source.length(); ++i) {
                    char c = source.charAt(i);
                    if(c <= 127) {
                        buffer.append(c);
                    }
                }

                return buffer;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    public static void limitEditOnlyASCII(EditText editText, int maxLength) {
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuffer buffer = new StringBuffer();

                for(int i = 0; i < source.length(); ++i) {
                    char c = source.charAt(i);
                    if(c <= 127) {
                        buffer.append(c);
                    }
                }

                return buffer;
            }
        };
        LengthFilter lengthFilter = new LengthFilter(maxLength);
        editText.setFilters(new InputFilter[]{filter, lengthFilter});
    }
}
