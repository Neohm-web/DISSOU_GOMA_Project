package com.example.dissou_goma_project;


import android.content.Context;
import android.util.AttributeSet;


import androidx.appcompat.widget.AppCompatImageButton;


public class SquareImageButton extends AppCompatImageButton {


    public SquareImageButton(Context context) {
        super(context);
    }


    public SquareImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public SquareImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Forcer la hauteur = largeur pour un carré parfait
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}






