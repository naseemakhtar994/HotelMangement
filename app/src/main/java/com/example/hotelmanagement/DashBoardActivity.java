package com.example.hotelmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.civil.CivilCalendar;
import com.aminography.primecalendar.japanese.JapaneseCalendar;
import com.aminography.primedatepicker.picker.PrimeDatePicker;
import com.aminography.primedatepicker.picker.callback.MultipleDaysPickCallback;
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback;
import com.aminography.primedatepicker.picker.callback.SingleDayPickCallback;
import com.aminography.primedatepicker.picker.theme.LightThemeFactory;
import com.liuzhenlin.slidingdrawerlayout.SlidingDrawerLayout;

import java.util.Calendar;
import java.util.List;

public class DashBoardActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String User = "User";
    SharedPreferences sharedpreferences;
    Toolbar toolbar;
    SlidingDrawerLayout mSlidingDrawerLayout;
    TextView textViewLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        DrawerArrowDrawable mHomeAsUpIndicator = new DrawerArrowDrawable(this);
        mHomeAsUpIndicator.setGapSize(12f);
        mHomeAsUpIndicator.setColor(Color.WHITE);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        toolbar=findViewById(R.id.toolbar);
        mSlidingDrawerLayout=findViewById(R.id.sliding_drawer_layout);
//        textViewLogout=mSlidingDrawerLayout.getChildAt(0).findViewById(R.id.textViewLogout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(mHomeAsUpIndicator);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);


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



    public void logout(View view) {
        mSlidingDrawerLayout.closeDrawer(true);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(User, null);

        editor.commit();
        Intent intent=new Intent(getApplicationContext(),LooginActivity.class);
        startActivity(intent);
        finish();
    }



    public void AdminView(View view) {
        mSlidingDrawerLayout.closeDrawer(true);

        Intent intent=new Intent(getApplicationContext(),AdminLogin.class);
        startActivity(intent);
    }



    public void Compare(View view) {
        Intent intent=new Intent(getApplicationContext(),CompareActivity.class);
        startActivity(intent);
    }

    public void SearchHotels(View view) {
        Intent intent=new Intent(getApplicationContext(),ListingActivity.class);
        startActivity(intent);
    }

    public void ShowMyboking(View view) {
        Intent intent=new Intent(getApplicationContext(),BookingListingActivity.class);
        startActivity(intent);

    }

    public void myboking(View view) {
        Intent intent=new Intent(getApplicationContext(),MyAccount.class);
        startActivity(intent);

    } public void Help(View view) {
        Intent intent=new Intent(getApplicationContext(),HelpActivity.class);
        startActivity(intent);

    }
}