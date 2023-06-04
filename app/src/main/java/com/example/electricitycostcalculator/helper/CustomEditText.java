package com.example.electricitycostcalculator.helper;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import com.example.electricitycostcalculator.R;

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // Metin rengini ayarla
        setTextColor(ContextCompat.getColor(context, R.color.black));

        // Arka plan rengini ayarla
        int backgroundColor = ContextCompat.getColor(context, R.color.white);
        setBackground(createBackgroundDrawable(backgroundColor));

        // Kenarlık eklemek için aşağıdaki satırları kullanabilirsiniz
         int strokeColor = ContextCompat.getColor(context, R.color.gray);
         int strokeWidth = 1;
         setBackground(createBackgroundDrawable(backgroundColor, strokeColor, strokeWidth));
    }

    private GradientDrawable createBackgroundDrawable(int backgroundColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(backgroundColor);
        drawable.setCornerRadius(10f);
        return drawable;
    }

    // Kenarlık eklemek için aşağıdaki yöntemi kullanabilirsiniz
    private GradientDrawable createBackgroundDrawable(
            int backgroundColor, int strokeColor, int strokeWidth) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(backgroundColor);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadius(10f);
        return drawable;
    }
}
