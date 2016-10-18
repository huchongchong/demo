package com.demo.administrator.demo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.demo.administrator.demo.view.TouchImageView;
import com.demo.administrator.demo.view.colorpickerview.dialog.ColorPickerDialogFragment;
import com.demo.administrator.demo.view.colorpickerview.view.ColorPanelView;
import com.demo.administrator.demo.view.colorpickerview.view.ColorPickerView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends Activity implements ColorPickerView.OnColorChangedListener,ColorPickerDialogFragment.ColorPickerDialogListener, View.OnClickListener{


    private View mContentView;
    private View mControlsView;
    private boolean mVisible;
    private TouchImageView touchImageView;
    private int w = 2;
    private ColorPickerView mColorPickerView;
    private ColorPanelView mOldColorPanelView;
    private ColorPanelView mNewColorPanelView;
    private Button mOkButton;
    private Button mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        init();
    }

    private void init() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int initialColor = prefs.getInt("color_3", 0xFF000000);

        mColorPickerView = (ColorPickerView) findViewById(R.id.colorpickerview__color_picker_view);
        mOldColorPanelView = (ColorPanelView) findViewById(R.id.colorpickerview__color_panel_old);
        mNewColorPanelView = (ColorPanelView) findViewById(R.id.colorpickerview__color_panel_new);

        mOkButton = (Button) findViewById(R.id.okButton);
        mCancelButton = (Button) findViewById(R.id.cancelButton);


        ((LinearLayout) mOldColorPanelView.getParent()).setPadding(
                mColorPickerView.getPaddingLeft(), 0,
                mColorPickerView.getPaddingRight(), 0);


        mColorPickerView.setOnColorChangedListener(this);
        mColorPickerView.setColor(initialColor, true);
        mOldColorPanelView.setColor(initialColor);

        mOkButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onColorChanged(int newColor) {
        mNewColorPanelView.setColor(mColorPickerView.getColor());
    }
    public void onClickColorPickerDialog() {

        // The color picker menu item has been clicked. Show
        // a dialog using the custom ColorPickerDialogFragment class.

        ColorPickerDialogFragment f = ColorPickerDialogFragment
                .newInstance(1, null, null, Color.BLACK, true);

        f.setStyle(DialogFragment.STYLE_NORMAL, R.style.LightPickerDialogTheme);
        f.show(getFragmentManager(), "d");

    }
    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.okButton:
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
                edit.putInt("color_3", mColorPickerView.getColor());
                edit.commit();
               onClickColorPickerDialog();
                break;
            case R.id.cancelButton:
                finish();
                break;
        }

    }

    @Override
    public void onColorSelected(int dialogId, int color) {

    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}
