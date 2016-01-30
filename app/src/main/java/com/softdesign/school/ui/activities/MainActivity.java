package com.softdesign.school.ui.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.softdesign.school.R;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String VISIBLE_KEY = "visible";
    public static final String COLOR_TOOLBAR_KEY = "t_color";
    public static final String COLOR_STATUSBAR_KEY = "s_color";

    CheckBox mCheckBox;
    EditText mEditText1, mEditText2;
    Toolbar mToolbar;
    Button mBtnRed, mBtnBlue, mBtnGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lg.i(this.getLocalClassName(), "on create");

        setTitle("Home Task 2");

        mCheckBox = (CheckBox)findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mEditText1 = (EditText)findViewById(R.id.editText1);
        mEditText2 = (EditText)findViewById(R.id.editText2);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setupToolbar();

        mBtnRed = (Button)findViewById(R.id.btn_red);
        mBtnRed.setOnClickListener(this);
        mBtnBlue = (Button)findViewById(R.id.btn_blue);
        mBtnBlue.setOnClickListener(this);
        mBtnGreen = (Button)findViewById(R.id.btn_green);
        mBtnGreen.setOnClickListener(this);
    }

    private void  setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Menu click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *   Задание цвета
     */
    private void setMainColor(int tool_color, int stat_color){
        mToolbar.setBackgroundColor(this.getResources().getColor(tool_color));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(this.getResources().getColor(stat_color));
        }
    }

    /**
    *       Обработка клика по элементам
     */
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.checkBox:
                Toast.makeText(this, "Click CheckBox", Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()) {
                    mEditText1.setVisibility(View.INVISIBLE);
                } else {
                  mEditText1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_red:
                Toast.makeText(this, "Red Color", Toast.LENGTH_SHORT).show();
                setMainColor(R.color.color_red, R.color.color_dark_red);
                break;
            case R.id.btn_blue:
                Toast.makeText(this, "Blue Color", Toast.LENGTH_SHORT).show();
                setMainColor(R.color.color_blue, R.color.color_dark_blue);
                break;
            case R.id.btn_green:
                Toast.makeText(this, "Green Color", Toast.LENGTH_SHORT).show();
                setMainColor(R.color.color_green, R.color.color_dark_green);
                break;
            default:
                break;
        }
    }

    /**
     *  Выводят логи
     */
    @Override
    protected void onStart() {
        super.onStart();
        Lg.i(this.getLocalClassName(), "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.i(this.getLocalClassName(), "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.i(this.getLocalClassName(), "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.i(this.getLocalClassName(), "on stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.i(this.getLocalClassName(), "on restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.i(this.getLocalClassName(), "on destroy");
    }

    /**
     *   Сохранение определенных параметров
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.i(this.getLocalClassName(), "on save instance state");
        outState.putBoolean(VISIBLE_KEY, mEditText1.getVisibility() == View.VISIBLE);

        outState.putInt(COLOR_TOOLBAR_KEY, ((ColorDrawable) mToolbar.getBackground()).getColor());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outState.putInt(COLOR_STATUSBAR_KEY, getWindow().getStatusBarColor());
        }
    }

    /**
     *   Восстанавливает параметры
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.i(this.getLocalClassName(), "on restore instance state");
        int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;

        mEditText1.setVisibility(visibleState);

        setMainColor(savedInstanceState.getInt(COLOR_TOOLBAR_KEY), savedInstanceState.getInt(COLOR_STATUSBAR_KEY));
    }
}
