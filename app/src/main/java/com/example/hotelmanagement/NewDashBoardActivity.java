package com.example.hotelmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.liuzhenlin.slidingdrawerlayout.SlidingDrawerLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Field;

public class NewDashBoardActivity extends AppCompatActivity implements SlidingDrawerLayout.OnDrawerScrollListener {
    private DrawerArrowDrawable mHomeAsUpIndicator;
    /*synthetic*/ Toolbar mToolbar;
    @ColorInt
    /*package*/ static int sColorPrimary = -1;

    private SlidingDrawerLayout mSlidingDrawerLayout;
    private ListView mListView;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        final int screenWidth = metrics.widthPixels;
        Drawable icon = ContextCompat.getDrawable(this, R.mipmap.ic_launcher_round);
        assert icon != null;
        final float width_dif = (float) icon.getIntrinsicWidth() + 20f * metrics.density;
        mSlidingDrawerLayout = findViewById(R.id.sliding_drawer_layout);
        mSlidingDrawerLayout.setContentSensitiveEdgeSize(screenWidth);
        mSlidingDrawerLayout.setStartDrawerWidthPercent(1f - width_dif / (float) screenWidth);
        mSlidingDrawerLayout.addOnDrawerScrollListener(this);
//        //  At this activity starting, none of the drawers of SlidingDrawerLayout are available
//        // as in most cases their measurements are not yet started.
//        mSlidingDrawerLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mSlidingDrawerLayout.openDrawer(Gravity.START, true);
//            }
//        });

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                // This is a bit of a hack. Due to obsessive-compulsive disorder,
                // insert proper margin before the action bar's title.
                try {
                    Field field = mToolbar.getClass().getDeclaredField("mNavButtonView");
                    field.setAccessible(true);
                    View button = (View) field.get(mToolbar); // normally an ImageButton

                    //noinspection ConstantConditions
                    mToolbar.setTitleMarginStart((int) (width_dif - button.getWidth() + 0.5f));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mHomeAsUpIndicator = new DrawerArrowDrawable(this);
        mHomeAsUpIndicator.setGapSize(12f);
        mHomeAsUpIndicator.setColor(Color.WHITE);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(mHomeAsUpIndicator);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                SystemBarUtils.setTransparentStatus(getWindow());
////            } else {
////                SystemBarUtils.setTranslucentStatus(getWindow(), true);
////            }

//            final int statusHeight = SystemBarUtils.getStatusHeight(this);
//            mToolbar.getLayoutParams().height += statusHeight;
//            mToolbar.setPadding(mToolbar.getPaddingLeft(),
//                    mToolbar.getPaddingTop() + statusHeight,
//                    mToolbar.getPaddingRight(),
//                    mToolbar.getPaddingBottom());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @SuppressLint("RtlHardcoded")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mSlidingDrawerLayout.hasOpenedDrawer())
                    mSlidingDrawerLayout.closeDrawer(true);
                else
                    mSlidingDrawerLayout.openDrawer(Gravity.START, true);
                return true;
//            case R.id.option_see_github:
//                startActivity(
//                        new Intent(Intent.ACTION_VIEW)
//                                .setData(Uri.parse("https://github.com/lzls/SlidingDrawerLayout")));
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (mSlidingDrawerLayout.hasOpenedDrawer()) {
            mSlidingDrawerLayout.closeDrawer(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDrawerOpened(SlidingDrawerLayout parent, @NonNull View drawer) {
    }

    @Override
    public void onDrawerClosed(@NonNull SlidingDrawerLayout parent, @NonNull View drawer) {
    }

    @Override
    public void onScrollPercentChange(@NonNull SlidingDrawerLayout parent,
                                      @NonNull View drawer, float percent) {
        mHomeAsUpIndicator.setProgress(percent);

        final boolean light = percent >= 0.5f;
        final int alpha = (int) (0x7F + 0xFF * Math.abs(0.5f - percent) + 0.5f) << 24;

        if (sColorPrimary == -1)
            sColorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
        final int background = (light ? Color.WHITE : sColorPrimary) & 0x00FFFFFF | alpha;
        mToolbar.setBackgroundColor(background);

        final int foreground = (light ? Color.BLACK : Color.WHITE) & 0x00FFFFFF | alpha;
        mHomeAsUpIndicator.setColor(foreground);
        mToolbar.setTitleTextColor(foreground);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            SystemBarUtils.setLightStatus(getWindow(), light);
//            // MIUI6...
//        } else if (OSHelper.getMiuiVersion() >= 6) {
//            SystemBarUtils.setLightStatusForMIUI(getWindow(), light);
//            // FlyMe4...
//        } else if (OSHelper.isFlyme4OrLater()) {
//            SystemBarUtils.setLightStatusForFlyme(getWindow(), light);
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            SystemBarUtils.setTranslucentStatus(getWindow(), light);
//        }
    }

    @Override
    public void onScrollStateChange(@NonNull SlidingDrawerLayout parent, @NonNull View drawer,
                                    @SlidingDrawerLayout.ScrollState int state) {
        switch (state) {
            case SlidingDrawerLayout.SCROLL_STATE_TOUCH_SCROLL:
            case SlidingDrawerLayout.SCROLL_STATE_AUTO_SCROLL:
                // android:background attr set with a vector drawable resource id is not supported
                // on platforms prior to Lollipop
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP &&
                        drawer.getBackground() == null &&
                        (((SlidingDrawerLayout.LayoutParams) drawer.getLayoutParams()).gravity
                                & Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK) == Gravity.END) {
                    ViewCompat.setBackground(drawer,
                            ContextCompat.getDrawable(this, R.drawable.ic_launcher_background));
                }

                mListView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                break;
            case SlidingDrawerLayout.SCROLL_STATE_IDLE:
                mListView.setLayerType(View.LAYER_TYPE_NONE, null);
                break;
        }
    }


}